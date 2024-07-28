package com.joey.leetcode;

/**
 * @author pei.liu
 */
//147. 对链表进行插入排序
public class Problem_0147_InsertionSortList {

    //单链表的插入排序
    public ListNode insertionSortList(ListNode head) {
        // 1. 首先判断给定的链表是否为空，若为空，则不需要进行排序，直接返回。
        if (head == null) {
            return head;
        }
        // 2. 链表初始化操作
        ListNode dummy = new ListNode(-1); // 引入虚拟头结点
        dummy.next = head; // 目的是在head之前插入节点
        ListNode lastSorted = head;  // 维护lastSorted为链表已经排好序的最后一个节点并初始化
        ListNode cur = head.next;   // 维护cur 为待插入的元素并初始化

        // 3. 插入排序
        while (cur != null) {
            if (lastSorted.val <= cur.val) {     // 说明cur应该位于lastSorted之后
                lastSorted = lastSorted.next;   // 将lastSorted后移一位,cur变成新的lastSorted
            } else {    // 否则,从链表头结点开始向后遍历链表中的节点
                ListNode prev = dummy;      // 从链表头开始遍历 prev是插入节点cur位置的前一个节点
                while (prev.next.val <= cur.val) { // 循环退出的条件是找到cur应该插入的位置
                    prev = prev.next;
                }
                // 以下三行是为了完成对cur的插入（配合题解动图可以直观看出）
                lastSorted.next = cur.next;
                cur.next = prev.next;
                prev.next = cur;
            }
            cur = lastSorted.next; // 此时 cur 为下一个待插入的元素
        }
        // 返回排好序的链表
        return dummy.next;
    }
}
