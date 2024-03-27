package com.joey.top_hot;

/**
 * 141. 环形链表
 * 给定一个链表，判断链表中是否有环。
 * 如果链表中存在环，则返回 true 。 否则，返回 false 。
 */
public class Problem_0141_LinkedListCycle {

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
}
