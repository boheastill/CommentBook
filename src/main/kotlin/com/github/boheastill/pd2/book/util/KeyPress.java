package com.github.boheastill.pd2.book.util;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class KeyPress {
    public static void pressKey(Robot robot, int keyvalue) {
        robot.keyPress(keyvalue);
        robot.keyRelease(keyvalue);
    }

    public static void pressKeyCtrlAlt(Robot robot) {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ALT);
//        robot.keyRelease(KeyEvent.VK_CONTROL);
//        robot.keyRelease(KeyEvent.VK_ALT);
    }

    public static void pressKeyWithShift(Robot robot, int keyvalue) {
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(keyvalue);
        robot.keyRelease(keyvalue);
        robot.keyRelease(KeyEvent.VK_SHIFT);
    }

    public static void closeApplication(Robot robot) {
// pressKey(robot, KeyEvent.VK_ALT);
// pressKey(robot, KeyEvent.VK_F4);
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_F4);
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.keyRelease(KeyEvent.VK_F4);
//for linux.
// robot.keyPress(KeyEvent.VK_ALT);
// robot.keyPress(KeyEvent.VK_W);
// robot.keyRelease(KeyEvent.VK_ALT);
// robot.keyRelease(KeyEvent.VK_W);
        robot.keyPress(KeyEvent.VK_N);
        robot.keyRelease(KeyEvent.VK_N);
    }//1111222333444455556666777888999
///////////1
    public static void main(String[] args) throws IOException {
        try {
            Robot robot = new Robot();
            pressKeyCtrlAlt(robot);
//robot.keyPress(KeyEvent.VK_SPACE);
        } catch (AWTException e) {//zzz
            e.printStackTrace();
        }
    }

    /*
    public static void main(String[] args) throws IOException {
        try {
            Robot robot = new Robot();
            Runtime.getRuntime().exec("notepad");
// For linux.
//Runtime.getRuntime().exec("gedit");
//定义5秒的延迟以便你打开notepad 哈哈
// Robot 开始写
            robot.delay(3000);
            for (int i = 0; i < 2; i++) {
                pressKeyWithShift(robot, KeyEvent.VK_H);
                pressKey(robot, KeyEvent.VK_I);
                pressKey(robot, KeyEvent.VK_SPACE);
//pressKeyWithShift(robot, KeyEvent.VK_H);
                pressKeyWithShift(robot, KeyEvent.VK_I);
                pressKey(robot, KeyEvent.VK_SPACE);
                pressKey(robot, KeyEvent.VK_A);
                pressKey(robot, KeyEvent.VK_M);
                pressKey(robot, KeyEvent.VK_SPACE);
                pressKey(robot, KeyEvent.VK_T);
                pressKey(robot, KeyEvent.VK_H);
                pressKey(robot, KeyEvent.VK_E);
                pressKey(robot, KeyEvent.VK_SPACE);
                pressKey(robot, KeyEvent.VK_J);
                pressKey(robot, KeyEvent.VK_A);
                pressKey(robot, KeyEvent.VK_V);
                pressKey(robot, KeyEvent.VK_A);
                pressKey(robot, KeyEvent.VK_SPACE);
                pressKey(robot, KeyEvent.VK_R);
                pressKey(robot, KeyEvent.VK_O);
                pressKey(robot, KeyEvent.VK_B);
                pressKey(robot, KeyEvent.VK_O);
                pressKey(robot, KeyEvent.VK_T);
// VK_ENTER
                pressKey(robot, KeyEvent.VK_ENTER);
//pressKey(robot, KeyEvent.);
            }
//            closeApplication(robot);
//robot.keyPress(KeyEvent.VK_SPACE);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }*/
}\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
