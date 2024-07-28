package com.joey.leetcode;


import java.util.LinkedList;

/**
 * 116. 填充每个节点的下一个右侧节点指针
 * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。
 * 如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 *
 * 初始状态下，所有 next 指针都被设置为 NULL。
 */

public class Problem_0116_PopulatingNextRightPointersInEachNode {

    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        if (root == null) return null;
        LinkedList<Node> q = new LinkedList<>();
        q.offer(root);
        int size;
        while (!q.isEmpty()) {
            size = q.size();
            for (int i = 0; i < size; i++) {
                Node node = q.pollFirst();
                node.next = i == size - 1 ? null : q.peekFirst();
                if (node.left != null) q.offerLast(node.left);
                if (node.right != null) q.offerLast(node.right);
            }
        }
        return root;
    }
}
