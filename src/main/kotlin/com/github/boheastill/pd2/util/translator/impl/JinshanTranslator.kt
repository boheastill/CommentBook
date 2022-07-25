package com.github.boheastill.pd2.util.translator.impl

import com.fasterxml.jackson.annotation.JsonProperty
import com.github.boheastill.pd2.util.HttpUtil
import com.github.boheastill.pd2.util.JsonUtil
import org.apache.commons.lang3.StringUtils
import java.util.*

/**
 * 金山翻译
 *
 * @author wangchao
 * @date 2019/09/01
 */
class JinshanTranslator : AbstractTranslator() {
    public override fun translateEn2Ch(text: String): String? {
        return try {
            val response: JinshanResponse? = HttpUtil.get(java.lang.String.format(URL, HttpUtil.encode(text)))?.let {
                JsonUtil.fromJson(
                    it,
                    JinshanResponse::class.java
                )
            }
            Objects.requireNonNull(response)?.symbols!![0].parts!![0].means!![0]
        } catch (ignore: Exception) {
            StringUtils.EMPTY
        }
    }

    public override fun translateCh2En(text: String): String? {
        // TODO: 2020-8-27  
        return null
    }

    class JinshanResponse {
        @JsonProperty("word_name")
        var wordName: String? = null

        @JsonProperty("is_CRI")
        var isCRI: String? = null
        var exchange: Exchange? = null
        var symbols: List<Symbols>? = null
    }

    class Exchange {
        @JsonProperty("word_pl")
        var wordPl: List<String>? = null

        @JsonProperty("word_third")
        var wordThird: String? = null

        @JsonProperty("word_past")
        var wordPast: String? = null

        @JsonProperty("word_done")
        var wordDone: String? = null

        @JsonProperty("word_ing")
        var wordIng: String? = null

        @JsonProperty("word_er")
        var wordEr: String? = null

        @JsonProperty("word_est")
        var wordEst: String? = null
    }

    class Parts {
        var part: String? = null
        var means: List<String>? = null
    }

    class Symbols {
        @JsonProperty("ph_en")
        var phEn: String? = null

        @JsonProperty("ph_am")
        var phAm: String? = null

        @JsonProperty("ph_other")
        var phOther: String? = null

        @JsonProperty("ph_en_mp3")
        var phEnMp3: String? = null

        @JsonProperty("ph_am_mp3")
        var phAmMp3: String? = null

        @JsonProperty("ph_tts_mp3")
        var phTtsMp3: String? = null
        var parts: List<Parts>? = null
    }

    companion object {
        private const val URL =
            "http://dict-co.iciba.com/api/dictionary.php?key=1E55091D2F202FA617472001B3AF0D39&type=json&w=%s"
    }
}