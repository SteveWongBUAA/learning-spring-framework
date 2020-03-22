package com.vava.json;

import java.util.Map;

import com.google.gson.Gson;

/**
 * @author Steve
 * Created on 2020-03
 */
public class TestParam {
    private String type;
    private Map<String, Object> map;

    public TestParam(String type, Map<String, Object> map) {
        this.type = type;
        this.map = map;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
