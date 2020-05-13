package com.vava.net.tokens;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Steve
 * Created on 2020-05
 */
public enum OperatorEnum {
    COMMA(","),
    SQUARE_BRACKET_LEFT("["),
    SQUARE_BRACKET_RIGHT("]"),
    PARENTHESES_LEFT("("),
    PARENTHESES_RIGHT(")"),
    BRACE_LEFT("{"),
    BRACE_RIGHT("}"),
    SUB("-"),
    ADD("+"),
    MERGE("&"),
    KEEP("#"),

    RELATION("->"),
    ;

    private static final Map<String, OperatorEnum> map = new HashMap<>();

    static {
        for (OperatorEnum operatorEnum : OperatorEnum.values()) {
            map.put(operatorEnum.getStrOperator(), operatorEnum);
        }
    }

    private String strOperator;

    OperatorEnum(String strOperator) {
        this.strOperator = strOperator;
    }

    public static OperatorEnum findByStrOperator(String strOperator) {
        return map.getOrDefault(strOperator, null);
    }

    public String getStrOperator() {
        return strOperator;
    }

    public void setStrOperator(String strOperator) {
        this.strOperator = strOperator;
    }
}
