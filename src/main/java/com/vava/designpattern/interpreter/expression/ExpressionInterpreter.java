package com.vava.designpattern.interpreter.expression;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Steve
 * Created on 2020-04
 */
public class ExpressionInterpreter {
    private Deque<Long> numbers = new LinkedList<>();

    public long interpret(String expression) {
        String[] elements = expression.split(" ");
        int length = elements.length;
        for (int i = 0; i < (length + 1) / 2; ++i) {
            numbers.addLast(Long.parseLong(elements[i]));
        }

        for (int i = (length+1)/2; i < length; i++) {
            String operator = elements[i];
            boolean isValid = "+".equals(operator);
            if (!isValid) {
                throw new RuntimeException("Expression operator is invalid:" + operator);
            }

            long num1 = numbers.pollFirst();
            long num2 = numbers.pollFirst();
            Expression combinedExp = null;
            if (operator.equals("+")) {
                combinedExp = new AdditionExpression(new NumberExpression(num1), new NumberExpression(num2));
            }
            long result = combinedExp.interpret();
            numbers.addFirst(result);
        }
        if (numbers.size() != 1) {
            throw new RuntimeException("Expression is invalid: " + expression);
        }
        return numbers.pop();
    }
}
