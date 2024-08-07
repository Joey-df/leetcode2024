package com.joey.leetcode;

/**
 * 25. K 个一组翻转链表
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 进阶：
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 * 示例 2：
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 * 示例 3：
 * 输入：head = [1,2,3,4,5], k = 1
 * 输出：[1,2,3,4,5]
 * 示例 4：
 * 输入：head = [1], k = 1
 * 输出：[1]
 * 提示：
 *
 * 列表中节点的数量在范围 sz 内
 * 1 <= sz <= 5000
 * 0 <= Node.val <= 1000
 * 1 <= k <= sz
 */
public class Problem_0025_ReverseNodesInKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        int len = length(head);
        if (k==1 || len<k) return head;
        //len>=k && k!=1
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;
        for (int i=0; i<k; i++) {
            cur = cur.next;
        }
        ListNode restHead = cur.next; //第k+1个节点
        cur.next = null;
        //cur来到第k个节点
        //前k个节点反转
        ListNode next = null;
        ListNode pre = reverseKGroup(restHead, k);
        head = dummy.next;
        while (head!=null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    //传入链表头节点head，返回链表长度
    private int length(ListNode head) {
        if (head==null) {
            return 0;
        }
        int len = 0;
        while (head!=null) {
            len++;
            head = head.next;
        }
        return len;
    }
}
