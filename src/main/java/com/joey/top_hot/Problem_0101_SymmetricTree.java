package com.joey.top_hot;

import tree.TreeNode;

/**
 * 101. 对称二叉树
 * 给定一个二叉树，检查它是否是镜像对称的。
 * 例如，二叉树[1,2,2,3,4,4,3] 是对称的。
 *      1
 *     / \
 *    2   2
 *   / \ / \
 *  3  4 4  3
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/symmetric-tree
 */
public class Problem_0101_SymmetricTree {

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return is(root.left, root.right);
    }

    //判断两个树是否镜面对称
    private static boolean is(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null ^ t2 == null) return false;
        if (t1.val != t2.val) return false;
        return is(t1.left, t2.right) && is(t1.right, t2.left);
    }
}
