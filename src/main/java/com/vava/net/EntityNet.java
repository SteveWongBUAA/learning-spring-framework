package com.vava.net;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * @author Steve
 * Created on 2020-04
 * 用来保存网里的所有Entity节点，可以从任意一个开始出发遍历
 */
public class EntityNet {
    private Map<String, Entity> entityNet;

    public Map<String, Entity> getEntityNet() {
        return entityNet;
    }

    public EntityNet(Map<String, Entity> entityNet) {
        this.entityNet = entityNet;
    }

    public EntityNet() {
        this.entityNet = new HashMap<>();
    }

    /**
     * 正反方向都要建立关系
     */
    public void putRelation(String from, String relation, String to) {
        if (StringUtils.isEmpty(relation)) {
            throw new NetException("empty relation");
        }
        entityNet.computeIfAbsent(from, k -> new Entity(from));
        Entity entityFrom = entityNet.get(from);
        entityNet.computeIfAbsent(to, k -> new Entity(to));
        Entity entityTo = entityNet.get(to);
        putBiDirectionalRelations(entityFrom, relation, entityTo);
    }

    private void putBiDirectionalRelations(Entity from, String relation, Entity to) {
        from.getRelations().computeIfAbsent(relation, key -> new HashSet<>()).add(to);
        to.getRelations().computeIfAbsent("~" + relation, key -> new HashSet<>()).add(from);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Entity entity : entityNet.values()) {
            sb.append(entity.toString()).append("\n\n");
        }
        return sb.toString();
    }
}
