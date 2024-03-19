package com.joey.top_hot;


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

    public static Node connect(Node root) {
        if (root==null) return null;
        LinkedList<Node> q = new LinkedList<>();
        q.offerLast(root);
        while(!q.isEmpty()) {
            int size = q.size();
            while(size > 0) {
                Node cur = q.pollFirst();
                if (size==1) {
                    cur.next = null;
                } else {
                    cur.next = q.peek();
                }
                if (cur.left!=null) q.offerLast(cur.left);
                if (cur.right!=null) q.offerLast(cur.right);
                size--;
            }
        }
        return root;
    }
}
