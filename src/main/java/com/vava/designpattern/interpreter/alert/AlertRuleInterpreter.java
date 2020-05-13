package com.vava.designpattern.interpreter.alert;

import java.util.Map;

/**
 * @author Steve
 * Created on 2020-04
 */
public class AlertRuleInterpreter {
    private AlertExpression alertExpression;
    public AlertRuleInterpreter(String expression) {
        this.alertExpression = new OrExpression(expression);
    }

    public boolean interpret(Map<String, Long> stats) {
        return alertExpression.interpret(stats);
    }
}
