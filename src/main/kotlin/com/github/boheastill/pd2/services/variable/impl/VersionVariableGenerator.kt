package com.github.boheastill.pd2.services.variable.impl

import com.github.boheastill.pd2.services.variable.VariableGenerator
import com.intellij.psi.PsiElement

/**
 * @author [wangchao](mailto:wangchao.star@gmail.com)
 * @version 1.0.0
 * @since 2019-12-07 23:17:00
 */
class VersionVariableGenerator : VariableGenerator {
    override fun generate(element: PsiElement?): String? {
        return "1.0.0"
    }
}