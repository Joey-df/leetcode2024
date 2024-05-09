package com.joey.top_hot;

//148. 排序链表
//给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
//示例 1：
//输入：head = [4,2,1,3]
//输出：[1,2,3,4]
//示例 2：
//输入：head = [-1,5,3,4,0]
//输出：[-1,0,3,4,5]
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/sort-list
//单链表排序
public class Problem_0148_SortList {

    //单链表归并排序
    //递归含义
    //head为头的链表进行归并排序，返回排序后的链表头节点
    //O(nlogn)
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode s = head;
        ListNode f = head.next;
        while (f != null && f.next != null) {
            s = s.next;
            f = f.next.next;
        }
        //s来到中点 / 上中点
        ListNode h2 = s.next;
        s.next = null;
        //对h1 h2排序
        ListNode sortedH1 = sortList(head);
        ListNode sortedH2 = sortList(h2);
        //merge
        return merge(sortedH1, sortedH2);
    }


    public ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l1, l2.next);
            return l2;
        }
    }

    // 单链表的选择排序
    // leetcode上提交会超时
    public ListNode sortList2(ListNode head) {
        if (head == null || head.next == null) return head;
        for (ListNode p = head; p != null; p = p.next) {
            //每一趟遍历，找到当前节点到结尾之间的值最小的node
            ListNode min = p;
            for (ListNode q = p.next; q != null; q = q.next) {
                if (q.val < min.val) {
                    min = q;
                }
            }
            //swap
            int t = p.val;
            p.val = min.val;
            min.val = t;
        }
        return head;
    }


    // 单链表快速排序
    // leetcode上提交会超时
    public ListNode sortList3(ListNode head) {
        return sort(head)[0];
    }

    // 传入链表头节点，返回排序后链表的头节点、尾节点
    private ListNode[] sort(ListNode head) {
        if (head == null || head.next == null) {
            return new ListNode[]{head, head};
        }
        ListNode[][] part = partition(head);
        ListNode[] lessPart = part[0];
        ListNode[] equalsPart = part[1];
        ListNode[] bigPart = part[2];
        ListNode[] left = sort(lessPart[0]);
        ListNode[] right = sort(bigPart[0]);
        if (left[1] != null) {
            left[1].next = equalsPart[0]; //小于区的尾，连等于区的头
            equalsPart[1] = equalsPart[1] == null ? left[1] : equalsPart[1];
        }
        if (equalsPart[1] != null) {
            equalsPart[1].next = right[0]; //等于区的尾，连大于区的头
        }
        return new ListNode[]{
                left[0] != null ? left[0] : (equalsPart[0] != null ? equalsPart[0] : right[0]),
                right[1] != null ? right[1] : (equalsPart[1] != null ? equalsPart[1] : left[1])
        };
    }

    // 传入链表头节点，返回小于区的头和尾、等于区的头和尾、大于区的头和尾
    private ListNode[][] partition(ListNode head) {
        ListNode lh = null;
        ListNode lt = null;
        ListNode eh = null;
        ListNode et = null;
        ListNode bh = null;
        ListNode bt = null;
        ListNode next = null;
        int base = head.val;
        while (head != null) {
            next = head.next;
            if (head.val < base) {
                if (lh == null) {
                    lh = head;
                    lt = head;
                } else {
                    lt.next = head;
                    lt = lt.next;
                }

            } else if (head.val > base) {
                if (bh == null) {
                    bh = head;
                    bt = head;
                } else {
                    bt.next = head;
                    bt = bt.next;
                }
            } else {
                if (eh == null) {
                    eh = head;
                    et = head;
                } else {
                    et.next = head;
                    et = et.next;
                }
            }
            head.next = null;
            head = next;
        }
        if (lt != null) {
            lt.next = null;
        }
        if (et != null) {
            et.next = null;
        }
        if (bt != null) {
            bt.next = null;
        }
        return new ListNode[][]{{lh, lt}, {eh, et}, {bh, bt}};
    }

    //单链表的插入排序
    public ListNode insertionSortList(ListNode head) {
        // 1. 首先判断给定的链表是否为空，若为空，则不需要进行排序，直接返回。
        if (head == null) {
            return head;
        }
        // 2. 链表初始化操作
        ListNode dummy = new ListNode(-1); // 引入虚拟头结点
        dummy.next = head; // 目的是在head之前插入节点
        ListNode lastSorted = head;  // 维护lastSorted为链表已经排好序的最后一个节点并初始化
        ListNode cur = head.next;   // 维护cur 为待插入的元素并初始化

        // 3. 插入排序
        while (cur != null) {
            if (lastSorted.val <= cur.val) {     // 说明cur应该位于lastSorted之后
                lastSorted = lastSorted.next;   // 将lastSorted后移一位,cur变成新的lastSorted
            } else {    // 否则,从链表头结点开始向后遍历链表中的节点
                ListNode prev = dummy;      // 从链表头开始遍历 prev是插入节点cur位置的前一个节点
                while (prev.next.val <= cur.val) { // 循环退出的条件是找到cur应该插入的位置
                    prev = prev.next;
                }
                // 以下三行是为了完成对cur的插入（配合题解动图可以直观看出）
                lastSorted.next = cur.next;
                cur.next = prev.next;
                prev.next = cur;
            }
            cur = lastSorted.next; // 此时 cur 为下一个待插入的元素
        }
        // 返回排好序的链表
        return dummy.next;
    }

}