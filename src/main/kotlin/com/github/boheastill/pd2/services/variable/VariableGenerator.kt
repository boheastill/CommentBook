package com.github.boheastill.pd2.services.variable

import com.intellij.psi.PsiElement

/**
 * 变量生成器
 *
 * @author wangchao
 * @date 2019/12/07
 */
interface VariableGenerator {
    /**
     * 生成
     *
     * @param element 元素
     * @return [String]
     */
    fun generate(element: PsiElement?): String?
}