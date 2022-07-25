package com.github.boheastill.pd2.util.translator.impl

import com.fasterxml.jackson.annotation.JsonProperty
import com.github.boheastill.pd2.prop.EasyJavadocConfigComponent
import com.github.boheastill.pd2.util.HttpUtil
import com.github.boheastill.pd2.util.HttpUtil.encode
import com.github.boheastill.pd2.util.JsonUtil.fromJson
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.diagnostic.Logger
import org.apache.commons.codec.digest.DigestUtils
import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.StringUtils
import java.util.*

/**
 * 百度翻译
 *
 * @author wangchao
 * @date 2019/09/01
 */
class BaiduTranslator : AbstractTranslator() {
    private val config = ServiceManager.getService(
        EasyJavadocConfigComponent::class.java
    ).state

    public override fun translateEn2Ch(text: String): String? {
        try {
            return get(text)
        } catch (e: InterruptedException) {
            Thread.currentThread().interrupt()
        } catch (e: Exception) {
            LOGGER.error("请求百度翻译接口异常：请检查本地网络是否可连接外网，也有可能被百度限流", e)
        }
        return StringUtils.EMPTY
    }


    public override fun translateCh2En(text: String): String? {
        try {
            return get(text)
        } catch (e: InterruptedException) {
            Thread.currentThread().interrupt()
        } catch (e: Exception) {
            LOGGER.error("请求百度翻译接口异常：请检查本地网络是否可连接外网，也有可能被百度限流", e)
        }
        return StringUtils.EMPTY
    }

    @Throws(InterruptedException::class)
    private operator fun get(text: String): String? {
        var result: String? = ""
        for (i in 0..9) {
            val salt = RandomStringUtils.randomNumeric(16)
            val sign = DigestUtils.md5Hex(config.appId + text + salt + config.token)
            val eText = encode(text)
            val response =
                fromJson(HttpUtil[String.format(URL, config.appId, salt, sign, eText)]!!, BaiduResponse::class.java)
            if (response == null || "54003" == response.errorCode) {
                Thread.sleep(500)
            } else {
                result = Objects.requireNonNull(response).transResult!![0].dst
                break
            }
        }
        return result
    }

    class BaiduResponse {
        @JsonProperty("error_code")
        var errorCode: String? = null

        @JsonProperty("error_msg")
        var errorMsg: String? = null
        var from: String? = null
        var to: String? = null

        @JsonProperty("trans_result")
        var transResult: List<TransResult>? = null
    }

    class TransResult {
        var src: String? = null
        var dst: String? = null
    }

    companion object {

        private val LOGGER = Logger.getInstance(
            BaiduTranslator::class.java
        )
        private const val URL =
            "http://api.fanyi.baidu.com/api/trans/vip/translate?from=auto&to=auto&appid=%s&salt=%s&sign=%s&q=%s"
    }
}
