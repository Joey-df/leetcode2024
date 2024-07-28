package com.joey.leetcode;

/**
 * 62. 不同路径
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 */
public class Problem_0062_UniquePaths {
    public static int uniquePaths(int m, int n) {
        //行列模型
        //dp[i][j]含义：机器人从(0,0)出发到达(i,j)的方法数
        int[][] dp = new int[m][n];
        dp[0][0] = 1; // 从(0,0)出发到达(0,0)，一种方法
        for (int row = 1; row < m; row++) {
            dp[row][0] = dp[row - 1][0]; // 第一列，从上面一步走下来
        }
        for (int col = 1; col < n; col++) {
            dp[0][col] = dp[0][col - 1]; // 第一行，从左边一步走过来
        }
        for (int row = 1; row < m; row++) {
            for (int col = 1; col < n; col++) {
                dp[row][col] = dp[row - 1][col] + dp[row][col - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
