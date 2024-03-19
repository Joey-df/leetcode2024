package com.joey.top_hot;

/**
 * 138. 复制带随机指针的链表
 */
public class Problem_0138_CopyListWithRandomPointer {

    // Definition for a Node.
    private static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null) return head;
        // a->b->c->d->null
        Node next = null;
        Node cur = head;
        // link new nodes
        while (cur != null) {
            next = cur.next; // b
            Node node = new Node(cur.val); // a'
            cur.next = node;
            node.next = next;
            cur = next;
        }

        // a->a'->b->b'->c->c'->d->d'->null
        // link random
        cur = head;
        Node copy = null;
        while (cur != null) {
            next = cur.next.next;
            copy = cur.next;
            copy.random = (cur.random != null) ? cur.random.next : null;
            cur = next;
        }

        // split
        Node res = head.next;
        cur = head; //a
        while (cur != null) {
            next = cur.next.next; //b
            copy = cur.next;//a'
            cur.next = next;
            copy.next = (next == null) ? null : next.next;
            cur = next;
        }
        return res;
    }
}
