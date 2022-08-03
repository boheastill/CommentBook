package com.github.boheastill.pd2.toolWindow

import com.intellij.openapi.wm.ToolWindow
import java.awt.event.ActionEvent
import javax.swing.*

/*类不是组件，只是存储的合集区域。类的字段才是组件，*/
class ToolWinView(toolWindow: ToolWindow) {

    var allContent: JPanel? = null//容器
    private var selectBox: JComboBox<String>? = null//选择框
    private var exeButton: JButton? = null//执行按钮
    private var inputField: JTextField? = null//输入
    private var outPutPane: JTextPane? = null//输出
    private var leagueBox: JComboBox<String>? = null//选择框


    //初始化
    init {
        //toolWindow.hide(null)
        //toolWindow.title = "隐藏"
        //1.初始化参数
        toolWindow.title = "设置面板"

        //2.初始化组件：
        // 下拉框
        var selectList =
            "向前翻页（+数量,数量自定待完成）、向后翻页（+数量）、安全模式、搜索文本页码、跳转指定页/比例、存/取书签、加载指定路径txt文档（待完成）、下载（待完成）、A"
        var split = selectList.split("、")
        for (i in split) {
            selectBox?.addItem(i)
        }

        //3.初始化监听：
        //执行按钮监听
        exeButton?.addActionListener { e: ActionEvent? ->
            var outPutStr = StringBuilder()
            var select = selectBox?.selectedItem.toString()
            var input = inputField?.text.toString()
            outPutStr.append(("select值为$select\n参数为 $input\n开始调用业务逻辑"))
            outPutPane?.text = outPutStr.toString()
        }



    }

    //标签，自排版,可以设置文本和文本前面的图标，增减字段要和form的字段对应
    //按钮：
    //  为null时候，走form的设置,代码的监听修改会生效
    //  如果new，显示form文件，但初始化的监听不生效


}