package com.github.boheastill.pd2.util

import java.util.regex.Pattern

object RegUtil {
    /*传入文本，返回符合正则的文本列表*/
    fun regTextList(pendingText: String, pattern: Pattern): List<String> {

//        Pattern pattern = Pattern.compile(".*?[,;，。：；）)]");
        val regResultMapList: MutableList<String> = ArrayList()
        var m_idx = 0
        val matcher = pattern.matcher(pendingText)
        while (m_idx <= pendingText.length && matcher.find(m_idx)) {
            regResultMapList.add(matcher.group())
            m_idx = matcher.end() + if (matcher.group().length == 0) 1 else 0
        }
        return regResultMapList
    }
}