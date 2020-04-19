package com.vava.designpattern.singleton;

import java.util.UUID;

/**
 * @author Steve
 * Created on 2020-04
 */
public class DCLSingleton {
    private String s;
    private static volatile DCLSingleton INSTANCE;

    private DCLSingleton() {
        s = UUID.randomUUID().toString();
    }

    public String getS() {
        return s;
    }

    public static DCLSingleton getInstance() {
        if (INSTANCE == null) {
            synchronized(DCLSingleton.class) {
                if (INSTANCE == null) {

                    INSTANCE = new DCLSingleton();
                }
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        DCLSingleton dclSingleton = DCLSingleton.getInstance();
        DCLSingleton dclSingleton2 = DCLSingleton.getInstance();
        System.out.println(dclSingleton + ":" + dclSingleton.getS());
        System.out.println(dclSingleton2 + ":" + dclSingleton2.getS());


    }
}
