package com.github.boheastill.pd2.book;


import com.github.boheastill.pd2.book.service.CataChapParseI;
import com.github.boheastill.pd2.book.service.PCA;
import com.github.boheastill.pd2.book.util.CodeGennerate;
import com.github.boheastill.pd2.book.vo.Catalogue;
import com.github.boheastill.pd2.book.vo.Chapter;
import com.github.boheastill.pd2.book.vo.ProcessStatu;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.github.boheastill.pd2.book.util.ChangeToPinYinJP.getPyName;
import static com.github.boheastill.pd2.book.util.IORecord.recordTime;
import static com.github.boheastill.pd2.book.util.IORecord.save2File;

;

/**
 * 多线程问题
 * 1.目录分页、目录、章节哪些需要多线程？
 * 2.如何实现 现有的从某章节开始保存？
 * 3.章节顺序乱序问题
 * 4.用哈希表写能否保证线程一致性？
 * 5.除了fututer处，其他地方如何阻塞，等待线程执行完毕。
 * 建议：
 * 开新分支，大幅重写
 * 工具化 循环的 多线程 与 适时阻塞处理业务
 * 方案 新文件格式、map排序、多文件合并、数据库预保存
 */
public class BookDownAPP {
    private static final CataChapParseI cataChapParseI = new PCA();
    public static final String SAVE_BASE_FILE = "D:\\down\\webGet\\";
    public static final String TRAIL = ".txt";
    public static List<String> TIME_RECORD = new LinkedList<>();


    public static void main(String[] args) throws IOException {
        String fileName = "我导演了玄武门事变";
        String chapterStartKey = "";
        new BookDownAPP().bookStart(fileName, chapterStartKey);
    }

    public void bookStart(String fileName, String chapterStartKey) throws IOException {
        //1.参数与初始化
        List<String> pyNameList = getPyName(fileName);//TODO 多音字校验
        String pyName = pyNameList.get(0);
        String baseUrl = cataChapParseI.getBaseUrl();
        String simpUrl = cataChapParseI.getSimpUrl();

        findinit(pyName);
        // 获得目录列表
        List<Catalogue> splitCataList = cataChapParseI.getCataList(baseUrl + simpUrl);
        // 获得完整目录//TODO 异步
        LinkedList<Catalogue> catalogueList = new LinkedList<>();
        for (Catalogue splitCata : splitCataList) {
            String url = splitCata.getPageUrl();
            cataChapParseI.getFullCata(url, catalogueList, baseUrl);
            recordTime(ProcessStatu.CATA_END);
        }
        // 正文
        StringBuilder sb = eachPageSave(catalogueList, pyName, chapterStartKey);
        findEnd(sb, pyName);
    }

    private Boolean ignore = true;

    //TODO 一次保存
    public StringBuilder eachPageSave(List<Catalogue> catalogueList, String filePyName, String chapterStartKey) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (Catalogue catalogue : catalogueList) {
            recordTime(ProcessStatu.PAGE_START);
            if ("".equals(chapterStartKey) || catalogue.getName().contains(chapterStartKey)) {
//                System.out.println("匹配到:" + catalogue.getName());
                ignore = false;
            }
            if (ignore) {
//                System.out.println("跳过:" + catalogue.getName());
                continue;
            }
            System.out.println("保存:" + catalogue.getName());
            //保存单页逻辑
            Chapter chapter = cataChapParseI.getChapter(catalogue.getPageUrl());
            //记录问题页
            if (!chapter.getNormal()) {
                sb.append(chapter).append("\n");
            }
            recordTime(ProcessStatu.SAVE_START);
            save2File(chapter, filePyName);
        }

        return sb;
    }


    private void findEnd(StringBuilder sb, String filePyName) throws IOException {
        recordTime(ProcessStatu.TASK_END);
        //保存报告文件
        String pathname = SAVE_BASE_FILE + filePyName + "_报告" + TRAIL;
        File file = new File(pathname);
        if (file.createNewFile()) {
            CodeGennerate.writejavaCode(file, TIME_RECORD, "问题：" + sb + "\n报告：\n", false);
        }
    }

    /*前置处理*/
    private void findinit(String filePyName) {
        recordTime(ProcessStatu.TASK_START);
        File oldFile = new File(SAVE_BASE_FILE + filePyName + TRAIL);
        if (oldFile.exists()) {
            boolean delete = oldFile.delete();
            System.out.println("delete" + delete);
        }
        File oldFileT = new File(SAVE_BASE_FILE + filePyName + "_报告" + TRAIL);
        if (oldFileT.exists()) {
            boolean delete = oldFileT.delete();
            System.out.println("delete" + delete);
        }
    }
}
