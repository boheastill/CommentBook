package com.github.boheastill.pd2.toolWindow

import FunActionI18n
import com.github.boheastill.pd2.entity.GuiLabel18n
import com.github.boheastill.pd2.services.WriterService
import com.intellij.openapi.components.ServiceManager
import getObjByText
import java.awt.Dimension
import java.awt.event.ItemEvent
import java.awt.event.KeyEvent
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.*

class ConView : JDialog() {
    //rooot
    var contentPane: JPanel? = null

    //语言选择
    var label1: JLabel? = null
    var rb1: JRadioButton? = null
    var rb2: JRadioButton? = null


    //选择事件
    var label2: JLabel? = null
    var comboBox1: JComboBox<String>? = null

    //输入框
    var label3: JLabel? = null
    var inputFiled: JTextField? = null

    //输出框
    var label4: JLabel? = null
    var outPutPane: JTextPane? = null

    //按钮行
    var buttonOK: JButton? = null
    var buttonCancel: JButton? = null
    var infoField: JTextField? = null

    //字段，用户最新操作
    var action: FunActionI18n? = FunActionI18n.NEXT//不切换默认后翻页
    private val writerService: WriterService = ServiceManager.getService(WriterService::class.java)

    init {
        //容器
        setContentPane(contentPane)
        //窗口位置
        minimumSize = Dimension(500, 450)
        setLocationByPlatform(true)
        isModal = true
        //窗口事件
        getRootPane().defaultButton = buttonOK
        buttonOK!!.addActionListener { onOK() }
        buttonCancel!!.addActionListener { onCancel() }
        //默认全局中文
        //语言行初始化，按用户的选择更新语言
        initLanguageRaw()
        //保存用户选择的action
        comboBox1?.addItemListener({ e: ItemEvent? ->
            if (e?.stateChange == ItemEvent.SELECTED) {
                val eit = e.item.toString()
                action = getObjByText(eit)
            }
        })
        // 点击 X 时调用 onCancel()
        defaultCloseOperation = DO_NOTHING_ON_CLOSE
        addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent) {
                onCancel()
            }
        })

        // 遇到 ESCAPE 时调用 onCancel()
        contentPane!!.registerKeyboardAction(
            { onCancel() }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT
        )

    }

    private fun initLanguageRaw() {
        //按钮合并组
        var group = ButtonGroup()
        group.add(rb1)
        group.add(rb2)

        displayAllNameByLanguage("cn")
        //按钮监听后改变文本
        rb1?.addActionListener {
            displayAllNameByLanguage("cn")
        }
        rb2?.addActionListener {
            displayAllNameByLanguage("en")
        }
    }

    private fun displayAllNameByLanguage(league: String) {
        rb1?.text = "中文"
        rb2?.text = "English"
        //标签
        label1?.text = GuiLabel18n.label118n.getText(league);
        label2?.text = GuiLabel18n.label218n.getText(league);
        label3?.text = GuiLabel18n.label318n.getText(league);
        label4?.text = GuiLabel18n.label418n.getText(league);
        buttonOK?.text = GuiLabel18n.btlabel1.getText(league);
        buttonCancel?.text = GuiLabel18n.btlabel2.getText(league);
        //先清空comboBox1中的元素
        comboBox1?.removeAllItems()
        for (i in FunActionI18n.values()) {
            comboBox1?.addItem(i.getText(league))
        }
    }

    private fun onOK() {
        outPutPane?.text = writerService.getTextByActionFiled(action, inputFiled?.text?.trim())
        var bookInfo = writerService.getInfo()
        infoField?.text = bookInfo
    }


    private fun onCancel() {
        // 必要时在此处添加您的代码
        dispose()
    }

}