package com.vava.bean;

/**
 * @author steve
 * Created on 2020-03-09
 */
public class CustomDataSource {
    private String env;

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public CustomDataSource(String env) {
        this.env = env;
    }
}
