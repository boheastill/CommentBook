package com.github.boheastill.pd2.util.translator

/**
 * 翻译
 *
 * @author wangchao
 * @date 2019/11/25
 */
interface Translator {
    /**
     * 英译中
     *
     * @param text 文本
     * @return [String]
     */
    fun en2Ch(text: String?): String?

    /**
     * 中译英
     *
     * @param text 文本
     * @return [String]
     */
    fun ch2En(text: String?): String?

    /**
     * 清除缓存
     */
    fun clearCache()
}