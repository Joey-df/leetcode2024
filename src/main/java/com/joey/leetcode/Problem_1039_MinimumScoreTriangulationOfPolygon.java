package com.joey.leetcode;

import java.util.Arrays;

/**
 * @author pei.liu
 */
//1039. 多边形三角剖分的最低得分
//你有一个凸的 n 边形，其每个顶点都有一个整数值。给定一个整数数组 values ，其中 values[i] 是第 i 个顶点的值（即 顺时针顺序 ）。
//假设将多边形 剖分 为 n - 2 个三角形。对于每个三角形，该三角形的值是顶点标记的乘积，三角剖分的分数是进行三角剖分后所有 n - 2 个三角形的值之和。
//返回 多边形进行三角剖分后可以得到的最低分 。
public class Problem_1039_MinimumScoreTriangulationOfPolygon {

    //枚举每个分割点，求全局最小值
    //范围上的尝试模型
    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return fun(values, 0, n - 1, dp);
    }

    //返回values[l,r]范围上最小的得分
    public static int fun(int[] values, int l, int r, int[][] dp) {
        if (l > r) return 0;
        if (l == r) return 0; //1个点
        if (l + 1 == r) return 0; //两个点
        if (dp[l][r] != -1) return dp[l][r];
        int ans = Integer.MAX_VALUE;
        for (int p = l + 1; p < r; p++) { //枚举分割点
            ans = Math.min(ans, fun(values, l, p, dp) + fun(values, p, r, dp) + values[p] * values[l] * values[r]);
        }
        dp[l][r] = ans;
        return ans;
    }

    public int minScoreTriangulation2(int[] values) {
        int n = values.length;
        int[][] dp = new int[n][n];
        for (int l = n - 3; l >= 0; l--) {
            for (int r = l + 2; r < n; r++) {
                dp[l][r] = Integer.MAX_VALUE;
                for (int p = l + 1; p < r; p++) {
                    dp[l][r] = Math.min(dp[l][r], dp[l][p] + dp[p][r] + values[p] * values[l] * values[r]);
                }
            }
        }
        return dp[0][n - 1];
    }


}
