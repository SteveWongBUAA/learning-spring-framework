package com.vava.datatree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author Steve
 * Created on 2020-05
 */
public class DataTree {
    private static final long MAX_ITER = 10000000000l;
    private Map<String, DataNode> nodeMap;

    public DataTree() {
        this.nodeMap = new HashMap<>();
    }

    public void addData(String id, String parentId) {
        nodeMap.putIfAbsent(id, new DataNode(id));
        DataNode childDataNode = nodeMap.get(id);
        nodeMap.putIfAbsent(parentId, new DataNode(parentId));
        DataNode parentDataNode = nodeMap.get(parentId);
        parentDataNode.addChild(childDataNode);
        childDataNode.setParentNode(parentDataNode);
    }

    public DataNode getNode(String id) {
        return nodeMap.getOrDefault(id, null);
    }

    public Set<String> getAllChildrenList(String id) {
        Set<String> res = new HashSet<>();
        if (!nodeMap.containsKey(id)) {
            return res;
        }
        Queue<DataNode> queue = new LinkedList<>();
        queue.add(nodeMap.get(id));
        boolean drainQueue = false;
        for (int i = 0; i < MAX_ITER; i++) {
            if (queue.isEmpty()) {
                drainQueue = true;
                break;
            }
            DataNode dataNode = queue.poll();
            res.add(dataNode.getId());
            queue.addAll(dataNode.getChildNodes());
        }
        if (!drainQueue) {
            throw new RuntimeException("can not drain queue after loop: " + MAX_ITER);
        }
        return res;
    }

    public Map<String, Object> getAllChildrenHierarchically(String id) {
        Map<String, Object> map = new HashMap<>();
        if (!nodeMap.containsKey(id)) {
            return map;
        }
        return nodeMap.get(id).toMap();
    }

    public List<String> getAllParents(String id) {
        List<String> res = new ArrayList<>();
        if (!nodeMap.containsKey(id)) {
            return res;
        }
        DataNode dataNode = nodeMap.get(id);
        boolean loopEnd = false;
        for (int i = 0; i < MAX_ITER; i++) {
            if (null == dataNode) {
                loopEnd = true;
                break;
            }
            res.add(dataNode.getId());
            dataNode = dataNode.getParentNode();
        }
        if (!loopEnd) {
            throw new RuntimeException("can not get all parents after loop: " + MAX_ITER);
        }
        return res;
    }
}

