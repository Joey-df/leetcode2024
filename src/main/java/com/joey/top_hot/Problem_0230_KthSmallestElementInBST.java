package com.joey.top_hot;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 230. 二叉搜索树中第K小的元素
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，
 * 请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 */
public class Problem_0230_KthSmallestElementInBST {

    //中序遍历的结果，数够第k个返回
    public static int kthSmallest(TreeNode root, int k) {
        ArrayList<Integer> inOrder = in(root);
        return inOrder.get(k-1);
    }

    //迭代遍历二叉树，中序遍历的结果收集到ans中返回
    public static ArrayList<Integer> in(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode node = stack.pop();
                ans.add(node.val);
                cur = node.right;
            }
        }
        return ans;
    }
}
