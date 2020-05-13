package com.vava.net;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author Steve
 * Created on 2020-04
 */
public class Entity {
    private String id;
    private HashMap<String, HashSet<Entity>> relations;

    public Entity(String id) {
        this.id = id;
        this.relations = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HashMap<String, HashSet<Entity>> getRelations() {
        return relations;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, HashSet<Entity>> entry : relations.entrySet()) {
            sb.append("--[").append(entry.getKey()).append("]-->[");
            for (Entity entity : entry.getValue()) {
                sb.append(entity.getId()).append(",");
            }
            sb.append("]\n");
        }
        return "Entity{" +
                "id='" + id + '\'' +
                ", relations=\n" + sb.toString() +
                '}';
    }
}
