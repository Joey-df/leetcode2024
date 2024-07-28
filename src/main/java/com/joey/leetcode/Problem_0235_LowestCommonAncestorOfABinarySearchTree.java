package com.joey.leetcode;

/**
 * 235. 二叉搜索树的最近公共祖先
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：
 * “对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，
 * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 */
public class Problem_0235_LowestCommonAncestorOfABinarySearchTree {

    //递归
    //根据p q两个结点的值和root.val之间的关系判断
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        //p q位于root左右两侧，或者p q其中之一是root
        return root;
    }

    //迭代
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode cur = root;
        while (cur != null) {
            if (p.val > cur.val && q.val > cur.val) {
                cur = cur.right; //如果p q的值同时大于cur.val，在cur的右树找
            } else if (p.val < cur.val && q.val < cur.val) {
                cur = cur.left; //如果p q的值同时小于cur.val，在cur的左树找
            } else {
                return cur; //p q位于root左右两侧，或者p q其中之一是root
            }
        }
        return null;
    }

}
