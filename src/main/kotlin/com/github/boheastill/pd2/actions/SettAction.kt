package com.github.boheastill.pd2.actions

import FunBoxValueI18n
import com.github.boheastill.pd2.entity.BookEntity
import com.github.boheastill.pd2.services.ReaderService
import com.github.boheastill.pd2.toolWindow.ConfigView
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project

class SettAction : AnAction() {

    var chachText: String = ""
    var display: Boolean = true    //仅加载第一次触发为真

    private val bookEntity: BookEntity = BookEntity()
    var chachpath = ""
    private val readerService: ReaderService = ServiceManager.getService(ReaderService::class.java)


    /**
     * 触发事件
     * todo 持久化   gui
     */
    override fun actionPerformed(anActionEvent: AnActionEvent) {
        /*if (display) {
            display = false
            return bookEntity.displaySignNum()
        }*/

        //1.参数
        val editor: Editor = anActionEvent.getRequiredData(CommonDataKeys.EDITOR)
        val project: Project = anActionEvent.getRequiredData(CommonDataKeys.PROJECT)

        // 1.展示用户设置窗口并读取配置
        var configView = ConfigView();
        configView.isVisible = true;
        var action = configView.action
        var keyWord: String = configView.textField1.toString()
        var ouputFiled = configView.textField2
        ouputFiled?.text = "output"
        var text = getText(action, keyWord)
        readerService.showText(editor, project, text)
    }

    private fun getText(action: FunBoxValueI18n?, keyWord: String): String {
        var text = "无法解析语义"
        when (action) {
            FunBoxValueI18n.PRE -> {
                text = bookEntity.previousPageNum(bookEntity.disChachNum, 1)
            }

            FunBoxValueI18n.NEXT -> {
                text = bookEntity.nextPageNum(bookEntity.disChachNum, 1)

            }

            FunBoxValueI18n.DOWN -> {
            }

            FunBoxValueI18n.LOAD -> {
                var path = keyWord.substring(1)
                if (path != chachpath) {
                    bookEntity.loadFile2String(path)
                    chachpath = path
                    text = bookEntity.displaySignNum()
                } else {
                    text = bookEntity.nextPageNum(bookEntity.disChachNum, 1)
                }
            }

            FunBoxValueI18n.JUMPTO -> {
                var tarstr = keyWord.substring(1)
                if (tarstr.indexOf("%") != -1) {
                    var tarrate = tarstr.substring(0, tarstr.indexOf("%")).toInt()

                    text = bookEntity.getTextByPageNum(tarrate)
                } else {
                    var signPageNum = keyWord.substring(1).toInt()
                    text = bookEntity.getTextByPageNum(signPageNum)
                }
            }

            FunBoxValueI18n.MARK_SET -> {
                var markLabel = keyWord.substring(1)
                bookEntity.markMap[markLabel] = bookEntity.disChachNum
                text = "已经标记"
            }

            FunBoxValueI18n.MARK_GET -> {
                var markLabel = keyWord.substring(1)
                var markPageNum = bookEntity.markMap[markLabel]
                if (markPageNum != null) {
                    text = bookEntity.getTextByPageNum(markPageNum)
                } else {
                    text = "没有标记"
                }
            }

            FunBoxValueI18n.SEARCH -> {
                var findStr = keyWord.substring(1)
                text = bookEntity.find(findStr)
            }

            FunBoxValueI18n.SAFE -> {
                text = "序列化方法\n" +
                        " * 反序列化方法\n" +
                        " * 实现 测试文本1 父类的POJO,\n" +
                        " * POJO包含： private static final long serialVersionUID = 1L;"
            }
        }
        return text
    }

    /*业务逻辑*/
}
