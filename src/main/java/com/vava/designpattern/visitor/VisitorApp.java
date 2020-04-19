package com.vava.designpattern.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Steve
 * Created on 2020-04
 *
 * Java函数重载的规则：不是通过运行时的参数决定的，而是通过编译时声明的类型
 */
public class VisitorApp {
    public static void main(String[] args) {
        List<ResourceFile> resourceFiles = listAllResourceFiles();
        Extractor extractor = new Extractor();
        for (ResourceFile resourceFile: resourceFiles) {
            resourceFile.accept(extractor);
        }

    }

    private static List<ResourceFile> listAllResourceFiles() {
        List<ResourceFile> resourceFiles = new ArrayList<>();
        resourceFiles.add(new PdfFile("./pdf"));
        resourceFiles.add(new WordFile("./word"));
        resourceFiles.add(new ExcelFile("./excel"));
        return resourceFiles;
    }

}
