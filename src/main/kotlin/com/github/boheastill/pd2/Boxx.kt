package com.github.boheastill.pd2

import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.WindowEvent
import java.awt.event.WindowListener
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel

/**
 * @create: 2019-04-26 15:29
 * @author: Nick.Ma
 * @Desc:
 */
class Boxx : Thread(), ActionListener, WindowListener {
    private var tf1: TextField? = null
    private var tf2: TextField? = null
    private var tf3: TextField? = null
    private var tf4: TextField? = null
    private var tfStart: TextField? = null
    private var taLog //日志输出区域 不可以设置颜色相关
            : TextArea? = null
    private var jbtStart //开始按钮
            : JButton? = null

    override fun run() {
        //父窗体
        val jFrame = JFrame("青青河边草")
        // 创建按钮
        jbtStart = JButton("开始")
        jbtStart?.addActionListener(this)
        // 创建相关的文本域
        tf1 = TextField("08:00")
        tf2 = TextField("08:00")
        tf3 = TextField("08:00")
        tf4 = TextField("08:00")
        tfStart = TextField("09:00")
        taLog = TextArea()
        taLog!!.columns = 30
        taLog!!.rows = 150
        taLog!!.background = Color.CYAN
        taLog!!.font = Font("宋体", Font.BOLD, 16)
        taLog!!.isEditable = false

        // 创建相关的Label标签
        val label1 = JLabel("时间一")
        val label2 = JLabel("时间二")
        val label3 = JLabel("时间三")
        val label4 = JLabel("时间四")
        val labelStart = JLabel("开始时间")
        val labelLog = JLabel("输出日志信息")
        val labelTip1 =
            JLabel("<html>小马哥,特别制作:<br>1.按照格式填写时间<br>2.尽信工具不如无工具<br>3.总工时默认40H/W</html>")
        val labelTip2 =
            JLabel("<html>小马哥,简单说明:<br>1.时间1,2,3,4填写当天的实际工时<br>2.开始时间,目标天的上班时间<br>3.输出日志:EndTime2即目标天的下班时间</html>")

        //四个时间
        val panel1 = JPanel(GridLayout(2, 4))
        panel1.add(label1)
        panel1.add(label2)
        panel1.add(label3)
        panel1.add(label4)
        panel1.add(tf1)
        panel1.add(tf2)
        panel1.add(tf3)
        panel1.add(tf4)

        //开始时间
        val panel2 = JPanel(GridLayout(2, 1))
        panel2.add(labelStart, BorderLayout.CENTER)
        panel2.add(tfStart, BorderLayout.CENTER)
        //日志输出
        val panel3 = JPanel(GridLayout(2, 1))
        panel3.add(labelLog)
        panel3.add(taLog)

        //开始按钮及左右信息
        val panel4 = JPanel(GridLayout(1, 3))
        panel4.add(labelTip1)
        panel4.add(jbtStart)
        panel4.add(labelTip2)

        //上级布局
        val panel22 = JPanel(GridLayout(4, 1))
        panel22.add(panel1)
        panel22.add(panel2)
        panel22.add(panel3)
        panel22.add(panel4)
        jFrame.setLayout(BorderLayout())
        jFrame.add(panel22, BorderLayout.CENTER)

        // 初始化JFrame窗口
        jFrame.setLocation(800, 300)
        jFrame.setSize(600, 500)
        jFrame.setBackground(Color.darkGray)
        jFrame.setResizable(true)
        jFrame.setVisible(true)

    }

    override fun actionPerformed(view: ActionEvent) {
        if (view.getSource() === jbtStart) {
            val day1 = tf1!!.text
            val day2 = tf2!!.text
            val day3 = tf3!!.text
            val day4 = tf4!!.text
            val startTime = tfStart!!.text
            if (day1.isNotEmpty() && day2.isNotEmpty() && day3.isNotEmpty() && day4.isNotEmpty() && startTime.isNotEmpty()) {
                calcTime(day1, day2, day3, day4)
                whatTime(startTime)
            }
        }
    }

    private fun calcTime(day1: String, day2: String, day3: String, day4: String) {
        val day5 = ""
        var h1 = 0
        var h2 = 0
        var h3 = 0
        var h4 = 0
        var h5 = 0
        var m1 = 0
        var m2 = 0
        var m3 = 0
        var m4 = 0
        var m5 = 0
        if (day1.isNotEmpty()) {
            val splitTime1 = day1.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            h1 = splitTime1[0].toInt()
            m1 = splitTime1[1].toInt()
        }
        if (day2.isNotEmpty()) {
            val splitTime1 = day2.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            h2 = splitTime1[0].toInt()
            m2 = splitTime1[1].toInt()
        }
        if (day3.isNotEmpty()) {
            val splitTime1 = day3.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            h3 = splitTime1[0].toInt()
            m3 = splitTime1[1].toInt()
        }
        if (day4.isNotEmpty()) {
            val splitTime1 = day4.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            h4 = splitTime1[0].toInt()
            m4 = splitTime1[1].toInt()
        }
        if (day5.isNotEmpty()) {
            val splitTime1 = day5.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            h5 = splitTime1[0].toInt()
            m5 = splitTime1[1].toInt()
        }
        val totalH = h1 + h2 + h3 + h4 + h5
        val totalM = m1 + m2 + m3 + m4 + m5
        val totalSum = totalH + totalM / 60f
        totalDiff = totalTime - totalSum
        taLog!!.append("totalSum:$totalSum")
        taLog!!.append("\n")
        taLog!!.append("totalDif:" + totalDiff)
        taLog!!.append("\n")
    }

    private fun whatTime(startTime: String) {
        var startH = 0
        var startM = 0
        if (startTime.isNotEmpty()) {
            val splitTime1 = startTime.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            startH = splitTime1[0].toInt()
            startM = splitTime1[1].toInt()
        }
        val resM = totalDiff % 1
        val resH = (totalDiff / 1).toInt()
        var v = resM * 60 + startM
        if (v >= 60) {
            v = v - 60
            startH++
        }
        val endH = startH + resH + 1
        val endM = v
        taLog!!.append("xx:" + resM + "hh:" + resH)
        taLog!!.append("\n")
        taLog!!.append("endH:" + endH + "endM:" + endM)
        taLog!!.append("\n")
        taLog!!.append("EndTime:$endH:$endM")
        taLog!!.append("\n")
        val ceilH = Math.ceil(endM.toDouble()).toInt()
        val endTime = String.format("%02d", ceilH)
        taLog!!.append("EndTime2:$endH:$endTime")
        taLog!!.append("\n")
    }

    override fun windowOpened(e: WindowEvent) {}
    override fun windowClosing(e: WindowEvent) {
        System.exit(0)
    }

    override fun windowClosed(e: WindowEvent) {
        System.exit(0)
    }

    override fun windowIconified(e: WindowEvent) {}
    override fun windowDeiconified(e: WindowEvent) {}
    override fun windowActivated(e: WindowEvent) {}
    override fun windowDeactivated(e: WindowEvent) {}

    companion object {
        private const val totalTime = 40
        private var totalDiff = 0.0f
    }
}
fun main (){
    var boxx = Boxx()
}