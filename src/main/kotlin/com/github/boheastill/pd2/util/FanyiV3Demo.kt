package com.github.boheastill.pd2.util

import com.google.gson.JsonParser
import org.apache.http.NameValuePair
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.HttpClients
import org.apache.http.message.BasicNameValuePair
import org.apache.http.util.EntityUtils
import org.slf4j.LoggerFactory
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.regex.Pattern

object FanyiV3Demo {
    private val logger = LoggerFactory.getLogger(FanyiV3Demo::class.java)
    private const val YOUDAO_URL = "https://openapi.youdao.com/api"
    private const val APP_KEY = "1915461d1c15cfa2"
    private const val APP_SECRET = "YjMTQXwzX0TgCaCLVfm7360ElTzJVX4N"
    val stringStringHashMap = TextExcel2Map.ydLeagueMap()

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val q = "Auth cache not set in the context"
        val s = traslationSimple(q)
        println(s)
    }

    /*中英互译
     * 传入文本无中文，译为中文。
     * 含中文，译为英文。
     * */
    @Throws(IOException::class)
    fun traslationSimple(q: String): String {
        //识别传入文本是否无汉字
        val strings =
            RegUtil.regTextList(q, Pattern.compile("[\\u4e00-\\u9fa5]+"))
        val params: MutableMap<String, String?> =
            HashMap()
        val leagurFrom: String?
        val leagueTO: String?
        if (strings.size == 0) {
            leagurFrom = stringStringHashMap?.get("英文")
            leagueTO = stringStringHashMap?.get("中文")
        } else {
            leagurFrom = stringStringHashMap?.get("中文")
            leagueTO = stringStringHashMap?.get("英文")
        }
        println("从" + leagurFrom + "到" + leagueTO)
        params["from"] = leagurFrom
        params["to"] = leagueTO
        val salt = System.currentTimeMillis().toString()
        params["signType"] = "v3"
        val curtime = (System.currentTimeMillis() / 1000).toString()
        params["curtime"] = curtime
        val signStr =
            APP_KEY + truncate(q) + salt + curtime + APP_SECRET
        val sign = getDigest(signStr)
        params["appKey"] = APP_KEY
        params["q"] = q
        params["salt"] = salt
        params["sign"] = sign
        params["vocabId"] = ""
        return requestForHttp(YOUDAO_URL, params)
    }

    @Throws(IOException::class)
    fun requestForHttp(url: String?, params: Map<String, String?>): String {
        var anwser = ""

        /** 创建HttpClient  */
        val httpClient = HttpClients.createDefault()

        /** httpPost  */
        val httpPost = HttpPost(url)
        val paramsList: MutableList<NameValuePair> = ArrayList()
        val it = params.entries.iterator()
        while (it.hasNext()) {
            val (key, value) = it.next()
            paramsList.add(BasicNameValuePair(key, value))
        }
        httpPost.entity = UrlEncodedFormEntity(paramsList, "UTF-8")
        val httpResponse = httpClient.execute(httpPost)
        try {
            val contentType = httpResponse!!.getHeaders("Content-Type")
            logger.info("Content-Type:" + contentType[0].value)
            if ("audio/mp3" == contentType[0].value) {
                //如果响应是wav
                val httpEntity = httpResponse.entity
                val baos = ByteArrayOutputStream()
                httpResponse.entity.writeTo(baos)
                val result = baos.toByteArray()
                EntityUtils.consume(httpEntity)
                if (result != null) { //合成成功
                    val file = "合成的音频存储路径" + System.currentTimeMillis() + ".mp3"
                    byte2File(result, file)
                }
            } else {
                /** 响应不是音频流，直接显示结果  */
                val httpEntity = httpResponse.entity
                val json = EntityUtils.toString(httpEntity, "UTF-8")
                EntityUtils.consume(httpEntity)
                logger.info(json)

                // json 字符串转 json对象
                val returnData = JsonParser.parseString(json).asJsonObject
                anwser = returnData.getAsJsonArray("translation")[0].asString
            }
        } finally {
            try {
                httpResponse?.close()
            } catch (e: IOException) {
                logger.info("## release resouce error ##$e")
            }
        }
        return anwser
    }

    /**
     * 生成加密字段
     */
    fun getDigest(string: String?): String? {
        if (string == null) {
            return null
        }
        val hexDigits = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F')
        val btInput = string.toByteArray(StandardCharsets.UTF_8)
        return try {
            val mdInst = MessageDigest.getInstance("SHA-256")
            mdInst.update(btInput)
            val md = mdInst.digest()
            val j = md.size
            val str = CharArray(j * 2)
            var k = 0
            for (byte0 in md) {
                str[k++] = hexDigits[byte0.toInt() ushr 4 and 0xf]
                str[k++] = hexDigits[byte0.toInt() and 0xf]
            }
            String(str)
        } catch (e: NoSuchAlgorithmException) {
            null
        }
    }

    /**
     * @param result 音频字节流
     * @param file   存储路径
     */
    private fun byte2File(result: ByteArray, file: String) {
        val audioFile = File(file)
        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(audioFile)
            fos.write(result)
        } catch (e: Exception) {
            logger.info(e.toString())
        } finally {
            if (fos != null) {
                try {
                    fos.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun truncate(q: String?): String? {
        if (q == null) {
            return null
        }
        val len = q.length
        var result: String
        return if (len <= 20) q else q.substring(0, 10) + len + q.substring(len - 10, len)
    }
}