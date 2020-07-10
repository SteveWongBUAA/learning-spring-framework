package com.vava.dao;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * @author Steve
 * Created on 2020-07
 */
@Data
public class Message<T> {
    private int status;                                     // 状态码
    private String message;                                 // 状态描述
    private T data;                                         // 实际返回的数据
    private LocalDateTime timestamp = LocalDateTime.now();   // 当前时间

    private Message(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> Message<T> ok() {
        return new Message(200, "", null );
    }

}

