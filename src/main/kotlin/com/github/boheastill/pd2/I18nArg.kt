package com.github.boheastill.pd2

enum class I18nArg {
    selectBoxValue_("导入文档", "Load a document");

    var ch: String
    var en: String

    constructor(ch: String, en: String) {
        this.ch = ch
        this.en = en
    }

    fun get(lang: String): String {
        return when (lang) {
            "ch" -> ch
            "en" -> en
            else -> ch
        }
    }
}