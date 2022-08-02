enum class FunBoxValueI18n(var cn: String, var en: String) {
    PRE("向前翻页（+数量,数量自定待完成）", "previous page（+number, number to be completed）"),
    NEXT("向后翻页（+数量）", "next page（+number）"),
    SAFE("安全模式", "safe mode"),
    SEARCH("搜索文本页码", "search text page number"),
    JUMPTO("跳转指定页/比例", "jump to specified page/ratio"),
    MARK_SET("存书签", "save bookmark"),
    MARK_GET("取书签", "load bookmark"),
    LOAD("加载指定路径txt文档", "load specified path txt document"),
    DOWN("下载（待完成）", "download（to be completed）");

    fun getText(league: String): String {
        return when (league) {
            "cn" -> cn
            "en" -> en
            else -> cn
        }
    }

}

fun getObjByText(text: String): FunBoxValueI18n {
    //根据FunBoxValueI18n的cn或en参数值获取对象
    return FunBoxValueI18n.values().first { it.getText("cn") == text || it.getText("en") == text }
}
