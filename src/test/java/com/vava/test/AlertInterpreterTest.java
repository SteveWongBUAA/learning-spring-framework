package com.vava.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.vava.designpattern.interpreter.alert.AlertRuleInterpreter;

/**
 * @author Steve
 * Created on 2020-04
 */
public class AlertInterpreterTest {
    @Test
    public void testBasic() {
        String rule = "key > 100 && key2 < 30 || key3 < 100 || key4 == 88";
        AlertRuleInterpreter interpreter = new AlertRuleInterpreter(rule);
        Map<String, Long> stats = new HashMap<>();
        stats.put("key1", 101l);
        stats.put("key3", 121l);
        stats.put("key4", 88l);
        boolean alert = interpreter.interpret(stats);
        Assert.assertTrue(alert);
    }

    @Test
    public void testBracket() {
        String rule = "key > 100 && (key2 < 30 || key3 < 100 || key4 == 88)";
        AlertRuleInterpreter interpreter = new AlertRuleInterpreter(rule);
        Map<String, Long> stats = new HashMap<>();
        stats.put("key1", 19l);
        stats.put("key3", 121l);
        stats.put("key4", 88l);
        boolean alert = interpreter.interpret(stats);
        Assert.assertFalse(alert);
    }
}
