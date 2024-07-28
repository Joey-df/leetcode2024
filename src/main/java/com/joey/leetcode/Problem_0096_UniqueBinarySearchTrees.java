package com.joey.leetcode;

/**
 * 96. 不同的二叉搜索树
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？
 * 返回满足题意的二叉搜索树的种数。
 *
 * Example:
 * Input: 3
 * Output: 5
 * Explanation:
 * Given n = 3, there are a total of 5 unique BST's:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 * Constraints:
 * 1 <= n <= 19
 */
public class Problem_0096_UniqueBinarySearchTrees {

//    public int numTrees(int n) {
//
//    }

    //递归含义：
    //val连续的n个节点，可以组成多少种不同的BST，返回
    public int fun(int n) {
        if (n==0 || n==1) return 1;
        if (n==2) return 2;
        //n>=3
        int ans = 0;
        for (int val = 1; val <= n; val++) {
            //枚举每个val，做为root节点的情况
            int left = fun(val - 1);
            int right = fun(n - val);
            ans += left * right;
        }
        return ans;
    }

    // 方法2：方法1的动态规划版本
    public int numTrees1(int n) {
        if (n==0 || n==1) return 1;
        if (n==2) return 2;
        int[] dp = new int[n+1]; //dp[k]: the number of BST trees with k consecutive(连续) nodes.
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int k = 3; k <= n; k++) {
            //枚举1～k每个值作为root
            //1做root时，左子树个数0（1-1），右子树个数k-1
            //2做root时，左子树个数1（2-1），右子树个数k-2
            //3做root时，左子树个数2（3-1），右子树个数k-3
            for (int root = 1; root <= k; root++) {
                dp[k] += dp[root-1] * dp[k-root]; //左子树数量 * 右子树数量
            }
        }
        return dp[n];
    }

    // 方法2：95题思想的暴力尝试
    public int numTrees2(int n) {
        return fun(1,n);
    }

    //递归含义
    //[start,end]连续的闭区间上 生成BST有多少种，返回
    public int fun(int start, int end) {
        if (start>end) {
            return 1; //空树
        } else if(start==end) {
            return 1; //单节点的树
        } else {
            int ans = 0;
            for (int i = start; i <= end; i++) {
                int left = fun(start, i - 1);
                int right = fun(i + 1, end);
                ans += left*right;
            }
            return ans;
        }
    }

}
