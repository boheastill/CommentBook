package com.github.boheastill.pd2.actions

import com.github.boheastill.pd2.services.TextService
import com.github.boheastill.pd2.toolWindow.TraView
import com.github.boheastill.pd2.util.FanyiV3Demo.traslationSimple
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project

class TraAction : AnAction() {

    private val textService: TextService = ServiceManager.getService(TextService::class.java)

    /**
     * 触发事件
     * todo 持久化   gui
     */
    override fun actionPerformed(anActionEvent: AnActionEvent) {


        //1.参数
        val editor: Editor = anActionEvent.getRequiredData(CommonDataKeys.EDITOR)
        val project: Project = anActionEvent.getRequiredData(CommonDataKeys.PROJECT)
        var cheakedText = textService.cheakedText(project, editor)
        println("cheakedText = $cheakedText")
        // 1.展示用户设置窗口并读取配置
        var conView = TraView();
        var ta = conView.textArea1;
        var traslationSimple = traslationSimple(cheakedText);
        ta?.text = traslationSimple

        conView.isVisible = true;

//        showService.showText(editor, project, conView.outPutPane?.text + "^-^" + conView.infoField?.text)

    }

}
