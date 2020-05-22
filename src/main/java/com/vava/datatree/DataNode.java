package com.vava.datatree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import com.google.gson.Gson;

/**
 * @author Steve
 * Created on 2020-05
 */
public class DataNode {
    private String id;
    private DataNode parentNode;
    private Set<DataNode> childNodes;

    public DataNode(String id) {
        this.id = id;
        this.parentNode = null;
        this.childNodes = new HashSet<>();
    }

    public DataNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(DataNode parentNode) {
        this.parentNode = parentNode;
    }

    public Set<DataNode> getChildNodes() {
        return childNodes;
    }

    public String getId() {
        return id;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put(id, childrenToMap());
        return map;
    }

    public Map<String, Object> childrenToMap() {
        Map<String, Object> map = new HashMap<>();
        if (CollectionUtils.isEmpty(childNodes)) {
            return null;
        } else {
            for (DataNode dataNode : childNodes) {
                map.put(dataNode.getId(), dataNode.childrenToMap());
            }
        }
        return map;
    }

    @Override
    public String toString() {
        return new Gson().toJson(toMap());
    }

    public void addChild(DataNode dataNode) {
        this.childNodes.add(dataNode);
    }
}
