package com.github.boheastill.pd2.entity

import com.github.boheastill.pd2.services.ReaderService
import com.github.boheastill.pd2.services.textStr
import com.github.boheastill.pd2.util.groupDiv
import java.io.File

class BookEntity {
    var souceText: String
    var pageContenSize: Int
    var maxPageNum: Int
    var disChachNum: Int = 1
        set(value) {
            field = value
            println("展示页码: $field 并缓存")
        }
    var souceTextLength: Int

    //书签
    var markMap: HashMap<String, Int> = HashMap()


    init {
        this.souceText = textStr
        this.souceTextLength = textStr.length
        this.pageContenSize = 100
        this.maxPageNum = groupDiv(souceText.length, pageContenSize)
//        this.curPageNum = 1
    }

    /**
     * 传入页码，获取对应文本
     * 头尾成环，会缓存当前页码
     * 页码溢出:返回提示文本
     * */
    fun getTextByPageNum(opageNum: Int): String {
        var pageNum = opageNum
        if (opageNum < 1) {
            pageNum = maxPageNum
        } else if (opageNum > maxPageNum) {
            pageNum = 1
        }
        //缓存已展示页码
        disChachNum = pageNum
        var startPageIdx = (pageNum - 1) * pageContenSize
        var endPageIdx = pageNum * pageContenSize

        val startIndex = startPageIdx
        val endIndex = Math.min(endPageIdx, souceTextLength)

        val pageText = souceText.substring(startIndex, endIndex)
        return pageText
    }


    /*显示向后指定页*/
    fun nextPageNum(pageNum: Int, size: Int): String {
        var textByPageNum = getTextByPageNum(pageNum + size)
        return textByPageNum
    }

    /*显示标记页*/
    fun displaySignNum(): String {
        var textByPageNum = getTextByPageNum(disChachNum)
        return textByPageNum
    }

    /*显示向前指定页*/
    fun previousPageNum(pageNum: Int, size: Int): String {
        var textByPageNum = getTextByPageNum(pageNum - size)
        return textByPageNum
    }

    /*
    * 传入字符串，
    * 返回字符串所在的页内容
    * 搜不到返回首页
    * */
    fun find(findStr: String): String {
        var findpageNum = groupDiv(souceText.indexOf(findStr), pageContenSize)

        return getTextByPageNum(findpageNum)
    }

    fun loadFile2String(filePath: String) {

        //加载文件

        val file = File(filePath)
        val fileReader = file.bufferedReader()
        var text = fileReader.readText()
        fileReader.close()
        //初始化
        this.souceText = text
        this.souceTextLength = text.length
        this.pageContenSize = 100
        this.maxPageNum = groupDiv(souceText.length, pageContenSize)
        //输出信息
        println("文本长度: ${souceText.length}")
        println("页面长度: $pageContenSize")
        println("页数: $maxPageNum")

        ReaderService().display = true
    }
}
