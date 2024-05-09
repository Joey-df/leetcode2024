package com.joey.od.realquestions;

/**
 * @author pei.liu
 */
public class No18_判断链表是否有环 {

    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    // lc 141
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode s = head.next;
        ListNode f = head.next.next;
        while (s != f) {
            if (f == null || f.next == null) return false;
            s = s.next;
            f = f.next.next;
        }
        return true;
    }

    //lc 142
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode s = head.next;
        ListNode f = head.next.next;
        while (s != f) {
            if (f == null || f.next == null) return null;
            s = s.next;
            f = f.next.next;

        }
        f = head;
        while (s != f) {
            s = s.next;
            f = f.next;
        }
        return s;
    }
}
