package com.joey.od.realquestions;


import java.util.HashSet;
import java.util.Set;

/**
 * @author pei.liu
 */
public class No32_Lc_160_单链表相交 {

    public ListNode getIntersectionNode(ListNode h1, ListNode h2) {
        if (h1 == null || h2 == null) return null;
        //都不为空
        ListNode cur1 = h1, cur2 = h2;
        int len1 = 1, len2 = 1;
        while (cur1.next != null) {
            cur1 = cur1.next;
            len1++;
        }
        //cur1 来到最后一个结点
        while (cur2.next != null) {
            cur2 = cur2.next;
            len2++;
        }
        if (cur1 != cur2) return null;
        //cur1==cur2
        ListNode longer = len1 > len2 ? h1 : h2;
        ListNode shorter = longer == h1 ? h2 : h1;
        int N = Math.abs(len1 - len2);
        while (N-- > 0) {
            longer = longer.next;
        }
        while (longer != shorter) {
            longer = longer.next;
            shorter = shorter.next;
        }
        return longer;
    }

    //哈希表
    public ListNode getIntersectionNode2(ListNode h1, ListNode h2) {
        if (h1 == null || h2 == null) return null;
        Set<ListNode> set = new HashSet<>();
        ListNode cur = h1;
        while (cur != null) {
            set.add(cur);
            cur = cur.next;
        }

        cur = h2;
        while (cur != null) {
            if (set.contains(cur)) return cur;
            cur = cur.next;
        }
        return null;
    }
}
