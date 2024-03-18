package com.joey.linkedlist;

/**
 * @author pei.liu
 */

//206. 反转链表
public class Problem_0206_ReverseList {

    public static ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode pre = null, next;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
