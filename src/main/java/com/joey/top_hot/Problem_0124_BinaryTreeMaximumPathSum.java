package com.joey.top_hot;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * 124. 二叉树中的最大路径和
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。
 * 同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 * <p>
 * 路径和 是路径中各节点值的总和。
 * <p>
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 */
public class Problem_0124_BinaryTreeMaximumPathSum {

    public static class Info {
        int maxSumAllTree; // 整棵树上的最大路径和
        int maxSumFromRoot; // 从根结点往下扎的最大路径和

        public Info(int maxSumAllTree, int maxSumFromRoot) {
            this.maxSumAllTree = maxSumAllTree;
            this.maxSumFromRoot = maxSumFromRoot;
        }
    }

    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        return fun(root).maxSumAllTree;
    }

    private Info fun(TreeNode x) {
        if (x == null) return null;
        Info linfo = fun(x.left);
        Info rinfo = fun(x.right);
        int p1 = Integer.MIN_VALUE;
        int p2 = Integer.MIN_VALUE;
        if (linfo != null) {
            p1 = linfo.maxSumAllTree; //左树的整体最大值
            p2 = x.val + linfo.maxSumFromRoot; //从x结点往左下扎
        }
        int p3 = Integer.MIN_VALUE;
        int p4 = Integer.MIN_VALUE;
        if (rinfo != null) {
            p3 = rinfo.maxSumAllTree; //右树的整体最大值
            p4 = x.val + rinfo.maxSumFromRoot; //从x结点往右下扎
        }
        int p5 = Integer.MIN_VALUE;
        if (linfo != null && rinfo != null) {
            p5 = x.val + linfo.maxSumFromRoot + rinfo.maxSumFromRoot; //经过x穿越左右子树
        }
        int p6 = x.val; //只包含x结点
        int maxSumAllTree = Math.max(p1, Math.max(p2, Math.max(p3, Math.max(p4, Math.max(p5, p6)))));
        int maxSumFromRoot = Math.max(p2, Math.max(p4, p6));
        return new Info(maxSumAllTree, maxSumFromRoot);
    }
}
