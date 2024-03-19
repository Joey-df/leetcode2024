package com.joey.top_hot;

import linked_list.ListNode;

/**
 * 160. 相交链表
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。
 * 如果两个链表没有交点，返回 null 。
 */
public class Problem_0160_IntersectionOfTwoLinkedLists {

    public ListNode getIntersectionNode(ListNode h1, ListNode h2) {
        if (h1 == null && h2 == null) return null;
        if (h1 == null ^ h2 == null) return null;
        //都不为空
        int n = 0;
        ListNode head1 = h1;
        ListNode head2 = h2;
        while (h1.next != null) {
            n++;
            h1 = h1.next;
        }

        //h1来到e1
        while (h2.next != null) {
            n--;
            h2 = h2.next;
        }

        if (h1 != h2) {
            return null;
        }
        //相交
        ListNode s = n < 0 ? head1 : head2;
        ListNode l = s == head1 ? head2 : head1;
        n = Math.abs(n);
        while (n-- > 0) {
            l = l.next;
        }
        while (s != l) {
            s = s.next;
            l = l.next;
        }
        return s;
    }
}
