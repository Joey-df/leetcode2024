package com.joey.od.s200;

/**
 * @author pei.liu
 */

import java.util.HashMap;
import java.util.Scanner;
import java.util.StringJoiner;

public class 文件缓存系统 {
    /**
     * 双向链表节点
     */
    static class Node {
        /**
         * 记录本题的文件名
         */
        String key;
        /**
         * 记录本题的文件大小
         */
        int val;
        /**
         * 记录该文件的访问次数
         */
        int freq;
        /**
         * 当前节点的前一个节点
         */
        Node last;
        /**
         * 当前节点的后一个节点
         */
        Node next;

        public Node(String key, int val, int freq) {
            this.key = key;
            this.val = val;
            this.freq = freq;
            this.last = null;
            this.next = null;
        }
    }

    /**
     * 双向链表
     */
    static class DoubleLinkedList {
        /**
         * 链表节点数
         */
        int size;
        /**
         * 链表头节点
         */
        Node head;
        /**
         * 链表尾节点
         */
        Node tail;

        /**
         * 尾插
         *
         * @param node 要插入的节点
         */
        public void addToTail(Node node) {
            if (this.size == 0) {
                this.head = node;
                this.tail = node;
            } else {
                this.tail.next = node;
                node.last = this.tail;
                this.tail = node;
            }

            this.size++;
        }

        /**
         * 删除指定节点
         *
         * @param node 指定删除的节点
         */
        public void removeNode(Node node) {
            // 如果是空链表，则没法删除
            if (this.size == 0) return;

            if (this.size == 1) {
                // 如果是单节点链表，则删除完，链表为空
                this.head = null;
                this.tail = null;
            } else if (node == this.head) {
                // 被删除节点是链表头节点
                this.head = this.head.next;
                this.head.last = null;
            } else if (node == this.tail) {
                // 被删除节点是链表尾节点
                this.tail = this.tail.last;
                this.tail.next = null;
            } else {
                // 被删除节点是链表中间节点
                node.last.next = node.next;
                node.next.last = node.last;
            }

            this.size--;
        }
    }

    static class LFUCache {
        /**
         * key是文件名，value是文件信息（key.value本质是freqMap.value对应的链表中节点Node的对象地址）
         */
        HashMap<String, Node> keyMap;

        /**
         * key是访问次数，value是访问次数位key的文件（Node）组成的链表（Link）
         */
        HashMap<Integer, DoubleLinkedList> freqMap;

        /**
         * 文件系统总容量
         */
        int capacity;

        /**
         * 最少访问次数（记录自各个文件访问次数中最少的那个）
         */
        int minFreq;

        public LFUCache(int capacity) {
            this.capacity = capacity;
            this.minFreq = 0;
            this.keyMap = new HashMap<>();
            this.freqMap = new HashMap<>();
        }

        /**
         * @param key 对应本题的文件名
         */
        public void get(String key) {
            // 如果文件不存在，则不作任何操作。
            if (!this.keyMap.containsKey(key)) return;

            Node node = this.keyMap.get(key);
            // 每次文件访问后，总访问次数加1，最近访问时间更新到最新时间
            incNodeFreq(node);
        }

        /**
         * @param key 对应本题的文件名
         * @param val 对应本题的文件大小
         */
        public void put(String key, int val) {
            // 如果新文件的文件名和文件缓存中已有的文件名相同，则不会放在缓存中
            if (this.keyMap.containsKey(key)) return;

            // 当缓存空间不足以存放新的文件时，根据规则删除文件，直到剩余空间满足新的文件大小位置，再存放新文件。
            while (this.capacity < val) {
                if (this.minFreq == 0) {
                    // 文件系统空了，也放不下该文件，则不放入
                    return;
                }

                // 找出最少访问次数对应的链表
                DoubleLinkedList minFreqDoubleLinkedList = this.freqMap.get(this.minFreq);

                // 链表头部节点是最少访问次数中，最远访问的文件，我们需要删除它
                Node removeNode = minFreqDoubleLinkedList.head;
                // 删除对应文件后，文件系统容量新增
                this.capacity += removeNode.val;

                // 执行删除操作，freqMap和keyMap都要删除掉对应文件的记录
                minFreqDoubleLinkedList.removeNode(removeNode);
                this.keyMap.remove(removeNode.key);

                // 如果删除后，最少访问次数的链表空了，则需要找到下一个最少访问次数的链表
                if (minFreqDoubleLinkedList.size == 0) {
                    // 最少访问次数没有对应文件（链表为空），则删除最少访问次数的记录（freqMap的key）
                    this.freqMap.remove(this.minFreq);

                    if (this.freqMap.size() > 0) {
                        this.minFreq = this.freqMap.keySet().stream().min((a, b) -> a - b).get();
                    } else {
                        // 文件系统没有缓存文件了，则最少次数为0，表示文件系统空了
                        this.minFreq = 0;
                    }
                }
            }

            // 新增文件，则文件系统容量减少
            this.capacity -= val;
            // 新增文件的访问次数为1，因此最少访问次数变为了1
            this.minFreq = 1;
            Node node = new Node(key, val, this.minFreq);
            // 执行新增操作，freqMap和keyMap都要新增对应文件的记录
            this.freqMap.putIfAbsent(this.minFreq, new DoubleLinkedList());
            this.freqMap.get(this.minFreq).addToTail(node);
            this.keyMap.put(key, node);
        }

        public void incNodeFreq(Node node) {
            DoubleLinkedList doubleLinkedList = this.freqMap.get(node.freq);

            // 由于要更新文件的访问次数，因此需要将node从当前访问次数的链表中删除
            doubleLinkedList.removeNode(node);
            // 如果当前访问次数链表只有当前node，则继续看当前链表对应的访问次数是否位最少访问次数，若是，则说明删除当前节点后，最少访问次数对应的文件没了
            if (doubleLinkedList.size == 0) {
                // 当前访问次数没有对应文件（链表为空），则删除当前访问次数的记录（freqMap的key）
                this.freqMap.remove(node.freq);

                if (node.freq == this.minFreq) {
                    // 此时我们应该更新最少访问次数
                    this.minFreq++;
                }
            }

            node.freq++; // 总访问次数加1
            this.freqMap.putIfAbsent(node.freq, new DoubleLinkedList());
            // 将node插入到对应freq的链表中
            // 尾插 是为了实现：最近访问时间更新到最新时间，即这里认为：链表尾部是最近的，链表头部是最远的
            this.freqMap.get(node.freq).addToTail(node);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = Integer.parseInt(sc.nextLine());
        LFUCache lfuCache = new LFUCache(m);

        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            String[] operation = sc.nextLine().split(" ");

            String op = operation[0];
            String fileName = operation[1];

            if ("put".equals(op)) {
                int fileSize = Integer.parseInt(operation[2]);
                lfuCache.put(fileName, fileSize);
            } else {
                lfuCache.get(fileName);
            }
        }

        if (lfuCache.capacity == m) {
            // 如果文件系统容量没有减少，则没有文件被缓存
            System.out.println("NONE");
        } else {
            // 否则取出文件系统中的文件名，按照字典序输出
            StringJoiner sj = new StringJoiner(",");
            lfuCache.keyMap.keySet().stream().sorted().forEach(sj::add);
            System.out.println(sj);
        }
    }
}