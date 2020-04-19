package com.vava.designpattern.visitor;

/**
 * @author Steve
 * Created on 2020-04
 */
public class Extractor {
    public void extract2txt(PdfFile pdfFile) {
        System.out.println("pdf extract");
    }

    public void extract2txt(WordFile wordFile) {
        System.out.println("word extract");
    }

    public void extract2txt(ExcelFile excelFile) {
        System.out.println("excel extract");
    }


}
