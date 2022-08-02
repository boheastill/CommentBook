package com.github.boheastill.pd2.toolWindow

import FunBoxValueI18n
import com.github.boheastill.pd2.entity.GuiLabel18n
import getObjByText
import java.awt.Dimension
import java.awt.event.ItemEvent
import java.awt.event.KeyEvent
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.*

class ConfigView : JDialog() {
    var contentPane: JPanel? = null

    var buttonOK: JButton? = null
    var buttonCancel: JButton? = null

    var comboBox1: JComboBox<String>? = null

    var textField1: JTextField? = null
    var button1: JButton? = null


    var label1: JLabel? = null
    var rb1: JRadioButton? = null
    var rb2: JRadioButton? = null

    var label2: JLabel? = null
    var label3: JLabel? = null
    var label4: JLabel? = null
    var textField2: JTextField? = null

    //保存动作
    var action: FunBoxValueI18n? = FunBoxValueI18n.PRE

    init {
        //1.容器设置
        setContentPane(contentPane)
        //展示到指定位置
        setLocationByPlatform(true)
        minimumSize = Dimension(450, 300)
        isModal = true
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
            { onCancel() },
            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
            JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT
        )


    }

    private fun initLanguageRaw() {
        //给标签赋值
//        label1?.text = "语言\\Language"
//        rb1!!.text = "中文"
//        rb2!!.text = "English"
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
        for (i in FunBoxValueI18n.values()) {
            comboBox1?.addItem(i.getText(league))
        }
    }

    private fun onOK() {
        // 在此处添加您的代码
        dispose()
    }

    private fun onCancel() {
        // 必要时在此处添加您的代码
        dispose()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val dialog = ConfigView()
            dialog.pack()
            dialog.isVisible = true
            System.exit(0)
        }
    }
}