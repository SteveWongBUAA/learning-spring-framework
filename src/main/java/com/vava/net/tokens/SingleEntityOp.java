package com.vava.net.tokens;

import java.util.HashMap;
import java.util.Map;

import com.vava.net.Entity;
import com.vava.net.EntityNet;

/**
 * @author Steve
 * Created on 2020-05
 */
public class SingleEntityOp extends NetOp {
    private Token singleEntity;

    public SingleEntityOp(Token singleEntity) {
        this.singleEntity = singleEntity;
    }

    @Override
    public String toString() {
        return "SingleEntityOp{" +
                "singleEntity=" + singleEntity +
                "}";
    }

    @Override
    EntityNet execute() {
        Map<String, Entity> map = new HashMap<>();
        return new EntityNet();
    }
}
