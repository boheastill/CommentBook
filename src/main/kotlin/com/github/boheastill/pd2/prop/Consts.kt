package com.github.boheastill.pd2.prop

import com.google.common.collect.Sets

/**
 * 常量
 *
 * @author wangchao
 * @date 2019/12/08
 */
object Consts {
    /**
     * 基础类型集
     */
    val BASE_TYPE_SET: Set<String> = Sets.newHashSet(
        "byte", "short", "int", "long", "char", "float",
        "double", "boolean"
    )

    /** 停止词  */
    val STOP_WORDS: Set<String> = Sets.newHashSet("the")

    /**
     * 腾讯翻译
     */
    const val TENCENT_TRANSLATOR = "腾讯翻译"

    /**
     * 百度翻译
     */
    const val BAIDU_TRANSLATOR = "百度翻译"

    /**
     * 有道翻译
     */
    const val YOUDAO_TRANSLATOR = "有道翻译"

    /**
     * 金山翻译
     */
    const val JINSHAN_TRANSLATOR = "金山翻译"

    /**
     * 关闭翻译
     */
    const val CLOSE_TRANSLATOR = "关闭（只使用自定义翻译）"
}