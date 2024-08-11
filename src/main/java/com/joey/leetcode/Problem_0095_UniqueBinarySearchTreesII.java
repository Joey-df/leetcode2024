package com.joey.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 95. 不同的二叉搜索树II(收集所有答案)
 * 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。
 * 可以按 任意顺序 返回答案。
 * 示例 1：
 * 输入：n = 3
 * 输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
 * 示例 2：
 * 输入：n = 1
 * 输出：[[1]]
 * 提示：
 * 1 <= n <= 8
 */
//好题，孪生题 96题
public class Problem_0095_UniqueBinarySearchTreesII {

    //返回1～n范围上互不相同的值组成的BST
    public List<TreeNode> generateTrees(int n) {
        if (n <= 0) return new ArrayList<>();
        return fun(1, n);
    }

    //收集[s,e]范围上所有可能的BST，放到ans中返回
    public List<TreeNode> fun(int s, int e) {
        List<TreeNode> ans = new ArrayList<>();
        if (s > e) {
            ans.add(null);
        } else if (s == e) {
            ans.add(new TreeNode(s));
        } else { // s < e
            for (int i = s; i <= e; i++) {
                List<TreeNode> left = fun(s, i - 1);
                List<TreeNode> right = fun(i + 1, e);
                for (TreeNode l : left) {
                    for (TreeNode r : right) {
                        TreeNode root = new TreeNode(i); //枚举每个root，注意这行的位置
                        root.left = l;
                        root.right = r;
                        ans.add(root);
                    }
                }
            }
        }
        return ans;
    }


}
