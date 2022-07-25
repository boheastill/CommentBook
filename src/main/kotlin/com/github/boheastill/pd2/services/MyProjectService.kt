package com.github.boheastill.pd2.services

import com.github.boheastill.pd2.MyBundle
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.project.Project


class MyProjectService(private val myProject: Project) {

    init {
        println(MyBundle.message("projectService", myProject.name))
    }

    fun someServiceMethod(parameter: String?) {
        // do some more stuff
    }

    companion object {
        //看大部分源码，一般都会创建getInstance()来获取实例。
        val instance: MyProjectService
            get() = ServiceManager.getService(MyProjectService::class.java)
    }
}