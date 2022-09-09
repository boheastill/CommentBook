package com.github.boheastill.pd2.book.service;


import cn.i623.myspringutil.fun.book.vo.Catalogue;
import cn.i623.myspringutil.fun.book.vo.Chapter;
import cn.i623.myspringutil.fun.book.vo.ProcessStatu;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.util.LinkedList;
import java.util.List;

import static cn.i623.myspringutil.fun.book.util.IORecord.recordTime;
import static cn.i623.myspringutil.fun.book.util.Web.getResult;

@SuppressWarnings("unused")
public class MobileA implements CataChapParseI {

    //    aim wlb,gtb 7 1h
//    学习以检验为真理，个人时间第一优先级。
//    外出以英语和参与为主要目标
//    工作以下次要求为本次目标
    @Override
    public String getBaseUrl() {
        return "https://m.soxs.cc/";
    }

    /**
     * 相对网址，开头有/
     */
    @Override
    public String getSimpUrl() {
        return "/booktxt/85241200116/";
    }

    @Override
    public List<Catalogue> getCataList(String url) {
        List<Catalogue> rawList = new LinkedList<>();

        System.out.println(url);
        String result = getResult(url);

        Document doc = Jsoup.parse(result);
        Element body = doc.body();
        Node node1 = body.childNode(1);
//        System.out.println(node1);
        Node node = node1.childNode(12).childNode(2).childNode(1);
//        正文
        for (int i = 0; i < node.childNodes().size(); i++) {
            Node node2 = node.childNode(i);
//            System.out.println(node2);
            Node node3 = node2.childNode(0);
            String href = node2.attributes().get("value");
            Catalogue catalogue = new Catalogue();
            catalogue.setPageUrl(href);
            catalogue.setName(node3.toString());
            rawList.add(catalogue);
        }
        return rawList;
    }

    @Override
    public void getFullCata(String url, LinkedList<Catalogue> catalogue, String baseUrl) {


        String result = getResult(url);
        Document doc = Jsoup.parse(result);
        Element body = doc.body();
        Node node1 = body.childNode(1);
        Node node = node1.childNode(10);
//        System.out.println(body);
//        正文
        for (int i = 0; i < node.childNodes().size(); i++) {
            Node node2 = node.childNode(i);
            if (!"".equals(node2.toString().trim())) {
                Node node3 = node2.childNode(0).childNode(0);
                String href = node2.childNode(0).attributes().get("href");
                Catalogue chapterTitle = new Catalogue();
                chapterTitle.setPageUrl(baseUrl + href);
                chapterTitle.setName(node3.toString());
                catalogue.add(chapterTitle);
            }
        }
    }

    @Override
    public Chapter getChapter(String url) {
        Chapter chapter = new Chapter();
        int count = 0;
        List<String> rawList = new LinkedList<>();

        String result = getResult(url);
        recordTime(ProcessStatu.PRISE_START);
        // 使用 jsoup 解析
        Document doc = Jsoup.parse(result);
        Element body = doc.body();
//        标题
        Node node1 = body.childNode(3);
        String s1 = node1.childNode(0).toString();
//        正文
        Node node2 = body.childNode(5);
        for (int i = 0; i < node2.childNodes().size() - 1; i++) {
            String s = node2.childNode(i).childNode(0).toString();
//            System.out.println(s);
            count += s.length();
            rawList.add(s);
        }
        //写入
        if (rawList.size() > 20 && count > 1000) {
            chapter.setNormal(true);
        }
        chapter.setTitle(s1);
        chapter.setRawList(rawList);
        chapter.setCount(count);
        chapter.setRaw(rawList.size());
        return chapter;
    }
}
