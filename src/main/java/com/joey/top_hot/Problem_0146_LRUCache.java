package com.joey.top_hot;

import java.util.HashMap;
import java.util.Map;

/**
 * 146. LRU 缓存机制
 */
public class Problem_0146_LRUCache {

    int capacity;
    // map中value放的是k/v包装成的Node，
    // 这样设计的原因:
    // 1、value是Node的内存地址，get时在双向链表中O(1)的时间复杂度直接寻址。
    // 2、当双向链表容量满了，需要删除head时，根据head.val在map中同步删除对应的记录。
    Map<Integer, Node> map;
    DoubleLinkedList list;

    public Problem_0146_LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        list = new DoubleLinkedList();
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        list.moveToTail(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) { //update
            Node node = map.get(key);
            node.val = value;
            list.moveToTail(node);
        } else { //insert
            if (map.size() == capacity) {
                //clear oldest
                Node h = list.delHead();
                map.remove(h.key);
            }
            Node node = new Node(key, value);
            map.put(key, node);
            list.addToTail(node);
        }
    }


    private static class Node {
        int key;
        int val;
        Node next;
        Node last;
        public Node(int k, int v) {
            key = k;
            val = v;
            next = null;
            last = null;
        }
    }

    public static class DoubleLinkedList {
        Node head;
        Node tail;
        public DoubleLinkedList() {
            head = null;
            tail = null;
        }

        public void addToTail(Node node) {
            if (node==null) {
                return;
            }
            if (head==null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                node.last = tail;
                tail = node;
            }
        }

        public Node delHead() {
            if (head==null) {
                return null;
            }
            if (head==tail) {
                Node res = head;
                head=null;
                tail=null;
                return res;
            }
            Node res = head;
            head = head.next;
            head.last = null;
            res.next = null;
            return res;
        }

        public void moveToTail(Node node) {
            if (node==null) return;
            if (node==tail) return;
            if (node==head) {
                head = head.next;
                head.last = null;
                node.next = null;
            } else {
                node.last.next = node.next;
                node.next.last = node.last;
            }
            node.last = tail;
            tail.next = node;
            tail = node;
        }
    }
}
