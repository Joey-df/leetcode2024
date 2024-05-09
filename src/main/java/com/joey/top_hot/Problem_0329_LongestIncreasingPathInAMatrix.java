package com.joey.top_hot;

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
        int[][] dp = new int[n][m]; //傻缓存
        //dp[i][j]==0表示没算过，但凡算过 值至少是1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //枚举从每一个位置开始走的答案，求max就是答案
                ans = Math.max(ans, func(matrix, i, j, dp));
            }
        }
        return ans;
    }

    //递归含义
    //从matrix[i][j]出发走出的最长递增链有多长
    private static int func(int[][] matrix, int row, int col, int[][] dp) {
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length) {
            return 0; //走到越界了，返回0
        }
        if (dp[row][col] != 0) { //如果之前算过了，直接返回
            return dp[row][col];
        }
        int next = 0;
        if (row > 0 && matrix[row - 1][col] > matrix[row][col]) { //up
            next = Math.max(next, func(matrix, row - 1, col, dp));
        }
        if (row < matrix.length - 1 && matrix[row + 1][col] > matrix[row][col]) { //down
            next = Math.max(next, func(matrix, row + 1, col, dp));
        }
        if (col > 0 && matrix[row][col - 1] > matrix[row][col]) { //left
            next = Math.max(next, func(matrix, row, col - 1, dp));
        }
        if (col < matrix[0].length - 1 && matrix[row][col + 1] > matrix[row][col]) { //right
            next = Math.max(next, func(matrix, row, col + 1, dp));
        }
        dp[row][col] = 1 + next; //返回之前设置缓存
        return 1 + next;
    }

}
