package com.joey.leetcode;

//221. 最大正方形
//在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
//Example 1:
//Input: matrix =
// [
// ["1","0","1","0","0"],
// ["1","0","1","1","1"],
// ["1","1","1","1","1"],
// ["1","0","0","1","0"]
// ]
//Output: 4
public class Problem_0221_MaximalSquare {

    public static int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int N = matrix.length;
        int M = matrix[0].length;
        //dp[i][j]含义：
        //以(i,j)做右下角，最大正方形的边长，是多长
        int[][] dp = new int[N][M];
        dp[0][0] = matrix[0][0] == '1' ? 1 : 0;
        int max = dp[0][0]; //最大边长
        //第一行
        for (int i = 1; i < M; i++) {
            dp[0][i] = matrix[0][i] - '0';
            max = Math.max(max, dp[0][i]);
        }
        //第一列
        for (int i = 1; i < N; i++) {
            dp[i][0] = matrix[i][0] - '0';
            max = Math.max(max, dp[i][0]);
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                if (matrix[i][j] == '1') {
                    //状态转移：左边 上边 左上角 三者取最小值，最后再+1
                    dp[i][j] = Math.min(dp[i - 1][j - 1], (Math.min(dp[i - 1][j], dp[i][j - 1]))) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max * max;
    }

}
