package com.github.boheastill.pd2.util

import com.intellij.openapi.diagnostic.Logger
import org.apache.commons.codec.Charsets
import org.apache.commons.lang3.StringUtils
import org.apache.http.client.config.RequestConfig
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.utils.HttpClientUtils
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.util.stream.Collectors

/**
 * @author wangchao
 * @date 2019/09/01
 */
object HttpUtil {
    private val LOGGER = Logger.getInstance(
        HttpUtil::class.java
    )
    private const val CONNECT_TIMEOUT = 300
    private const val SOCKET_TIMEOUT = 1000
    operator fun get(url: String): String? {
        if (StringUtils.isBlank(url)) {
            return null
        }
        var result: String? = null
        var httpclient: CloseableHttpClient? = null
        var response: CloseableHttpResponse? = null
        try {
            httpclient = HttpClients.createDefault()
            val httpGet = HttpGet(url)
            httpGet.config =
                RequestConfig.custom().setSocketTimeout(SOCKET_TIMEOUT).setConnectTimeout(CONNECT_TIMEOUT).build()
            response = httpclient.execute(httpGet)
            result = EntityUtils.toString(response.entity, StandardCharsets.UTF_8)
        } catch (e: IOException) {
            LOGGER.warn("请求" + url + "异常", e)
        } finally {
            HttpClientUtils.closeQuietly(response)
            HttpClientUtils.closeQuietly(httpclient)
        }
        return result
    }

    operator fun get(url: String, params: Map<String, Any>): String? {
        var url = url
        if (StringUtils.isBlank(url)) {
            return null
        }
        val paramStr = params.entries.stream()
            .map { (key, value): Map.Entry<String, Any> ->
                key + "=" + encode(
                    value.toString()
                )
            }.collect(Collectors.joining("&"))
        url = if (url.contains("?")) "$url&$paramStr" else "$url?$paramStr"
        return HttpUtil[url]
    }

    fun encode(word: String): String {
        return try {
            URLEncoder.encode(word, Charsets.UTF_8.name())
        } catch (e: UnsupportedEncodingException) {
            LOGGER.warn("url转义失败,word=$word", e)
            StringUtils.EMPTY
        }
    }
}