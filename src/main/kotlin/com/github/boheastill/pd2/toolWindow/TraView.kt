package com.github.boheastill.pd2.toolWindow

import java.awt.Dimension
import javax.swing.JDialog
import javax.swing.JPanel
import javax.swing.JTextArea

class TraView : JDialog() {
    var textArea1: JTextArea? = null
    var panel1: JPanel? = null

    init {
        //容器
        setContentPane(panel1)
        //窗口位置
        minimumSize = Dimension(300, 150)
        setLocationByPlatform(true)
        isModal = true
    }

}