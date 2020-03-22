package com.vava.designpattern.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Steve
 * Created on 2020-03
 */
public class StrategyFactory {
    private static final Map<String, Strategy> strategies = new HashMap<>();
    static {
        strategies.put("A", new ConcreteStrategyA());
        strategies.put("B", new ConcreteStrategyB());
    }

    public static Strategy getStrategy(String type) {
        return strategies.get(type);
    }
}
