package com.joey.top_hot;

import tree.TreeNode;

/**
 * 687. 最长同值路径
 * 给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。
 * 这条路径可以经过也可以不经过根节点。
 * 注意：两个节点之间的路径长度由它们之间的边数表示。
 * 示例 1:
 * 输入:
 *
 *      5
 *     / \
 *    4   5
 *   / \   \
 *  1   1   5
 * 输出:
 * 2
 *
 * 示例 2:
 * 输入:
 *      1
 *     / \
 *    4   5
 *   / \   \
 *  4   4   5
 * 输出:
 * 2
 * 注意: 给定的二叉树不超过10000个结点。 树的高度不超过1000。
 */
public class Problem_0687_LongestUnivaluePath {

    //同值路径的长度 = 同值路径节点数-1
    private static class Info {
        int fromRoot; // 必须从头节点出发，往下扎，同值路径的节点数
        int allMax;  // 不要求从头节点出发 整棵树上 同值路径的节点数(可以经过头，可以从头出发，也可以不经过头，整棵树最大)

        public Info(int a, int b) {
            this.fromRoot = a;
            this.allMax = b;
        }
    }

    //用二叉树递归套路解
    public static int longestUnivaluePath(TreeNode root) {
        if (root==null) return 0;
        return process(root).allMax - 1;
    }

    public static Info process(TreeNode x) {
        if (x==null) {
            return new Info(0,0);
        }
        TreeNode left = x.left;
        TreeNode right = x.right;
        Info l = process(left);
        Info r = process(right);
        int fromRoot = 1; //只包含头节点
        if (left!=null && left.val==x.val) { //从头节点出发往左孩子扎
            fromRoot = Math.max(fromRoot, l.fromRoot + 1);
        }
        if (right!=null && right.val==x.val) { //从头节点出发往右孩子扎
            fromRoot = Math.max(fromRoot, r.fromRoot + 1);
        }

        int allMax = Math.max(fromRoot, Math.max(l.allMax, r.allMax)); //五种情况pk
        if (left!=null && left.val==x.val && right!=null && right.val==x.val) {
            allMax = Math.max(allMax, l.fromRoot+r.fromRoot+1);
        }

        return new Info(fromRoot, allMax);
    }
}
