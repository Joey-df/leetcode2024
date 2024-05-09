package com.joey.top_hot;

/**
 * 174. 地下城游戏
 * 给定一个二维数组 map，含义是一张地图，例如，如下矩阵:
 * -2 -3   3
 * -5 -10  1
 * 0  30 -5
 * 游戏的规则如下:
 * 骑士从左上角出发，每次只能向右或向下走，最后到达右下角见到公主。
 * 地图中每个位置的值代表骑士要遭遇的事情。
 * 如果是负数，说明此处有怪兽，要让骑士损失血量。
 * 如果是非负数，代表此处有血瓶，能让骑士回血。
 * 骑士从左上角到右下角的过程中，走到任何一个位置时，血量都不能少于1。
 * 为了保证骑士能见到公主，初始血量至少是多少?根据map，返回至少的初始血量。
 */
//LeetCode 174 地牢游戏（H）
//https://leetcode.com/problems/dungeon-game/
public class Problem_0174_DungeonGame {

    public int calculateMinimumHP(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        // return fun(matrix, 0, 0);
        return dpWays(matrix);
    }

    //暴力尝试
    //递归含义：
    //骑士即将登上[row,col]位置，出发到达右下角，至少的初始血量是多少？
    public static int fun(int[][] matrix, int row, int col) {
        int N = matrix.length;
        int M = matrix[0].length;
        //base case
        if (row == N - 1 && col == M - 1) {
            if (matrix[row][col] < 0) {
                return -matrix[row][col] + 1;
            } else {
                return 1; //至少得1滴血登上[row,col]
            }
        }
        //还没有到右下角
        //到达最后一行，只往右走
        if (row == N - 1) {
            int rightNeed = fun(matrix, row, col + 1); //右侧的格子能通关需要的最小血量，必是大于0的，比如是3
            if (matrix[row][col] < 0) {
                return -matrix[row][col] + rightNeed; // 如matrix[row][col]=-5，rightNeed=3
            } else if (matrix[row][col] < rightNeed) {//1 3
                return rightNeed - matrix[row][col];
            } else { //matrix[row][col] >= rightNeed // 3 3 or 4 3
                return 1;//保证有1滴血不死能登上[row][col]即可
            }
        }

        //到达最后一列，只往下走
        if (col == M - 1) {
            int downNeed = fun(matrix, row + 1, col); // 3
            if (matrix[row][col] < 0) {
                return -matrix[row][col] + downNeed; // -5 3
            } else if (matrix[row][col] < downNeed) {//1 3
                return downNeed - matrix[row][col];
            } else { //matrix[row][col] >= downNeed // 3 3 or 4 3
                return 1;//保证有1滴血不死能登上[row][col]即可
            }
        }

        //普通位置，可以往下走 也可以往右走
        int rightNeed = fun(matrix, row, col + 1); // 3
        int downNeed = fun(matrix, row + 1, col); // 3
        int minNeed = Math.min(rightNeed, downNeed);
        if (matrix[row][col] < 0) {
            return -matrix[row][col] + minNeed; // -5 3
        } else if (matrix[row][col] < minNeed) {//1 3
            return minNeed - matrix[row][col];
        } else { //matrix[row][col] >= downNeed // 3 3 or 4 3
            return 1;//保证有1滴血不死能登上[row][col]即可
        }
    }

    //改动态规划
    //标准的行列模型
    public static int dpWays(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int N = matrix.length;
        int M = matrix[0].length;
        //dp[i][j]: 从(i,j)出发，到达右下角需要的最少血量是多少
        int[][] dp = new int[N][M];
        dp[N - 1][M - 1] = (matrix[N - 1][M - 1] < 0) ? (-matrix[N - 1][M - 1] + 1) : 1;

        //最后一行
        for (int col = M - 2; col >= 0; col--) {
            dp[N - 1][col] = 1;
            if (matrix[N - 1][col] < 0) {
                dp[N - 1][col] = -matrix[N - 1][col] + dp[N - 1][col + 1];
            } else if (matrix[N - 1][col] < dp[N - 1][col + 1]) {
                dp[N - 1][col] = dp[N - 1][col + 1] - matrix[N - 1][col];
            }
        }

        //最后一列
        for (int row = N - 2; row >= 0; row--) {
            dp[row][M - 1] = 1;
            if (matrix[row][M - 1] < 0) {
                dp[row][M - 1] = -matrix[row][M - 1] + dp[row + 1][M - 1];
            } else if (matrix[row][M - 1] < dp[row + 1][M - 1]) {
                dp[row][M - 1] = dp[row + 1][M - 1] - matrix[row][M - 1];
            }
        }

        //普通位置
        for (int row = N - 2; row >= 0; row--) {
            for (int col = M - 2; col >= 0; col--) {
                int rightNeed = dp[row][col + 1]; // 3
                int downNeed = dp[row + 1][col]; // 3
                int minNeed = Math.min(rightNeed, downNeed);
                dp[row][col] = 1;
                if (matrix[row][col] < 0) {
                    dp[row][col] = -matrix[row][col] + minNeed; // -5 3
                } else if (matrix[row][col] < minNeed) {//1 3
                    dp[row][col] = minNeed - matrix[row][col];
                }
            }
        }
        return dp[0][0];
    }
}
