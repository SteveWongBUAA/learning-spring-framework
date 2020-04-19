package com.vava.designpattern.visitor;

/**
 * @author Steve
 * Created on 2020-04
 */
public class PdfFile extends ResourceFile {
    public PdfFile(String filepath) {
        super(filepath);
    }

    @Override
    public void accept(Extractor extractor) {
        extractor.extract2txt(this);
    }


}
