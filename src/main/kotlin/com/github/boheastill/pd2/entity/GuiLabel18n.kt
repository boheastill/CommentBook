package com.github.boheastill.pd2.entity

enum class GuiLabel18n(var cn: String, var en: String) {
    label118n("Language", "语言"),
    label218n("功能", "Function"),
    label318n("参数", "arguments"),
    label418n("输出", "Output"),
    btlabel1("执行", "execute"),
    btlabel2("关闭", "close");

    fun getText(league: String): String {
        return when (league) {
            "cn" -> cn
            "en" -> en
            else -> cn
        }
    }
}