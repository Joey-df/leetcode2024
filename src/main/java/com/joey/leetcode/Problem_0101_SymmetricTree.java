package com.joey.leetcode;

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

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return is(root.left, root.right);
    }

    //判断a b是否镜面对称
    public boolean is(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null ^ b == null) return false;
        if (a.val != b.val) return false;
        return is(a.left, b.right) && is(a.right, b.left);
    }
}
