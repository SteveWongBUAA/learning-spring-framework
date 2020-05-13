package com.vava.designpattern.interpreter.alert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Steve
 * Created on 2020-04
 */
public class OrExpression implements AlertExpression {
    private List<AlertExpression> expressions = new ArrayList<>();

    public OrExpression(String expression) {
        // 或 是低优先级的，split完了默认是AndExpression
        String[] elements = expression.trim().split("\\|\\|");
        for (String strExp : elements) {
            expressions.add(new AndExpression(strExp));
        }
    }

    @Override
    public boolean interpret(Map<String, Long> stats) {
        for (AlertExpression alertExpression : expressions) {
            if (alertExpression.interpret(stats)) {
                return true;
            }
        }
        return false;
    }
}
