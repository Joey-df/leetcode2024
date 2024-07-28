package com.joey.leetcode;

import java.util.Stack;

//285. 二叉搜索树中的中序后继
//给定一棵二叉搜索树和其中的一个节点 p ，找到该节点在树中的中序后继。如果节点没有中序后继，请返回 null 。
//节点 p 的后继是值比 p.val 大的节点中键值最小的节点，即按中序遍历的顺序节点 p 的下一个节点。
public class Problem_0285_InorderSuccessorInBST {

    public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p==null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        boolean found = false;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode node = stack.pop();
                if (found) {
                    return node;
                }
                if (node == p) {
                    found = true;
                }
                cur = node.right;
            }
        }
        return null;
    }

}
