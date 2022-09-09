package com.github.boheastill.pd2.book.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Web {
    /**
     * 传入地址，返回get结果，无值会重试3次
     */
    public static String getResult(String url) {
        String result;
        int i = 0;
        //如果返回长度0，重试10次 todo PARAM加时间戳避免缓存
        do {
            result = sendGet(url, "");
            i++;
        } while (result.length() == 0 && i < 3);
        return result;
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        StringBuilder result = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            String urlNameString = url + "?" + param;
//            System.out.println("读取响应：" + urlNameString);
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.155 Safari/537.36");
            // 建立实际的连接
            connection.connect();

            // 获取所有响应头字段
//            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
////                System.out.println(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            //获取网址编码格式
            String contentType = connection.getContentType();
            contentType = contentType.substring(contentType.indexOf("charset=") + 8);
//            System.out.println("contentType:" + contentType);
            //设置编码格式
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), contentType));


            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result.toString();
    }
}
