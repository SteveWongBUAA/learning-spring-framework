package com.vava.designpattern.interpreter.alert;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Steve
 * Created on 2020-04
 */
public class Demo {
    public static void main(String[] args) {
        String rule = "key > 100 && key2 < 30 || key3 < 100 || key4 == 88";
        AlertRuleInterpreter interpreter = new AlertRuleInterpreter(rule);
        Map<String, Long> stats = new HashMap<>();
        stats.put("key1", 101l);
        stats.put("key3", 121l);
        stats.put("key4", 88l);
        boolean alert = interpreter.interpret(stats);
        System.out.println(alert);
    }
}
