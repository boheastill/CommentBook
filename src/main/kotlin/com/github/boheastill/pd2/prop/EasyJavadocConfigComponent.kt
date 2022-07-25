package com.github.boheastill.pd2.prop

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil
import java.util.*

/**
 * @author wangchao
 * @date 2019/08/25
 */
@State(name = "easyJavadoc", storages = [Storage("easyJavadoc.xml")])
class EasyJavadocConfigComponent : PersistentStateComponent<EasyJavadocConfiguration?> {
    private var configuration: EasyJavadocConfiguration? = null

    override fun loadState(state: EasyJavadocConfiguration) {
        XmlSerializerUtil.copyBean(state, Objects.requireNonNull<Any?>(getState()))
    }

    companion object {
        const val DEFAULT_DATE_FORMAT = "yyyy/MM/dd"
    }


    override fun getState(): EasyJavadocConfiguration {
        if (configuration == null) {
            configuration = EasyJavadocConfiguration()
            configuration!!.author = System.getProperty("user.name")
            configuration!!.dateFormat = DEFAULT_DATE_FORMAT
            configuration!!.simpleFieldDoc = true
            configuration!!.methodReturnType = EasyJavadocConfiguration.LINK_RETURN_TYPE
            configuration!!.setWordMap(TreeMap())
            configuration!!.translator = Consts.YOUDAO_TRANSLATOR
            configuration!!.setClassTemplateConfig(EasyJavadocConfiguration.TemplateConfig())
            configuration!!.setMethodTemplateConfig(EasyJavadocConfiguration.TemplateConfig())
            configuration!!.setFieldTemplateConfig(EasyJavadocConfiguration.TemplateConfig())
        }
        return configuration as EasyJavadocConfiguration
    }


}