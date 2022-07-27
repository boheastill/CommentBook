package com.github.boheastill.pd2.actions

import com.github.boheastill.pd2.services.ReaderService
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindowManager


class ReaderAction : AnAction() {
    private val readerService: ReaderService = ServiceManager.getService(ReaderService::class.java)


    /**
     * 触发事件
     * todo 持久化   gui
     */
    override fun actionPerformed(anActionEvent: AnActionEvent) {

        //1.获取参数
        val editor: Editor = anActionEvent.getRequiredData(CommonDataKeys.EDITOR)
        val project: Project = anActionEvent.getRequiredData(CommonDataKeys.PROJECT)
        //2.解析功能语义 && 3.分页逻辑3.0
        var tarText = readerService.resoveLeagueStruct(editor, project)
        //4.展示结果
        readerService.showText(editor, project, tarText)


    }

    /*业务逻辑*/
}



