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

public class PCA implements CataChapParseI {
    @Override
    public List<Catalogue> getCataList(String url) {
        List<Catalogue> rawList = new LinkedList<>();
        Catalogue catalogue1 = new Catalogue("单页目录", url);
        rawList.add(catalogue1);
        return rawList;
    }

    @Override
    public void getFullCata(String url, LinkedList<Catalogue> catalogue, String baseUrl) {
        String result = getResult(url);
        Document doc = Jsoup.parse(result);
        Element body = doc.body();
        List<Node> nodes = body.childNode(0).childNode(1).childNode(0).childNode(5).childNode(7).childNodes();
        for (Node cate : nodes) {
            if ("".equals(cate.toString().trim())) {
                continue;
            }
            Node x = cate.childNode(0);
//            System.out.println(x);
            //得到节点的值
            String href = x.attributes().get("href");
            String name = x.childNode(0).toString();
            Catalogue chapterTitle = new Catalogue(name, baseUrl + href);
            catalogue.add(chapterTitle);
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
//        System.out.println(body);
        Node node = body.childNode(0).childNode(1).childNode(3);
        //        标题
        String title = node.childNode(1).childNode(1).childNode(0).toString();
//        正文
        List<Node> rawNodelist = node.childNode(3).childNodes();//rawNodelist
        //遍历node2
        for (int i = 1; i < rawNodelist.size(); i++) {
            String text = rawNodelist.get(i).toString().trim();//去掉首尾空格
            if ("<br>".equals(text)) {  //如果是换行
                continue;
            }
            //跳过html注释
            if (text.startsWith("<!--")) {
                continue;
            }
            count += text.length();
            rawList.add(text);
        }

        //统计字数
        if (rawList.size() > 20 && count > 1000) {
            chapter.setNormal(true);
        }
        chapter.setTitle(title);
        chapter.setRawList(rawList);
        chapter.setCount(count);
        chapter.setRaw(rawList.size());
        return chapter;
    }


    /**
     * ROOT网址，结尾无/
     */
    @Override
    public String getBaseUrl() {
        return "https://www.qu-la.com";
//        return "http://www.biquge.com.tw";
    }

    /**
     * 相对网址，开头有/
     */
    @Override
    public String getSimpUrl() {
        return "/booktxt/85241200116/";
    }

}
