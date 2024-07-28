package com.joey.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 83. 删除排序链表中的重复元素
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
 * <p>
 * 返回同样按升序排列的结果链表。
 */
//进阶：删除无序但链表中值重复出现的节点
public class Problem_0083_RemoveDuplicatesFromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (cur.val == pre.val) {
                pre.next = cur.next; // del cur
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;


    }

    //进阶
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null) return null;
        Set<Integer> set = new HashSet<>();
        set.add(head.val);
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (set.contains(cur.val)) {
                pre.next = cur.next;
            } else {
                pre = cur;
                set.add(cur.val);
            }
            cur = cur.next;
        }
        return head;
    }

}
