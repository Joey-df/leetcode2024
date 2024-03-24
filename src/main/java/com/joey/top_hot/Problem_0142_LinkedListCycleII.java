package com.joey.top_hot;

//142. 环形链表 II
//给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
public class Problem_0142_LinkedListCycleII {

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode s = head.next, f = head.next.next;
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
