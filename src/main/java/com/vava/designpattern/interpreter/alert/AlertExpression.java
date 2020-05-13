package com.vava.designpattern.interpreter.alert;

import java.util.Map;

/**
 * @author Steve
 * Created on 2020-04
 */
public interface AlertExpression {
    boolean interpret(Map<String, Long> stats);
}
