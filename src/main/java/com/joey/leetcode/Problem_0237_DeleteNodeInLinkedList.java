package com.joey.leetcode;

//237. 删除链表中的节点
//请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点。传入函数的唯一参数为 要被删除的节点 。
public class Problem_0237_DeleteNodeInLinkedList {

    //此方法的局限性：
    //1、不能删除末尾节点
    //2、借尸还魂，实际删除的不是目标节点本身，而是其下一个节点
    //3、如果是节点是服务器，生产中的服务器下线，不可能通过这种方式达到目的
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

}
