package com.github.boheastill.pd2.book.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CodeGennerate {
    public static boolean writejavaCode(File file, List<String> rawList) {
        boolean flag = false;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            file.createNewFile();
            for (String raw : rawList) {
                bufferedWriter.write(raw);
                bufferedWriter.newLine();
            }
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return flag;
        }
    }

    public static boolean writejavaCode(File file, List<String> rawList, String title, Boolean append) {
        boolean flag = false;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, append), StandardCharsets.UTF_8))) {
            file.createNewFile();
            bufferedWriter.write(title);
            bufferedWriter.newLine();
            if (rawList == null) {
                return false;
            }
            for (String raw : rawList) {
                bufferedWriter.write(raw);
                bufferedWriter.newLine();
            }
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return flag;
        }
    }


    public static List<String> codeTemp(String nocutfilename, String packName) {
        List<String> rawList = new ArrayList<>();
        rawList.add("package com.turing.engine.scripts." + packName + ".filter");
        rawList.add("");
        rawList.add("import com.turing.engine.scripts.node0101C50001.filter.SingleRegularPostProcess");
        rawList.add("import java.util.regex.Pattern");
        rawList.add("//import com.turing.vo.out.DependParamsVO");
        rawList.add("//import com.turing.vo.out.TaskElementVO");
        rawList.add("");
        rawList.add("/*默认走父类，用了子类在usePatternReg给父类的正则，最终返回java该正则在全文捞到的第一个值*/");
        rawList.add("");
        rawList.add("class " + nocutfilename + " extends SingleRegularPostProcess {");
        rawList.add("    @Override");
        rawList.add("    Pattern usePatternReg() {");
        rawList.add("        return ~/(?<=前置)[^,.，。]*?(?=后置)/");
        rawList.add("    }");
        rawList.add("/*    @Override");
        rawList.add("    List<TaskElementVO.DrawResultVO> modelDeal(List<TaskElementVO.DrawResultVO> resultVOs, DependParamsVO dependParams) {");
        rawList.add("        return resultVOs");
        rawList.add("    }*/");
        rawList.add("/*    @Override");
        rawList.add("    List<Map<String, String>> postProcess(String rawText, List<TaskElementVO.DrawResultVO> resultVOs, DependParamsVO dependParams) {");
        rawList.add("        return null");
        rawList.add("    }*/");
        rawList.add("}");
        return rawList;
    }

}
