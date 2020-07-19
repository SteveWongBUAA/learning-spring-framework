package com.vava.qlexpress;

import lombok.Data;

/**
 * @author Steve
 * Created on 2020-07
 */
@Data
public class ApplicationVisibleRule {
    private int id;
    private String app_id;
    private int scope_id;
    private String param;
    private String operator;
    private String value;

    public ApplicationVisibleRule(int id, String app_id, int scope_id, String param, String operator,
            String value) {
        this.id = id;
        this.app_id = app_id;
        this.scope_id = scope_id;
        this.param = param;
        this.operator = operator;
        this.value = value;
    }
}
