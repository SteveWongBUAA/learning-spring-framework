package com.vava.designpattern.visitor;

/**
 * @author Steve
 * Created on 2020-04
 */
public class WordFile extends ResourceFile{

    public WordFile(String filepath) {
        super(filepath);
    }

    @Override
    public void accept(Extractor extractor) {
        extractor.extract2txt(this);
    }

}
