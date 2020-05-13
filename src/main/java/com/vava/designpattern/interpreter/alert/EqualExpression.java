package com.vava.designpattern.interpreter.alert;

import java.util.Map;

/**
 * @author Steve
 * Created on 2020-04
 */
public class EqualExpression implements AlertExpression {
    private String key;
    private long value;

    public EqualExpression(String expression) {
        String[] elements = expression.trim().split("\\s+");
        if (elements.length != 3 || !elements[1].trim().equals("==")) {
            throw new RuntimeException("EqualExpression invalid: " + expression);
        }
        this.key = elements[0].trim();
        this.value = Long.parseLong(elements[2].trim());
    }

    @Override
    public boolean interpret(Map<String, Long> stats) {
        if (!stats.containsKey(key)) {
            return false;
        }
        long statValue = stats.get(key);
        return statValue == value;
    }

}
