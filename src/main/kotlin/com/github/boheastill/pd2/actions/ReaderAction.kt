package com.github.boheastill.pd2.actions

import com.github.boheastill.pd2.services.ReaderService
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowManager
import javax.swing.JPanel


class ReaderAction : AnAction() {
    private val readerService: ReaderService = ServiceManager.getService(ReaderService::class.java)


    /**
     * 触发事件
     * todo 持久化   gui
     */
    override fun actionPerformed(anActionEvent: AnActionEvent) {
        print(anActionEvent)
        //1.获取参数
        val editor: Editor = anActionEvent.getRequiredData(CommonDataKeys.EDITOR)
        val project: Project = anActionEvent.getRequiredData(CommonDataKeys.PROJECT)
        //2.获取文本
        var tarText = readerService.resoveLeagueStruct2(editor, project)
        //4.展示结果
        readerService.showText(editor, project, tarText)
        //取得gui的内容
//        guitool(project)
    }

    private fun guitool(project: Project) {
        val toolWindow: ToolWindow = ToolWindowManager.getInstance(project).getToolWindow("Comment Book") ?: return
        toolWindow.show({ })

        var contentManager: JPanel = toolWindow.getContentManager().getContent(0)?.component as JPanel
        contentManager.components.forEach {
            println(it.javaClass.name)
        }
    }

    /*业务逻辑*/
}



