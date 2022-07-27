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
import javax.swing.*


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
        //2.解析功能语义 && 3.分页逻辑3.0
        var tarText = readerService.resoveLeagueStruct(editor, project)
        //4.展示结果
        readerService.showText(editor, project, tarText)

        //取得gui的内容
        val toolWindow: ToolWindow = ToolWindowManager.getInstance(project).getToolWindow("Comment Book") ?: return
        toolWindow.show({ })
        var contentManager: JPanel = toolWindow.getContentManager().getContent(0)?.component as JPanel

        val inputFiled: JTextField = contentManager.getComponent(0) as JTextField
        val inpurLabel = contentManager.getComponent(1) as JLabel
        val returnLabel = contentManager.getComponent(2) as JLabel
        val exeButton = contentManager.getComponent(3) as JButton
        val selectBox = contentManager.getComponent(4) as JComboBox<String>
        val outputFiled = contentManager.getComponent(5) as JTextPane

        val message =
            "inputFiled: ${inputFiled.text} \n inpurLabel: ${inpurLabel.text} \n returnLabel: ${returnLabel.text} \n exeButton: ${exeButton.text} \n selectBox: ${selectBox.selectedItem} \n outputFiled: ${outputFiled.text}"
        println(message)
        outputFiled.text = message

    }

    /*业务逻辑*/
}



