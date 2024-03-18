package com.joey.linkedlist;

/**
 * @author pei.liu
 */

//86. 分隔链表
//给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
//你应当 保留 两个分区中每个节点的初始相对位置。
//
//示例 1：
//输入：head = [1,4,3,2,5,2], x = 3
//输出：[1,2,2,4,3,5]
//示例 2：
//输入：head = [2,1], x = 2
//输出：[1,2]
public class Problem_0086_PartitionList {

    public static ListNode partition(ListNode head, int x) {
        if (head == null) return head;
        ListNode leftHead = null, leftTail = null, rightHead = null, rightTail = null;
        ListNode cur = head;
        ListNode next;
        while (cur != null) {
            next = cur.next;
            cur.next = null; // important,
            if (cur.val < x) {
                if (leftHead == null) {
                    leftHead = cur;
                    leftTail = cur;
                } else {
                    leftTail.next = cur;
                    leftTail = cur;
                }
            } else {
                if (rightHead == null) {
                    rightHead = cur;
                    rightTail = cur;
                } else {
                    rightTail.next = cur;
                    rightTail = cur;
                }
            }
            cur = next;
        }
        if (leftHead == null) return rightHead;
        if (rightHead != null) {
            leftTail.next = rightHead;
        }
        return leftHead;
    }

}
