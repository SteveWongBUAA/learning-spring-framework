package com.vava.designpattern.eventbus;

/**
 * @author Steve
 * Created on 2020-03
 */
public class Qmsg {
    private String content;

    public Qmsg(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Qmsg{" +
                "content='" + content + '\'' +
                '}';
    }
}
