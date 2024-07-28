package com.joey.leetcode;

/**
 * @author pei.liu
 */
//1669. 合并两个链表
//给你两个链表 list1 和 list2 ，它们包含的元素分别为 n 个和 m 个。
//请你将 list1 中下标从 a 到 b 的全部节点都删除，并将list2 接在被删除节点的位置。
public class Problem_1669_MergeInBetween {

    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode preA = list1;
        for (int i = 0; i < a - 1; i++) {
            preA = preA.next;
        }
        ListNode afterB = preA;
        for (int i = 0; i < b - a + 2; i++) {
            afterB = afterB.next;
        }
        preA.next = list2;
        while (list2.next != null) {
            list2 = list2.next;
        }
        list2.next = afterB;
        return list1;
    }

}
