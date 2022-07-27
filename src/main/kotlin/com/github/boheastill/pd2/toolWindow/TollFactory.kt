// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.github.boheastill.pd2.toolWindow

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory

class TollFactory : ToolWindowFactory {
    /**
     *IDEA打开后后执行一次
     * 可以设置参数给组件
     *
     * @param project    current project
     * @param toolWindow current tool window
     */
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        //获取AnActionEvent

//        println("创建工具窗口")
        //todo 了解单构造函数为什么传入idea的工具窗口，和form、conten的关系
        //创建自定义组件
        val guiWindow = Tool2(toolWindow)
        val contentFactory = ContentFactory.SERVICE.getInstance()
        //给自定义窗口传一个panel和一个标题
        val content = contentFactory.createContent(guiWindow.allContent, "文本工具", false)
        //idea的工具窗口上添加自定义窗口
        toolWindow.contentManager.addContent(content)
    }
}