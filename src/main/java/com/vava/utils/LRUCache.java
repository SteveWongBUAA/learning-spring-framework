package com.vava.utils;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Steve
 * Created on 2020-06
 */
class LRUCache {

    private class ListNode {
        public int key;
        public int value;
        public ListNode pre = null;
        public ListNode next = null;

        public ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private int count;
    private Map<Integer, ListNode> map;
    private ListNode head;
    private ListNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.count = 0;
        this.map = new HashMap<>(capacity);
        // 伪头部和伪尾部节点
        this.head = new ListNode(-1, -1);
        this.tail = new ListNode(-1, -1);
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            // 访问过，需要排到队尾
            ListNode resNode = map.get(key);
            // 删除
            removeNode(resNode);
            // 加到队尾
            addToTail(resNode);
            return resNode.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        ListNode newTail;
        if (map.containsKey(key)) {
            map.get(key).value = value;
            newTail = map.get(key);
        } else {
            newTail = new ListNode(key,value);
            // 还没满， 直接添加
            if (count >= capacity) {
                // 如果满了,需要删除队头
                ListNode nowHead = head.next;
                if (nowHead != tail) {
                    removeNode(nowHead);
                }
            }
        }
        addToTail(newTail);
    }

    private void addToTail(ListNode newTail) {
        tail.pre.next = newTail;
        newTail.next = tail;
        newTail.pre = tail.pre;
        tail.pre = newTail;

        map.put(newTail.key, newTail);
        count++;
    }

    private void removeNode(ListNode resNode) {
        resNode.pre.next = resNode.next;
        resNode.next.pre = resNode.pre;
        resNode.next = null;
        resNode.pre = null;

        map.remove(resNode.key);
        count--;
    }
}
