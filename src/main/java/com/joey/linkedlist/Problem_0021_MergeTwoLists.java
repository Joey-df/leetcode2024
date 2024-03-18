package com.joey.linkedlist;

/**
 * @author pei.liu
 */

//21. 合并两个有序链表
//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
public class Problem_0021_MergeTwoLists {

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null ^ list2 == null) {
            return list1 == null ? list2 : list1;
        }
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        ListNode cur1 = list1;
        ListNode cur2 = list2;
        while (cur1 != null && cur2 != null) {
            if (cur1.val <= cur2.val) {
                cur.next = cur1;
                cur1 = cur1.next;
            } else {
                cur.next = cur2;
                cur2 = cur2.next;
            }
            cur = cur.next;
        }
        if (cur1 != null) cur.next = cur1;
        if (cur2 != null) cur.next = cur2;
        return dummy.next;
    }

}
