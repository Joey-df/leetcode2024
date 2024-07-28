package com.joey.leetcode;

import java.util.HashMap;

/**
 * 82. 删除排序链表中的重复元素 II
 * 存在一个按升序排列的链表，给你这个链表的头节点 head，
 * 请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。
 * <p>
 * 返回同样按升序排列的结果链表。
 */
public class Problem_0082_RemoveDuplicatesFromSortedListII {

    //方法1：使用hashMap+迭代
    // [1,1,2,3,3,4,4,5]
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        HashMap<Integer, Integer> map = new HashMap<>();
        ListNode cur = head;
        while (cur != null) {
            map.put(cur.val, map.getOrDefault(cur.val, 0) + 1);
            cur = cur.next;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        cur = head;
        while (cur != null) {
            if (map.get(cur.val) > 1) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return dummy.next;
    }

}
