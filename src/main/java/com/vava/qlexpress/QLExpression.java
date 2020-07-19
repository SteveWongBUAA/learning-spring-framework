package com.vava.qlexpress;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Steve
 * Created on 2020-07
 */
public class QLExpression {
    private List<ApplicationVisibleRule> applicationVisibleRuleList;

    public QLExpression(List<ApplicationVisibleRule> applicationVisibleRuleList) {
        this.applicationVisibleRuleList = applicationVisibleRuleList;
    }

    @Override
    public String toString() {
        Map<Integer, List<String>> scopeExpressionMap = new HashMap<>();
        for (ApplicationVisibleRule applicationVisibleRule : applicationVisibleRuleList) {
            scopeExpressionMap.putIfAbsent(applicationVisibleRule.getScope_id(), new ArrayList<>());
            List<String> scopeExpressions = scopeExpressionMap.get(applicationVisibleRule.getScope_id());
            // 特殊处理 not in
            String scopeExpression = "";
            String operator = applicationVisibleRule.getOperator();
            if (operator.equals("not in")) {
                scopeExpression =
                        "!(e." + applicationVisibleRule.getParam() + " "
                                + "in" + " "
                                + applicationVisibleRule.getValue() + ")";
            } else {
                scopeExpression =
                        "(e." + applicationVisibleRule.getParam() + " "
                                + applicationVisibleRule.getOperator() + " "
                                + applicationVisibleRule.getValue() + ")";
            }
            scopeExpressions.add(scopeExpression);
        }
        List<String> strScopeExpression = new ArrayList<>();
        for (List<String> scopeExpressions : scopeExpressionMap.values()) {
            strScopeExpression.add("(" + String.join(" && ", scopeExpressions) + ")");
        }
        return String.join(" \n|| ", strScopeExpression);
    }
}
