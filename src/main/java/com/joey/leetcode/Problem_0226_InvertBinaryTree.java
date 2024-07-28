package com.joey.leetcode;


//226. 翻转二叉树
public class Problem_0226_InvertBinaryTree {
    //递归含义：
    //给定根节点root，返回反转后树的根节点
    public TreeNode invertTree(TreeNode root) {
        if (root==null) return null;
        TreeNode left=root.left;
        root.left = invertTree(root.right); //右子树翻转挂到左树
        root.right = invertTree(left); //左子树翻转挂到右树
        return root;
    }
}
