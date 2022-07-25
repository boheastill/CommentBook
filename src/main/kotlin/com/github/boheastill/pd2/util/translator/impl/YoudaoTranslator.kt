package com.github.boheastill.pd2.util.translator.impl

import com.github.boheastill.pd2.util.HttpUtil
import com.github.boheastill.pd2.util.HttpUtil.encode
import com.github.boheastill.pd2.util.HttpUtil.get
import com.github.boheastill.pd2.util.JsonUtil
import com.github.boheastill.pd2.util.JsonUtil.fromJson
import com.intellij.openapi.diagnostic.Logger
import org.apache.commons.lang3.StringUtils
import java.util.*
import java.util.stream.Collectors

/**
 * 有道翻译
 *
 * @author wangchao
 * @date 2019/09/01
 */
class YoudaoTranslator : AbstractTranslator() {
    public override fun translateEn2Ch(text: String): String? {
        return try {
            val response: YoudaoResponse? =
                HttpUtil.get(java.lang.String.format(EN2CH_URL, HttpUtil.encode(text)))?.let {
                    JsonUtil.fromJson(
                        it,
                        YoudaoResponse::class.java
                    )
                }
            Objects.requireNonNull(response)?.translateResult!!.stream()
                .map { translateResults: List<TranslateResult> ->
                    translateResults.stream().map { obj: TranslateResult -> obj.tgt }
                        .collect(Collectors.joining(" "))
                }
                .collect(Collectors.joining("\n"))
        } catch (e: Exception) {
            LOGGER.error("请求有道翻译接口异常：请检查本地网络是否可连接外网，也有可能已经被有道限流", e)
            StringUtils.EMPTY
        }
    }

    public override fun translateCh2En(text: String): String? {
        return try {
            val response: YoudaoResponse? =
                HttpUtil.get(java.lang.String.format(CH2EN_URL, HttpUtil.encode(text)))?.let {
                    JsonUtil.fromJson(
                        it,
                        YoudaoResponse::class.java
                    )
                }
            Objects.requireNonNull(response)?.translateResult!!.stream()
                .map { translateResults: List<TranslateResult> ->
                    translateResults.stream().map { obj: TranslateResult -> obj.tgt }
                        .collect(Collectors.joining(" "))
                }
                .collect(Collectors.joining("\n"))
        } catch (e: Exception) {
            LOGGER.error("请求有道翻译接口异常", e)
            StringUtils.EMPTY
        }
    }

    class YoudaoResponse {
        var type: String? = null
        var errorCode = 0
        var elapsedTime = 0
        var translateResult: List<List<TranslateResult>>? = null
    }

    class TranslateResult {
        var src: String? = null
        var tgt: String? = null
    }

    companion object {
        private val LOGGER = Logger.getInstance(
            YoudaoTranslator::class.java
        )
        private const val CH2EN_URL = "http://fanyi.youdao.com/translate?&doctype=json&type=ZH_CN2EN&i=%s"
        private const val EN2CH_URL = "http://fanyi.youdao.com/translate?&doctype=json&type=EN2ZH_CN&i=%s"
    }
}