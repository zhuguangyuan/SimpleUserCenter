package com.bruce.algo.others;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * LRU
 * put/get 都会将元素放在队头
 * put后，如果满了则删除队尾元素
 */
@Slf4j
public class LRU {
    private static final int capacity = 2;
    private final HashMap<Integer, Node> map = new HashMap<>(16);
    private final LinkedList<Node> list = new LinkedList<>();

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    static class Node {
        private int key;
        private int value;
        private Node prev;
        private Node next;
    }

    public int put(int key, int val) {
        // old value should update, and add to list head
        // new insert should check expire and remove the oldest one
        Node oldNode = map.get(key);
        if (oldNode != null) {
            log.info("put-oldValue, {}.", oldNode);
            list.remove(oldNode);
            oldNode.setValue(val);
            list.addFirst(oldNode);
            log.info("put-oldValue, done. mapItem:{}, listHead:{}", map.get(key), list.get(0));
        } else {
            oldNode = Node.builder()
                    .key(key)
                    .value(val)
                    .build();
            map.put(key, oldNode);
            list.addFirst(oldNode);

            log.info("put-newNode, done. mapItem:{}, listHead:{}", map.get(key), list.get(0));
            this.checkCapacity();
        }
        return oldNode.getValue();
    }

    public int get(int key) {
        Node targetNode = map.get(key);
        if (targetNode == null) {
            return -1;
        }

        list.remove(targetNode);
        list.addFirst(targetNode);
        log.info("get-not null, will re-put node to list head:{}", list.getFirst());
        return targetNode.getValue();
    }

    private void checkCapacity() {
        if (list.size() > capacity) {
            Node expireNode = list.removeLast();
            map.remove(expireNode.getKey());
            log.info("checkCapacity-expireNode:{}, map-key-null:{}", expireNode, map.get(expireNode.getKey()));
        } else {
            log.info("checkCapacity-enough.");
        }
    }
}
