package com.vava.designpattern.interpreter.expression;

/**
 * @author Steve
 * Created on 2020-04
 */
public class ExpressionDemo {
    public static void main(String[] args) {
        ExpressionInterpreter expressionInterpreter = new ExpressionInterpreter();
        long res = expressionInterpreter.interpret("1 2 43 + +");
        System.out.println(res);
    }
}
