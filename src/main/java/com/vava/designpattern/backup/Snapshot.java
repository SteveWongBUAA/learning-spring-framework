package com.vava.designpattern.backup;

/**
 * @author Steve
 * Created on 2020-04
 */
public class Snapshot {
    private String text;

    public Snapshot(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
