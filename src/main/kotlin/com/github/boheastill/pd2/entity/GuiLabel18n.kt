package com.github.boheastill.pd2.entity

enum class GuiLabel18n(var cn: String, var en: String) {
    label118n("Language", "语言"),
    label218n("功能", "Function"),
    label318n("参数", "arguments"),
    label418n("输出", "Output"),
    btlabel1("确认", "OK "),
    btlabel2("取消", "Cancel");

    fun getText(league: String): String {
        return when (league) {
            "cn" -> cn
            "en" -> en
            else -> cn
        }
    }
}