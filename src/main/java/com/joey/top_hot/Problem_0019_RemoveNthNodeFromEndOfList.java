package com.joey.top_hot;

//给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
//提示：
//链表中结点的数目为 sz
//1 <= sz <= 30
//0 <= Node.val <= 100
//1 <= n <= sz
public class Problem_0019_RemoveNthNodeFromEndOfList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode s = dummy, f = dummy;
        //f先走n+1步，然后s和f同步走，f都到null时，删除s的下一个结点即可
        for (int i = 0; i <= n; i++) f = f.next;
        while (f != null) {
            s = s.next;
            f = f.next;
        }
        s.next = s.next.next;
        return dummy.next;
    }
}
