package com.vava.designpattern.interpreter.alert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Steve
 * Created on 2020-04
 */
public class AndExpression implements AlertExpression {
    private List<AlertExpression> expressions = new ArrayList<>();

    public AndExpression(String expression) {
        String[] elements = expression.trim().split("&&");
        for (String strExp : elements) {
            if (strExp.contains(">")) {
                expressions.add(new GreaterExpression(strExp));
            } else if (strExp.contains("<")) {
                expressions.add(new LessExpression(strExp));
            } else if (strExp.contains("==")) {
                expressions.add(new EqualExpression(strExp));
            } else {
                throw new RuntimeException("AndExpression invalid: " + expression);
            }
        }
    }

    @Override
    public boolean interpret(Map<String, Long> stats) {
        for (AlertExpression alertExpression : expressions) {
            if (!alertExpression.interpret(stats)) {
                return false;
            }
        }
        return true;
    }
}
