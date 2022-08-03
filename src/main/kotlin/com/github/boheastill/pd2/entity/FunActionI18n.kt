enum class FunActionI18n(var cn: String, var en: String) {
    NEXT("向后翻页（+页数）", "next page（can specify number）"),
    PRE("向前翻页(+可指定页数，默认1)", "Previous page(specify the number, default 1)"),
    LOAD("加载指定路径txt文档", "load specified path txt document"),
    SEARCH("搜索文本页码", "search text page number"),
    JUMPTO("跳转指定页/比例", "jump to specified page/ratio"),
    MARK_SET("存书签", "save bookmark"),
    MARK_GET("取书签", "load bookmark"),
    RANDOM("安全模式", "Random mode"),
    DOWN("下载（待完成）", "download（to be completed）");

    fun getText(league: String): String {
        return when (league) {
            "cn" -> cn
            "en" -> en
            else -> cn
        }
    }

}

fun getObjByText(text: String): FunActionI18n {
    //根据FunBoxValueI18n的cn或en参数值获取对象
    return FunActionI18n.values().first { it.getText("cn") == text || it.getText("en") == text }
}
