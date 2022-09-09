package com.github.boheastill.pd2.actions

import com.github.boheastill.pd2.services.ShowService
import com.github.boheastill.pd2.services.TextService
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project


class TextAction : AnAction() {

    private val textService: TextService = ServiceManager.getService(TextService::class.java)

    private val showService: ShowService = ServiceManager.getService(ShowService::class.java)

    /**
     * This method is called when the action is invoked.
     */
    override fun actionPerformed(anActionEvent: AnActionEvent) {
        //1.参数
        val editor: Editor = anActionEvent.getRequiredData(CommonDataKeys.EDITOR)
        val project: Project = anActionEvent.getRequiredData(CommonDataKeys.PROJECT)
        //2.文本
        var textByNext = textService.getDisText()
        //4.展示
        showService.showText(editor, project, textByNext)
    }

}



