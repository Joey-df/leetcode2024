package com.joey.top_hot;

/**
 * 62. 不同路径
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 */
public class Problem_0062_UniquePaths {
    // 行列样本对应模型
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        //dp[i][j]含义
        //从(0,0)出发，到达(i,j)位置的方法数
        dp[0][0] = 1;
        for (int col = 1; col < n; col++) {
            //第一行只能是从左边一个位置向右走一步的结果
            dp[0][col] = dp[0][col - 1];
        }

        for (int row = 1; row < m; row++) {
            //第一列：只能是从上方的位置向下走一步的结果
            dp[row][0] = dp[row - 1][0];
        }

        //普遍位置
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
