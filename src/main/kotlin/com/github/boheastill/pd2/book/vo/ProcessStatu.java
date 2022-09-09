package com.github.boheastill.pd2.book.vo;

public enum ProcessStatu {
    TASK_START("任务开始"), TASK_END("任务结束"),
    DOWNLOAD_START("下载开始"), DOWNLOAD_END("下载结束"),
    PRISE_START("解析处理开始"), PRISE_END("解析处理结束"),
    TEST1("测试节点1"), TEST2("测试节点2"),
    PAGE_START("网络获取章节开始"), PAGE_END("网络获取章节结束"),
    CATA_START("目录开始"), CATA_END("目录结束"),
    CATA_ALLSPLIT_START("目录完整分页开始"), CATA_ALLSPLIT_END("目录完整分页结束"),
    SAVE_START("保存开始"), SAVE_END("保存结束");
//        _START("开始"), _END("结束"),

    private final String name;

    ProcessStatu(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }//
}