package com.joey.leetcode;


/**
 * 206. 反转链表
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 */
public class Problem_0206_ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode pre=null,next=null;
        while (head!=null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
