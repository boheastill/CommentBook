package com.github.boheastill.pd2.actions

import com.github.boheastill.pd2.services.ReaderService
import com.github.boheastill.pd2.toolWindow.ConView
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project

class SettAction : AnAction() {
    private val readerService: ReaderService = ServiceManager.getService(ReaderService::class.java)

    /**
     * 触发事件
     * todo 持久化   gui
     */
    override fun actionPerformed(anActionEvent: AnActionEvent) {

        //1.参数
        val editor: Editor = anActionEvent.getRequiredData(CommonDataKeys.EDITOR)
        val project: Project = anActionEvent.getRequiredData(CommonDataKeys.PROJECT)

        // 1.展示用户设置窗口并读取配置
        var conView = ConView();
        conView.isVisible = true;
        var action = conView.action
        var inputFiled = conView.inputFiled?.text
        var ouputFiled = conView.outPutPane?.text

//        var text = getText(action, inputFiled?.trim())
//        ouputFiled = text
//        readerService.showText(editor, project, text)
    }

}
