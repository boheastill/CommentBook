package com.github.boheastill.pd2.util

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.intellij.openapi.diagnostic.Logger
import org.apache.commons.lang3.StringUtils
import java.io.IOException
import java.util.*

/**
 * @author wangchao
 * @date 2019/09/01
 */
object JsonUtil {
    private val LOGGER = Logger.getInstance(
        JsonUtil::class.java
    )
    private val objectMapper = ObjectMapper()
    fun <T> toJson(obj: T): String {
        return if (Objects.isNull(obj)) {
            ""
        } else try {
            objectMapper.writeValueAsString(obj)
        } catch (e: JsonProcessingException) {
            LOGGER.warn("json序列化异常", e)
            ""
        }
    }

    fun <T> fromJson(json: String, tClass: Class<T>?): T? {
        return if (StringUtils.isBlank(json)) {
            null
        } else try {
            objectMapper.readValue(json, tClass)
        } catch (e: IOException) {
            LOGGER.warn("json序列化异常,json=$json", e)
            null
        }
    }

    fun <T> fromJson(json: String, tTypeReference: TypeReference<T>?): T? {
        return if (StringUtils.isBlank(json)) {
            null
        } else try {
            objectMapper.readValue(json, tTypeReference)
        } catch (e: IOException) {
            LOGGER.warn("json序列化异常,json=$json", e)
            null
        }
    }

    fun fromJson(json: String): JsonNode? {
        return if (StringUtils.isBlank(json)) {
            null
        } else try {
            objectMapper.readTree(json)
        } catch (e: IOException) {
            LOGGER.warn("json序列化异常,json=$json", e)
            null
        }
    }
}