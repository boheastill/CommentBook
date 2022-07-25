package com.github.boheastill.pd2.services.variable.impl

import com.github.boheastill.pd2.prop.EasyJavadocConfigComponent
import com.github.boheastill.pd2.services.variable.VariableGenerator
import com.intellij.openapi.components.ServiceManager
import com.intellij.psi.PsiElement

/**
 * @author [wangchao](mailto:wangchao.star@gmail.com)
 * @version 1.0.0
 * @since 2019-12-07 23:16:00
 */
class AuthorVariableGenerator : VariableGenerator {
    private val config = ServiceManager.getService(
        EasyJavadocConfigComponent::class.java
    ).state

    override fun generate(element: PsiElement?): String? {
        return config.author
    }
}