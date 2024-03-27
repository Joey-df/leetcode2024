package com.joey.top_hot;

//203. 移除链表元素
//给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
public class Problem_0203_RemoveLinkedListElements {

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        ListNode cur = head;
        while (cur != null && cur.val == val) {
            cur = cur.next; // cur来到第一个不是val的位置
        }
        head = cur;
        ListNode pre = cur;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

}
