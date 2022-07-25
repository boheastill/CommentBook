package com.github.boheastill.pd2.actions

import com.github.boheastill.pd2.services.WriterService
import com.github.boheastill.pd2.services.textStr
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.components.ServiceManager
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IElementType
import com.intellij.psi.util.PsiUtilBase
import com.intellij.psi.util.elementType
import com.jetbrains.rd.util.remove
import javax.swing.tree.DefaultMutableTreeNode

class ReaderAction : AnAction() {
    //获取Service
//    private val translatorService: TranslatorService = ServiceManager.getService(
//        TranslatorService::class.java
//    )
    private val writerService: WriterService = ServiceManager.getService(WriterService::class.java)

    var startIdx = 0
    val maxIndex = textStr.length - 1
    val contenLenth = 50


    /*业务逻辑*/
    override fun actionPerformed(anActionEvent: AnActionEvent) {
//        textReader(anActionEvent)
        //注释生成
//        val psiElement = anActionEvent.getData(LangDataKeys.PSI_ELEMENT)

        var project = anActionEvent.getProject() ?: return
        var editor = anActionEvent.getData(PlatformDataKeys.EDITOR) ?: return
        var psiFile = PsiUtilBase.getPsiFileInEditor(editor, project) ?: return
        var fileName = psiFile.getName();
        var document = PsiDocumentManager.getInstance(project).getDocument(psiFile);
//        var fileNode = DefaultMutableTreeNode(fileName);
        // 遍历当前对象的所有属性
        psiFile.children.forEach {
            if (it.elementType.toString() == "CLASS") {
                it.children.forEach {
                    if (it.elementType.toString() == "C_STYLE_COMMENT") {
                        var comment = it.text
                        var commentStr = comment.substring(2, comment.length - 2)
                        println(commentStr)
                        //替换注释
                        commentStr="测试替换文本"
                        writerService.write(anActionEvent,commentStr)
                    }
                }
            }
        }

    }

    private fun textReader(anActionEvent: AnActionEvent) {
        //取(startIdx + contenLenth) 和 maxIndex 中小的那个
        //从头再来
        if (startIdx >= maxIndex) {
            startIdx = 0
        }
        val endIdx = Math.min(startIdx + contenLenth, maxIndex)
        var text = textStr.substring(startIdx, endIdx)
        startIdx += contenLenth
        //这里就直接替换了原文
        writerService.write(anActionEvent, text)
    }
}

