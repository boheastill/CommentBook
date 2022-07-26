package com.github.boheastill.pd2.util

import java.util.regex.Pattern

object util {
    /**
     * 传入：文本，正则
     * 返回值用法：返回值长度0，说明没有匹配到值。长度为几，说明从前到后匹配到多少个值。每个值都是装着 start end text 的map.
     * note： 已防止数组越界问题，防止零长匹配导致死循环。
     * * 零长度结果举例：正则 (?<=张).*(?=三) ，在文本”张三“中，匹配到位置为【1，1），值为空字符串”“。
     */
    fun regDeal(pendingText: String?, pattern: Pattern): List<Map<String, String>> {
        val regResultMapList: MutableList<Map<String, String>> = ArrayList()
        //判空
        if (pendingText == null) {
            return regResultMapList
        }
        var m_idx = 0
        val matcher = pattern.matcher(pendingText)
        while (m_idx <= pendingText.length && matcher.find(m_idx)) {
            val regTextMap: MutableMap<String, String> = HashMap()
            regTextMap["regText"] = matcher.group()
            val regStart = matcher.start().toString()
            regTextMap["regStart"] = regStart
            val regEnd = matcher.end().toString()
            regTextMap["regEnd"] = regEnd
            regResultMapList.add(regTextMap)
            m_idx = matcher.end() + if (matcher.group().length == 0) 1 else 0
        }
        return regResultMapList
    }

    fun regText(pendingText: String?, pattern: Pattern): String? {
        if (pendingText == null) {
            return null
        }
        val matcher = pattern.matcher(pendingText)
        if (matcher.find()) {
            return matcher.group()
        }
        return null
    }


}