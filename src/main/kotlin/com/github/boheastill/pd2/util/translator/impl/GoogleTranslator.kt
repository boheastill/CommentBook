package com.github.boheastill.pd2.util.translator.impl

import com.fasterxml.jackson.databind.JsonNode
import com.github.boheastill.pd2.util.HttpUtil
import com.github.boheastill.pd2.util.JsonUtil
import com.intellij.openapi.diagnostic.Logger
import org.apache.commons.lang3.StringUtils
import java.util.*

/**
 * 谷歌翻译
 *
 * @author wangchao
 * @date 2021/08/11
 */
class GoogleTranslator : AbstractTranslator() {
    public override fun translateEn2Ch(text: String): String? {
        return try {
            val response: JsonNode? =
                HttpUtil.get(java.lang.String.format(EN2CH_URL, HttpUtil.encode(text)))?.let { JsonUtil.fromJson(it) }
            Objects.requireNonNull(response)?.get(0)?.get(0)?.get(0)?.textValue()
        } catch (e: Exception) {
            LOGGER.error("请求谷歌翻译接口异常：请检查本地网络是否可连接外网，也有可能已经被谷歌限流", e)
            StringUtils.EMPTY
        }
    }

    public override fun translateCh2En(text: String): String? {
        return try {
            val response: JsonNode? =
                HttpUtil.get(java.lang.String.format(CH2EN_URL, HttpUtil.encode(text)))?.let { JsonUtil.fromJson(it) }
            Objects.requireNonNull(response)?.get(0)?.get(0)?.get(0)?.textValue()

        } catch (e: Exception) {
            LOGGER.error("请求谷歌翻译接口异常：请检查本地网络是否可连接外网，也有可能已经被谷歌限流", e)
            StringUtils.EMPTY
        }
    }

    companion object {
        private val LOGGER = Logger.getInstance(
            GoogleTranslator::class.java
        )

        /*
    1：通过网络请求
    http://translate.google.cn/translate_a/single?client=at&sl=en&tl=zh-CN&dt=t&q=google
    http://translate.google.cn/translate_a/single?client=gtx&sl=en&tl=zh-CN&dt=t&q=google

    dt参数的作用，这里说明一下，dt决定了最终返回的数据，可以包含多个dt参数，以下是dt的一些值：

    t - 源text的翻译
    at - 会额外返回一些近义词
    ex - examples
    ss - 如果翻译的是单个词，会返回与该词相关的动词、形容词、名词
    md - 如果翻译的是单个词，返回该词的定义
    rw - 组词
    bd
    rm
    dt - 似乎是设定返回数据的格式
    可以用这个API，优点是不需要tk，缺点是返回的内容比较简单
    还有如果次数限制了，client=at修改为：client=gtx
    一般at和t正常一点。其它格式可能无法正常得到翻译结果或正确的翻译结果

    返回：
    en2ch
    [[["我的宝贝","my baby",null,null,1]],null,"en",null,null,null,null,[]]
    ch2en
    [[["my darling","我的宝贝",null,null,10]],null,"zh-CN",null,null,null,null,[]]
     */
        private const val EN2CH_URL =
            "http://translate.google.cn/translate_a/single?client=gtx&sl=en&tl=zh-CN&dt=t&q=%s"
        private const val CH2EN_URL =
            "http://translate.google.cn/translate_a/single?client=gtx&sl=zh-CN&tl=en&dt=t&q=%s"
    }
}