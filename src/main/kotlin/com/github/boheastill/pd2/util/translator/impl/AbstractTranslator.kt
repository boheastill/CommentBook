package com.github.boheastill.pd2.util.translator.impl

import com.github.boheastill.pd2.util.translator.Translator
import java.util.concurrent.ConcurrentHashMap
import kotlin.collections.set

/**
 * @author wangchao
 * @date 2020/08/28
 */
abstract class AbstractTranslator : Translator {
    private val en2chCacheMap: MutableMap<String, String> = ConcurrentHashMap()
    private val ch2enCacheMap: MutableMap<String, String> = ConcurrentHashMap()
    override fun en2Ch(text: String?): String? {
        if (text == null || text.length == 0) {
            return ""
        }
        var res = en2chCacheMap[text]
        if (res != null && res.length > 0) {
            return res
        }
        res = translateEn2Ch(text)
        if (res != null && res.length > 0) {
            en2chCacheMap[text] = res
        }
        return res!!
    }

    override fun ch2En(text: String?): String? {
        if (text == null || text.length == 0) {
            return ""
        }
        var res = ch2enCacheMap[text]
        if (res != null && res.length > 0) {
            return res
        }
        res = translateCh2En(text)
        if (res != null && res.length > 0) {
            ch2enCacheMap[text] = res
        }
        return res!!
    }

    /**
     * 清除缓存
     */
    override fun clearCache() {
        en2chCacheMap.clear()
        ch2enCacheMap.clear()
    }

    /**
     * 中译英
     *
     * @param text 文本
     * @return [String]
     */
    protected abstract fun translateCh2En(text: String): String?

    /**
     * 英译中
     *
     * @param text 文本
     * @return [String]
     */
    protected abstract fun translateEn2Ch(text: String): String?
}