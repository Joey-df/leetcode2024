package com.joey.leetcode;

import java.util.ArrayList;
import java.util.List;

//113. 路径总和 II
//给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
//叶子节点 是指没有子节点的节点。
public class Problem_0113_PathSumII {

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        dfs(root, targetSum, ans, new ArrayList<>());
        return ans;
    }

    private void dfs(TreeNode root, int target, List<List<Integer>> ans, ArrayList<Integer> path) {
        if (root == null) return;
        path.add(root.val); // 第一次到达add
        // 叶子结点时收集答案
        if (root.left == null && root.right == null) {
            if (root.val == target) {
                ans.add(new ArrayList<>(path));
            }
        }
        dfs(root.left, target - root.val, ans, path);
        dfs(root.right, target - root.val, ans, path);
        //最后一次到达remove
        path.remove(path.size() - 1);
    }
}
