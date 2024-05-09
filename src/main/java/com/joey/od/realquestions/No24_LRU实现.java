package com.joey.od.realquestions;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pei.liu
 */
public class No24_LRU实现 {

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

        public DoubleLinkedList() {
        }

        public void addToTail(Node node) {
            if (node==null) return;
            if (head==null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                node.last = tail;
                tail = node;
            }
        }

        public void moveToTail(Node node) {
            if (node ==null)return;
            if (node == tail) return;
            if (node == head) {
                head = head.next;
                head.last = null;
                node.next = null;
                node.last = tail;
                tail.next = node;
                tail = node;
            } else {
                node.next.last = node.last;
                node.last.next = node.next;
                tail.next = node;
                node.last = tail;
                tail = node;
            }
        }

        public Node removeHead() {
            Node ret = head;
            if (head == tail) { // only one node
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.last = null;
                ret.next = null;
            }
            return ret;
        }
    }

    int limit;
    Map<Integer, Node> map;
    DoubleLinkedList linkedList;

    public No24_LRU实现(int limit) {
        this.limit = limit;
        this.map = new HashMap<>();
        this.linkedList = new DoubleLinkedList();
    }

    public void put(int key, int val) {
        if (map.containsKey(key)) { //mod
            Node node = map.get(key);
            node.val = val;
            linkedList.moveToTail(node);
        } else { //add
            if (map.size() == limit) {
                //淘汰一个
                Node node = linkedList.removeHead();
                map.remove(node.key);
            }
            Node _node = new Node(key, val);
            linkedList.addToTail(_node);
            map.put(key, _node);
        }
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1; //不存在
        Node node = map.get(key);
        linkedList.moveToTail(node);
        return node.val;
    }
}
