package com.joey.top_hot;

import java.util.PriorityQueue;

/**
 * 23. 合并K个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 * <p>
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * <p>
 * 示例 1：
 * <p>
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * 示例 2：
 * <p>
 * 输入：lists = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：lists = [[]]
 * 输出：[]
 */
public class Problem_0023_MergeKSortedLists {

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> q = new PriorityQueue<>((a, b) -> a.val - b.val);
        //把每个小链表的头节点都放进去
        for (ListNode head : lists) {
            if (head != null)
                q.add(head);
        }
        if (q.isEmpty()) return null; // 处理[]、[[]]、[[],[]]等情况
        ListNode head = q.poll();
        if (head.next != null) {
            q.add(head.next); // 弹一个就加一个
        }
        ListNode cur = head;
        while (!q.isEmpty()) {
            ListNode node = q.poll();
            cur.next = node;
            cur = cur.next;
            if (node.next != null) {// 弹一个就加一个
                q.add(node.next);
            }
        }
        return head;
    }
}
