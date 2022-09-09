package com.github.boheastill.pd2.book.util;

import java.io.*;


public class FindPage {

    static Integer allPage = 18724;//vspage
    static String key = "章 寡人也";//vs key

    public static void main(String[] args) {

        writeTaskProcessNodeTimeFile(key, "D:\\down\\webGet", "JianDaoYiZhiShiHuangDi.txt");
    }

    public static boolean writeTaskProcessNodeTimeFile(String key, String filePath, String fileName) {
        Integer readSize = 0;
        Integer allSize = 0;
        try (BufferedWriter bufferedWriter = null) {
            String relFilePath = filePath + File.separator + fileName;
            File file = new File(relFilePath);
            BufferedReader buf = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = buf.readLine()) != null) {
//                System.out.println(line);
                if (line.indexOf(key) != -1) {
                    System.out.println(line);
                    readSize = allSize;
                }
                allSize += line.length();
            }
            double rate = (double) readSize / allSize;
            System.out.println(rate);
            double cataPage = rate * allPage;
            double verNum = (double) 11938 / 11928;//真实值/计算值，用于纠正计算值
            System.out.println("比例" + (int) cataPage);
            System.out.println("校正：" + (int) (cataPage * verNum));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
