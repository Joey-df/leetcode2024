package com.joey.leetcode;


/**
 * 437. 路径总和 III
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * <p>
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 */
public class Problem_0437_PathSumIII {

    //以root为根的树上，累加和为targetSum的路径总数返回
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) return 0;
        int count = 0;
        count += helper(root, targetSum, 0); //必须以root出发的路径总数
        count += pathSum(root.left, targetSum) //以left为根的树上，累加和为targetSum的路径总数
                + pathSum(root.right, targetSum); //以right为根的树上，累加和为targetSum的路径总数
        return count;
    }

    //递归含义
    //当前得到的累加和为curSum
    //必须以root出发得到的路径数，返回
    public int helper(TreeNode X, int targetSum, long curSum) {
        if (X == null) return 0;
        int count = 0;
        curSum += X.val;
        if (curSum == targetSum) {
            count++;
        }
        count += helper(X.left, targetSum, curSum);
        count += helper(X.right, targetSum, curSum);
        return count;
    }

}
