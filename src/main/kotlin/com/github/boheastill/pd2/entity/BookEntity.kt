package com.github.boheastill.pd2.entity

import com.github.boheastill.pd2.services.textStr
import com.github.boheastill.pd2.util.groupDiv
import java.io.File

class BookEntity {
    var textSource: String = ""
    var contentLenth: Int = 0
    var maxPageNum: Int = 0
    var curDisNum: Int = 0
        set(value) {
            field = value
            println("展示页码: $field 并缓存")
        }
    var textSourceLenth: Int = 0

    //书签
    var markMap: HashMap<String, Int> = HashMap()
    var isNewLoad: Boolean = true    //仅加载第一次触发为真
    var textName: String = ""

    init {
        loadBook(textStr, 100)
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
        curDisNum = pageNum
        var startPageIdx = (pageNum - 1) * contentLenth
        var endPageIdx = pageNum * contentLenth

        val startIndex = startPageIdx
        val endIndex = Math.min(endPageIdx, textSourceLenth)

        val pageText = textSource.substring(startIndex, endIndex)
        return pageText
    }


    /*显示向后指定页*/
    fun nextPageNum(pageNum: Int, size: Int): String {
        var textByPageNum = getTextByPageNum(pageNum + size)
        return textByPageNum
    }

    /*显示标记页*/
    fun displaySignNum(): String {
        var textByPageNum = getTextByPageNum(curDisNum)
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
        var findpageNum = groupDiv(textSource.indexOf(findStr), contentLenth)

        return getTextByPageNum(findpageNum)
    }

    fun loadBookByPath(filePath: String) {
        val file = File(filePath)
        val fileReader = file.bufferedReader()
        var text = fileReader.readText()
        fileReader.close()
        //filePath中截取文件名
        val fileName = filePath.substring(filePath.lastIndexOf("/") + 1)
        //初始化
        loadBook(text, 100, fileName.substring(0, fileName.lastIndexOf(".")))
    }


    /*传入 完整字符 和 每页上限 */
    private fun loadBook(text: String, contentLenth: Int, textName: String = "默认") {
        this.isNewLoad = true
        this.contentLenth = contentLenth
        this.textSource = text
        this.textSourceLenth = text.length
        this.curDisNum = 1
        this.maxPageNum = groupDiv(textSource.length, contentLenth)
        this.textName = textName
        println("初始化完成")
    }

    fun getTextBookInfo(): String {
        return "curNum:$curDisNum,maxNum:$maxPageNum,contentLenth:$contentLenth,textName:$textName"
    }

}
