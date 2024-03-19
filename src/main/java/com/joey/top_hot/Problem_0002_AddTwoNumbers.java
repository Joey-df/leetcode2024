package com.joey.top_hot;


/**
 * 给你两个非空 的链表，表示两个非负的整数。
 * 它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
 * <p>
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0开头。
 * <p>
 * 示例 1：
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 */
public class Problem_0002_AddTwoNumbers {

    public static ListNode addTwoNumbers(ListNode h1, ListNode h2) {
        if (h1 == null || h2 == null) return h1 == null ? h2 : h1;
        ListNode dummy = new ListNode(-1);
        ListNode cur1 = h1, cur2 = h2;
        int sum = cur1.val + cur2.val;
        int carry = sum / 10;
        ListNode cur = new ListNode(sum % 10);
        dummy.next = cur;
        cur1 = cur1.next;
        cur2 = cur2.next;
        while (cur1 != null || cur2 != null) {
            sum = (cur1 != null ? cur1.val : 0) + (cur2 != null ? cur2.val : 0) + carry;
            carry = sum / 10;
            cur.next = new ListNode(sum % 10);;
            cur = cur.next;
            if (cur1 != null) cur1 = cur1.next;
            if (cur2 != null) cur2 = cur2.next;
        }
        if (carry != 0) cur.next = new ListNode(carry);
        return dummy.next;
    }
}
