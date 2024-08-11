package com.joey.leetcode;

import java.util.Arrays;

//329. 矩阵中的最长递增路径
//给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。
//对于每个单元格，你可以往上，下，左，右四个方向移动。
//你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。
public class Problem_0329_LongestIncreasingPathInAMatrix {

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int ans = 1;
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans = Math.max(ans, fun(matrix, i, j, dp));
            }
        }
        return ans;
    }

    //递归含义
    //从(i,j)开始走的最长递增路径有多长
    public static int fun(int[][] matrix, int i, int j, int[][] dp) {
        int n = matrix.length;
        int m = matrix[0].length;
        if (i < 0 || i >= n || j < 0 || j >= m) {
            return 0; //越界了
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        //(i,j)没越界
        int next = 0;
        if (i + 1 < n && matrix[i + 1][j] > matrix[i][j]) {
            next = Math.max(next, fun(matrix, i + 1, j, dp));
        }
        if (i - 1 >= 0 && matrix[i - 1][j] > matrix[i][j]) {
            next = Math.max(next, fun(matrix, i - 1, j, dp));
        }
        if (j + 1 < m && matrix[i][j + 1] > matrix[i][j]) {
            next = Math.max(next, fun(matrix, i, j + 1, dp));
        }
        if (j - 1 >= 0 && matrix[i][j - 1] > matrix[i][j]) {
            next = Math.max(next, fun(matrix, i, j - 1, dp));
        }
        int ans = next + 1;
        dp[i][j] = ans;
        return ans;
    }

}
