package com.joey.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 146. LRU 缓存机制
 */
public class Problem_0146_LRUCache {

    static class Node {
        int key;
        int val;
        Node next;
        Node last;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    static class DoubleLinkedList {
        Node head;
        Node tail;

        //新加结点
        public void addToTail(Node node) {
            if (node == null) return;
            if (head == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                node.last = tail;
                tail = node;
            }
        }

        //调用方请保证node存在
        public void moveToTail(Node node) {
            if (node == tail) return;
            if (node == head) {
                head = head.next;
                head.last = null;
                node.next = null;
                node.last = tail;
                tail.next = node;
                tail = node;
            } else {
                node.last.next = node.next;
                node.next.last = node.last;
                node.last = tail;
                tail.next = node;
                node.next = null;
                tail = node;
            }
        }

        public Node removeHead() {
            if (head == null) return null;
            if (head == tail) {
                Node res = head;
                head = null;
                tail = null;
                return res;
            } else {
                Node res = head;
                head = head.next;
                head.last = null;
                res.next = null;
                return res;
            }
        }
    }

    Map<Integer, Node> map;
    DoubleLinkedList linkedList;
    int limit;

    public Problem_0146_LRUCache(int capacity) {
        map = new HashMap<>();
        linkedList = new DoubleLinkedList();
        limit = capacity;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node node = map.get(key);
        linkedList.moveToTail(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) { // mod
            Node node = map.get(key);
            node.val = value;
            linkedList.moveToTail(node);
        } else {
            // add
            if (map.size() == limit) {
                //淘汰
                Node old = linkedList.removeHead();
                map.remove(old.key);
            }
            Node nn = new Node(key, value);
            map.put(key, nn);
            linkedList.addToTail(nn);
        }
    }

}
