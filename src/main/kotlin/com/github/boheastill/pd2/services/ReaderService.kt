package com.github.boheastill.pd2.services

import com.github.boheastill.pd2.entity.BookEntity
import com.github.boheastill.pd2.util.util
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.util.PsiUtilBase
import com.intellij.psi.util.elementType
import java.util.regex.Pattern

/*todo 多返回值 ，跳页，固定位置*/
class ReaderService {
    //    var cstart: Int = 0
//    var cend: Int = 0
    var chachText: String = ""
    var display: Boolean = true    //仅第一次触发为真

    private val bookEntity: BookEntity = BookEntity()

    /**
     * 语义解析：
     * 定义：在类注释上，已^或……为开头和结尾为语法行，中间以空格分隔，如：^前7^  ,……后1……
     * 语法行开头有斜杠被认为是注释，不解析，如：^/前7^ ……\后1……
     * 语法行含义集：
     * 向前翻页（+数量）
     * 向后翻页（+数量）
     * 安全模式
     * 搜索文本页码
     * 跳转指定页/比例
     * 存/取书签
     * //todo 持久化含义集合，并支持自定义映射，增加映射冲突检查
     * */
    fun resoveLeagueStruct(editor: Editor, project: Project): String {
        //如果是展示，直接返回
        if (display) {
            display = false
            return bookEntity.signNum()
        }

        //分析语义
        var classComnenText = getClassComent(editor, project)
        var keyWord = util.regText(classComnenText, Pattern.compile("(?<=(\\^|……))[^,.，。:：]+?(?=(\\^|……))"))

        //无语义，返回下一个文本
        if (keyWord == null || keyWord.substring(0, 1) == "/" || keyWord.substring(0, 1) == "\\") {
            return bookEntity.nextPageNum(bookEntity.disChachNum, 1)
        }

        //按语义，返回
        var text = "无法解析语义"
        println("keyWord:$keyWord")
        when (keyWord.substring(0, 1)) {
            "l", "左", "前", "上" -> {
                text = bookEntity.previousPageNum(bookEntity.disChachNum, 1)
            }

            "s", "安" -> {
                text = "序列化方法\n" +
                        " * 反序列化方法\n" +
                        " * 实现 测试文本1 父类的POJO,\n" +
                        " * POJO包含： private static final long serialVersionUID = 1L;"
            }

            "r", "右", "后", "下" -> {
                text = bookEntity.nextPageNum(bookEntity.disChachNum, 1)

            }

            "f", "查", "搜" -> {
                var findStr = keyWord.substring(1)
                text = bookEntity.find(findStr)
            }

            "到" -> {
                var tarstr = keyWord.substring(1)
                if (tarstr.indexOf("%") != -1) {
                    var tarrate = tarstr.substring(0, tarstr.indexOf("%")).toInt()

                    text = bookEntity.getTextByPageNum(tarrate)
                } else {
                    var signPageNum = keyWord.substring(1).toInt()
                    text = bookEntity.getTextByPageNum(signPageNum)
                }
            }

            "标", "存" -> {
                var markLabel = keyWord.substring(1)
                bookEntity.markMap[markLabel] = bookEntity.disChachNum
                text = "已经标记"
            }

            "取", "签" -> {
                var markLabel = keyWord.substring(1)
                //从map中取出值
                var signPageNum: Int? = bookEntity.markMap[markLabel]
                if (signPageNum == null) {
                    text = "未标记"
                } else {
                    text = bookEntity.getTextByPageNum(signPageNum)
                }
            }
        }
        return text
    }

    /**
     * 光标 展示给定文本
     * 特定位置：优先替换光标选择文本，否则替换光标缓存的文本，否则在当前光标处生成文本块并缓存。
     * */
    fun textDisplayLogic(editor: Editor, project: Project, repText: String) {
        val document: Document = editor.document

        // Work off of the primary caret to get the selection info
        val primaryCaret: Caret = editor.caretModel.primaryCaret


        var start: Int = primaryCaret.getSelectionStart()
        var end: Int = primaryCaret.getSelectionEnd()
//        println("start: $start, end: $end")
        //未选择文本。替换缓存，或当前行生成。
        if (start == end) {
            val indexOf = document.getText().indexOf(chachText)
            //如果搜到了缓存的文本，替换该文本
            if (chachText.length != 0 && indexOf != -1) {

                start = indexOf
                end = start + chachText.length
            } else {
                //没有缓存的文本，生成文本块
                println("当前文本 $repText \n之前文本\n" + chachText)
            }
        }
        // Replace the selection with a fixed string.
        // Must do this document change in a write action context.
        //生成过，就缓存
        WriteCommandAction.runWriteCommandAction(project) {
            document.replaceString(start, end, repText)
            chachText = repText
        }
        //计算替换后的坐标
//        println("cstart: $cstart, cend: $cend")
        // De-select the text range that was just replaced
        primaryCaret.removeSelection()
    }

    /*文本格式化//todo 校验字符，防止误匹配原代码 风格可选*/
    fun textFormat(text: String): String {
        var ftext = text.trim().replace("\n", "").replace("\r", "").replace("\t", "")
        var length = ftext.length
        var stringBuilder = StringBuilder(ftext)
        var rawMax= 3
        for (rawLine in 1 until rawMax) {
            var div = length.toDouble() * rawLine.toDouble() / rawMax.toDouble()
//            println("div: ${div}")
            stringBuilder.insert(div.toInt(), "\n\t")
        }

        //按行分割
        var textComentWapper = "/*\n\t $stringBuilder \n*/"
        return textComentWapper
    }
//    /*文本格式化//todo 校验字符，防止误匹配原代码*/
//    fun textFormat(text: String): String {
//        var ftext = text.trim().replace("\n", "").replace("\r", "").replace("\t", "")
//            .replace("。", "。\n\t")
//            .replace(".", ".\n\t")
//        var wapperText = "/*\n\t $ftext \n*/"
//        return wapperText
//    }


    fun showText(editor: Editor, project: Project, text: String) {
        //文本格式化
        var wapperText = textFormat(text)
        //文本展示
        textDisplayLogic(editor, project, wapperText)
    }

    fun getClassComent(editor: Editor, project: Project): String {
        var classConment = ""
        var psiFile = PsiUtilBase.getPsiFileInEditor(editor, project) ?: return ""
        psiFile.children.forEach {
            it.children.forEach {
                if (it.elementType.toString() == "C_STYLE_COMMENT") {
                    classConment = it.text
                }
            }
        }
        return classConment
    }


}
