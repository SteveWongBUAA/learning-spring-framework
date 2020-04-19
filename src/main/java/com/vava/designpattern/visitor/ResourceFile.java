package com.vava.designpattern.visitor;

/**
 * @author Steve
 * Created on 2020-04
 */
public abstract class ResourceFile {
    private String filepath;

    public ResourceFile(String filepath) {
        this.filepath = filepath;
    }

    abstract public void accept(Extractor extractor);

}
