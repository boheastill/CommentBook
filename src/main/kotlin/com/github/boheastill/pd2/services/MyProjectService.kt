package com.github.boheastill.pd2.services

import com.intellij.openapi.project.Project
import com.github.boheastill.pd2.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
