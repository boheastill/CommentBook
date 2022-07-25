package com.github.boheastill.pd2.util

/**
 * 语言跑龙套
 * 语言工具
 *
 * @author wangchao
 * @version 1.0.0
 * @date 2020/02/12
 * @since 2019-12-08 03:16:00
 */
object LanguageUtil {
    /**
     * 是否是中文
     *
     * @param c c
     * @return boolean
     */
    private fun isChinese(c: Char): Boolean {
        val ub = Character.UnicodeBlock.of(c)
        return ub === Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub === Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || ub === Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub === Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B || ub === Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub === Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS || ub === Character.UnicodeBlock.GENERAL_PUNCTUATION
    }

    /**
     * 是否含有中文
     *
     * @param str str
     * @return boolean
     */
    fun isChinese(str: String): Boolean {
        val ch = str.toCharArray()
        for (c in ch) {
            if (isChinese(c)) {
                return true
            }
        }
        return false
    }

    /**
     * 是否是中文字符串
     *
     * @param str str
     * @return boolean
     */
    fun isAllChinese(str: String): Boolean {
        val ch = str.toCharArray()
        for (c in ch) {
            if (!isChinese(c)) {
                return false
            }
        }
        return true
    }
}