package com.vava.designpattern.observer;

/**
 * @author steve
 * Created on 2020-03-11
 */
public class Message {
    String content;

    @Override
    public String toString() {
        return "Message{" +
                "content='" + content + '\'' +
                '}';
    }

    public Message(String content) {
        this.content = content;
    }
}
