package com.github.boheastill.pd2.prop

import java.util.*

/**
 * 持久化配置文件
 *
 * @author wangchao
 * @date 2019/08/25
 */
class EasyJavadocConfiguration {
    /**
     * 作者
     */
    var author = "admin"

    /**
     * 日期格式
     */
    var dateFormat = "yyyy/MM/dd"

    /**
     * 属性是否使用简单模式
     */
    var simpleFieldDoc = false

    /**
     * 属性是否使用简单模式
     */
    var methodReturnType = LINK_RETURN_TYPE

    /**
     * 翻译方式
     */
    var translator: String? = "有道翻译"

    /**
     * 百度app id
     */
    var appId: String? = null

    /**
     * 百度密钥
     */
    var token: String? = null

    /**
     * 腾讯secretKey
     */
    var secretKey: String? = null

    /**
     * 腾讯secretId
     */
    var secretId: String? = null

    /**
     * 单词映射
     */
    private var wordMap: SortedMap<String, String>? = TreeMap()

    /**
     * 类模板配置
     */
    private var classTemplateConfig: TemplateConfig? = TemplateConfig()

    /**
     * 方法模板配置
     */
    private var methodTemplateConfig: TemplateConfig? = TemplateConfig()

    /**
     * 属性模板配置
     */
    private var fieldTemplateConfig: TemplateConfig? = TemplateConfig()

    /** 批量生成是否生成类注释  */
    var genAllClass: Boolean? = null

    /** 批量生成是否生成方法注释  */
    var genAllMethod: Boolean? = null

    /** 批量生成是否生成属性注释  */
    var genAllField: Boolean? = null

    /** 批量生成是否递归内部类  */
    var genAllInnerClass: Boolean? = null
    fun reset() {
        author = "admin"
        dateFormat = "yyyy/MM/dd"
        simpleFieldDoc = false
        translator = "有道翻译"
        appId = null
        token = null
        secretKey = null
        secretId = null
        wordMap = TreeMap()
        classTemplateConfig = TemplateConfig()
        methodTemplateConfig = TemplateConfig()
        fieldTemplateConfig = TemplateConfig()
    }

    /**
     * 模板配置
     */
    class TemplateConfig {
        /**
         * 是否默认的
         */
        var isDefault = true

        /**
         * 模板
         */
        var template = ""

        /**
         * 自定义映射
         */
        var customMap: Map<String, CustomValue>

        init {
            customMap = TreeMap()
        }
    }

    /**
     * 自定义值
     */
    class CustomValue {
        /**
         * 类型
         */
        var type: VariableType? = null

        /**
         * 值
         */
        var value: String? = null

        constructor() {}
        constructor(type: VariableType?, value: String?) {
            this.type = type
            this.value = value
        }
    }

    /**
     * 变量类型
     */
    enum class VariableType(val desc: String) {
        /**
         * 固定值
         */
        STRING("固定值"),

        /**
         * groovy脚本
         */
        GROOVY("Groovy脚本");

        companion object {
            fun fromDesc(desc: String): VariableType? {
                for (value in values()) {
                    if (value.desc == desc) {
                        return value
                    }
                }
                return null
            }
        }
    }

    fun getWordMap(): SortedMap<String, String> {
        if (wordMap == null) {
            wordMap = TreeMap()
        }
        return wordMap!!
    }

    fun setWordMap(wordMap: SortedMap<String, String>?) {
        this.wordMap = wordMap
    }

    fun getClassTemplateConfig(): TemplateConfig {
        if (classTemplateConfig == null) {
            classTemplateConfig = TemplateConfig()
        }
        return classTemplateConfig!!
    }

    fun setClassTemplateConfig(classTemplateConfig: TemplateConfig?) {
        this.classTemplateConfig = classTemplateConfig
    }

    fun getMethodTemplateConfig(): TemplateConfig {
        if (methodTemplateConfig == null) {
            methodTemplateConfig = TemplateConfig()
        }
        return methodTemplateConfig!!
    }

    fun setMethodTemplateConfig(methodTemplateConfig: TemplateConfig?) {
        this.methodTemplateConfig = methodTemplateConfig
    }

    fun getFieldTemplateConfig(): TemplateConfig {
        if (fieldTemplateConfig == null) {
            fieldTemplateConfig = TemplateConfig()
        }
        return fieldTemplateConfig!!
    }

    fun setFieldTemplateConfig(fieldTemplateConfig: TemplateConfig?) {
        this.fieldTemplateConfig = fieldTemplateConfig
    }

    val isCodeMethodReturnType: Boolean
        get() = CODE_RETURN_TYPE == methodReturnType
    val isLinkMethodReturnType: Boolean
        get() = LINK_RETURN_TYPE == methodReturnType

    companion object {
        /** 方法返回值为code类型  */
        const val CODE_RETURN_TYPE = "code"

        /** 方法返回值为link类型  */
        const val LINK_RETURN_TYPE = "link"
    }
}