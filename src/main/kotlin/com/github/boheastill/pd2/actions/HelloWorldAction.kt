package com.github.boheastill.pd2.actions

import com.github.boheastill.pd2.services.MyProjectService
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.ide.CopyPasteManager
import com.intellij.openapi.ui.Messages
import com.intellij.psi.PsiComment
import com.intellij.psi.PsiFileFactory
import java.awt.datatransfer.DataFlavor


class HelloWorldAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        //获取当前在操作的工程上下文
        val project = e.getData(PlatformDataKeys.PROJECT)

        //获取当前操作的类文件
        val psiFile = e.getData(CommonDataKeys.PSI_FILE)
        //获取当前类文件的路径
        val classPath = psiFile!!.virtualFile.path
        val title = "Hello World!"
        //生成注释
        MyProjectService.instance

        //显示对话框
        Messages.showMessageDialog(project, classPath, title, Messages.getInformationIcon())
    }

}