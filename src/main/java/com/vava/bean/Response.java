package com.vava.bean;

/**
 * @author Steve
 * Created on 2020-03
 */
public class Response {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Response() {
    }

    public Response(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
