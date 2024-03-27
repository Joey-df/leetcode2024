package com.joey.top_hot;

/**
 * 148. 排序链表
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * <p>
 * 示例 1：
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 * <p>
 * 示例 2：
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-list
 */
//单链表排序
public class Problem_0148_SortList {

    //递归含义
    //head为头的链表进行归并排序，返回排序后的链表头节点
    //O(nlogn)
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode s = dummy, f = dummy;
        while (f != null && f.next != null) {
            s = s.next;
            f = f.next.next;
        }
        //s来到中点/上中点
        ListNode left = head;
        ListNode right = s.next;
        s.next = null;
        ListNode h1 = sortList(left);
        ListNode h2 = sortList(right);
        return merge(h1, h2);
    }

    //merge两个有序链表
    private ListNode merge(ListNode l, ListNode r) {
        // 2->4->null
        // 1->3->null
        ListNode dummy = new ListNode(-1);
        ListNode cur1 = l;
        ListNode cur2 = r;
        ListNode cur = dummy;
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
        cur.next = cur1 != null ? cur1 : cur2;
        return dummy.next;
    }


}
