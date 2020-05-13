package com.vava.net.tokens;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.vava.net.Entity;
import com.vava.net.EntityNet;

/**
 * @author Steve
 * Created on 2020-05
 */
public class RelationOp extends NetOp {
    // 关系运算符的source应该是个EntityNet
    private NetOp sourceOp;
    private Token token;

    public RelationOp(NetOp sourceEntityNet, Token token) {
        this.sourceOp = sourceEntityNet;
        this.token = token;
    }

    @Override
    public String toString() {
        return "RelationOp{\n" +
                "  sourceOp=" + sourceOp +
                ", token=" + token +
                "\n}";
    }

    @Override
    public EntityNet execute() {
        Set<Entity> entitySet= new HashSet<>();
        EntityNet sourceEntityNet = sourceOp.execute();
        for (Entity entity : sourceEntityNet.getEntityNet().values()) {
            entitySet.addAll(entity.getRelations().get(token.getStrToken()));
        }
        Map<String, Entity> res = new HashMap<>();
        for (Entity entity:entitySet) {
            res.put(entity.getId(), entity);
        }
        return new EntityNet(res);
    }
}
