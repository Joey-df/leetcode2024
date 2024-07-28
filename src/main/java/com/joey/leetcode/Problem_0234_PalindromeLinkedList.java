package com.joey.leetcode;


import java.util.Stack;

/**
 * 234. 回文链表
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 * 输入: 1->2
 * 输出: false
 *
 * 示例 2:
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */
public class Problem_0234_PalindromeLinkedList {

    //两边遍历
    //第一遍将所有结点压栈
    //第二遍再从头开始，挨个和栈顶元素对比，看是否每次都相等
    public boolean isPalindrome(ListNode head) {
        if (head==null || head.next==null) {
            return true;
        }
        Stack<Integer> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            if (stack.pop() != cur.val) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }


    //省一半空间的做法
    public boolean isPalindrome2(ListNode head) {
        if (head==null || head.next==null) {
            return true;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode s = dummy.next; // head
        ListNode f = dummy.next.next; // head.next
        while (f!=null && f.next!=null) {
            s = s.next;
            f = f.next.next;
        }
        //s：奇数时来到中点，偶数时来到下中点
        ListNode cur = s.next;
        Stack<Integer> stack = new Stack<>();
        while (cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }
        cur = head;
        while (!stack.isEmpty()) {
            if (cur.val != stack.pop()) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }
}
