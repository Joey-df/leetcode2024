package com.joey.top_hot;


/**
 * 21. 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。
 * 新链表是通过拼接给定的两个链表的所有节点组成的。
 * <p>
 * 示例 1：
 * <p>
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 */

// 分别使用递归和迭代实现
public class Problem_0021_MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode h1, ListNode h2) {
        if (h1 == null || h2 == null) return h1 == null ? h2 : h1;
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy, cur1 = h1, cur2 = h2;
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


    public ListNode mergeTwoLists2(ListNode h1, ListNode h2) {
        if (h1 == null || h2 == null) return h1 == null ? h2 : h1;
        if (h1.val <= h2.val) {
            h1.next = mergeTwoLists2(h1.next, h2);
            return h1;
        }
        h2.next = mergeTwoLists2(h2.next, h1);
        return h2;
    }

}
