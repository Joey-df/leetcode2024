package com.joey.top_hot;

/**
 * 329. 矩阵中的最长递增路径
 * 给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。
 * <p>
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。
 * 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。
 */
public class Problem_0329_LongestIncreasingPathInAMatrix {

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int ans = 1;
        int[][] dp = new int[matrix.length][matrix[0].length];
        //dp[i][j]==0表示没算过，但凡算过 值至少是1
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                ans = Math.max(ans, func(matrix, i, j, dp));
            }
        }
        return ans;
    }

    //递归含义
    //从m[i][j]出发走出的最长递增链有多长
    private static int func(int[][] m, int r, int c, int[][] dp) {
        if (r < 0 || r >= m.length || c < 0 || c >= m[0].length) {
            return 0;
        }
        if (dp[r][c] != 0) {
            return dp[r][c];
        }
        int next = 0;
        if (r > 0 && m[r - 1][c] > m[r][c]) {
            next = Math.max(next, func(m, r - 1, c, dp));
        }
        if (r < m.length - 1 && m[r + 1][c] > m[r][c]) {
            next = Math.max(next, func(m, r + 1, c, dp));
        }
        if (c > 0 && m[r][c - 1] > m[r][c]) {
            next = Math.max(next, func(m, r, c - 1, dp));
        }
        if (c < m[0].length - 1 && m[r][c + 1] > m[r][c]) {
            next = Math.max(next, func(m, r, c + 1, dp));
        }
        dp[r][c] = 1 + next;
        return 1 + next;
    }

}
