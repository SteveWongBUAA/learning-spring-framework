package com.vava.bean;

import java.util.Map;

import com.google.gson.Gson;

/**
 * @author Steve
 * Created on 2020-03
 */
public class RealtimeCallCommandPayload {
    private String eventName;
    private Map<String, Object> args;

    public RealtimeCallCommandPayload(String eventName, Map<String, Object> args) {
        this.eventName = eventName;
        this.args = args;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
