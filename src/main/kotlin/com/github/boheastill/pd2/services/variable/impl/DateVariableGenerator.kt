package com.github.boheastill.pd2.services.variable.impl

import com.github.boheastill.pd2.prop.EasyJavadocConfigComponent
import com.github.boheastill.pd2.services.variable.VariableGenerator
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.diagnostic.Logger
import com.intellij.psi.PsiElement
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * @author [wangchao](mailto:wangchao.star@gmail.com)
 * @version 1.0.0
 * @since 2019-12-07 23:15:00
 */
class DateVariableGenerator : VariableGenerator {
    private val config = ServiceManager.getService(
        EasyJavadocConfigComponent::class.java
    ).state

    override fun generate(element: PsiElement?): String? {
        return try {
            LocalDateTime.now().format(DateTimeFormatter.ofPattern(config.dateFormat))
        } catch (e: Exception) {
            LOGGER.error("您输入的日期格式不正确，请到配置中修改类相关日期格式！", e)
            LocalDateTime.now().format(DateTimeFormatter.ofPattern(EasyJavadocConfigComponent.DEFAULT_DATE_FORMAT))
        }
    }

    companion object {
        private val LOGGER = Logger.getInstance(
            DateVariableGenerator::class.java
        )
    }
}