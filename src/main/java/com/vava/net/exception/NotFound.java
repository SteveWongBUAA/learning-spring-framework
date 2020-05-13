package com.vava.net.exception;

/**
 * @author Steve
 * Created on 2020-04
 */
public class NotFound extends RuntimeException {
    public NotFound(String message) {
        super(message);
    }
}
