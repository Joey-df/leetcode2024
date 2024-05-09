package com.joey.top_hot;

import java.util.Stack;

/**
 * 230. 二叉搜索树中第K小的元素
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，
 * 请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 */
public class Problem_0230_KthSmallestElementInBST {

    //中序遍历的结果，数够第k个返回
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        int count = 0;
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.add(cur);
                cur = cur.left;
            } else {
                TreeNode node = stack.pop();
                if (++count == k) { //数够第k个返回
                    return node.val;
                }
                if (node.right != null) {
                    cur = node.right;
                }
            }
        }
        return -1;
    }

}
