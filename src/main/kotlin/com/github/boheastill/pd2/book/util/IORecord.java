package com.github.boheastill.pd2.book.util;


import com.github.boheastill.pd2.book.vo.Chapter;
import com.github.boheastill.pd2.book.vo.ProcessStatu;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static cn.i623.myspringutil.fun.book.BookDownAPP.*;
import static com.github.boheastill.pd2.book.BookDownAPP.*;

public class IORecord {

    /*
     * 传入：流程节点,时间（指定）
     * **/
    public static void recordTime(ProcessStatu processNode) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");
        String nowTime = df.format(LocalDateTime.now());
//        timeMap.put(processNode, nowTime);
        TIME_RECORD.add(processNode.getName() + "\t" + nowTime);
    }


    public static void save2File(Chapter chapter, String filePyName) throws IOException {
        String pathname = SAVE_BASE_FILE + filePyName + TRAIL;
//        String pathname = BASE_FILE + page.getTitle() + TRAIL;
        File file = new File(pathname);
        if (file.exists()) {
//            System.out.println("    存在");
            CodeGennerate.writejavaCode(file, chapter.getRawList(), chapter.getTitle(), true);
        } else {
            System.out.println("创建文件" + filePyName);
            if (file.createNewFile()) {
                CodeGennerate.writejavaCode(file, chapter.getRawList(), chapter.getTitle(), false);
            }
        }///////////////////////////////////
    }

}
