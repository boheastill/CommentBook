package com.github.boheastill.pd2.book.util;


import com.github.stuxuhai.jpinyin.ChineseHelper;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;

import java.util.LinkedList;
import java.util.List;

public class ChangeToPinYinJP {


    public static List<String> getPyName(String str) {
        String pyName = ChangeToPinYinJP.changeToTuoFeng(str);
        //todo 多音字问题
        LinkedList<String> strings = new LinkedList<>();
        strings.add(pyName);
        return strings;
    }

    /**
     * 驼峰命名
     *
     * @param pinYinStr 需转换的汉字
     * @return 拼音字符串
     */

    public static String changeToTuoFeng(String pinYinStr) {
        String tempStr;
        String separator = "#";
        StringBuilder sb = new StringBuilder();
        try {
            tempStr = PinyinHelper.convertToPinyinString(pinYinStr, separator, PinyinFormat.WITHOUT_TONE);
            String[] pinyinArray = tempStr.split(separator);
            for (String py : pinyinArray) {
//                System.out.println(py);
                if (py.length() > 0) {
                    String s = py.substring(0, 1).toUpperCase() + py.substring(1);
                    sb.append(s);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 转换为有声调的拼音字符串
     *
     * @param pinYinStr 汉字
     * @return 有声调的拼音字符串
     */

    public String changeToMarkPinYin(String pinYinStr) {
        String tempStr = null;
        try {
            tempStr = PinyinHelper.convertToPinyinString(pinYinStr, " ", PinyinFormat.WITH_TONE_MARK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempStr;
    }

    /**
     * 转换为数字声调字符串
     *
     * @param pinYinStr 需转换的汉字
     * @return 转换完成的拼音字符串
     */

    public String changeToNumberPinYin(String pinYinStr) {
        String tempStr = null;
        try {
            tempStr = PinyinHelper.convertToPinyinString(pinYinStr, " ", PinyinFormat.WITH_TONE_NUMBER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempStr;
    }

    /**
     * 转换为不带音调的拼音字符串
     *
     * @param pinYinStr 需转换的汉字
     * @return 拼音字符串
     */

    public String changeToTonePinYin(String pinYinStr) {
        String tempStr = null;
        try {
            tempStr = PinyinHelper.convertToPinyinString(pinYinStr, " ", PinyinFormat.WITHOUT_TONE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempStr;
    }

    /**
     * 转换为每个汉字对应拼音首字母字符串
     *
     * @param pinYinStr 需转换的汉字
     * @return 拼音字符串
     */

    public String changeToGetShortPinYin(String pinYinStr) {
        String tempStr = null;
        try {
            tempStr = PinyinHelper.getShortPinyin(pinYinStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempStr;
    }

    /**
     * 检查汉字是否为多音字
     *
     * @param pinYinStr 需检查的汉字
     * @return true 多音字，false 不是多音字
     */

    public boolean checkPinYin(char pinYinStr) {
        boolean check = false;
        try {
            check = PinyinHelper.hasMultiPinyin(pinYinStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    /**
     * 简体转换为繁体
     *
     * @param pinYinStr
     * @return
     */

    public String changeToTraditional(String pinYinStr) {
        String tempStr = null;
        try {
            tempStr = ChineseHelper.convertToTraditionalChinese(pinYinStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempStr;
    }

    /**
     * 繁体转换为简体
     *
     * @param pinYinSt
     * @return
     */

    public String changeToSimplified(String pinYinSt) {
        String tempStr = null;
        try {
            tempStr = ChineseHelper.convertToSimplifiedChinese(pinYinSt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempStr;
    }

}