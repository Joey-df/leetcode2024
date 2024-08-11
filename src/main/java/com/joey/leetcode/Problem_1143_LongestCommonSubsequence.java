package com.joey.leetcode;

/**
 * 1143. 最长公共子序列
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 */
public class Problem_1143_LongestCommonSubsequence {

    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || "".equals(text1) || text2 == null || "".equals(text2)) return 0;
        char[] str1 = text1.toCharArray();
        char[] str2 = text2.toCharArray();
        int n = str1.length;
        int m = str2.length;
        // dp[i][j]的含义：str1[0...i]与str2[0...j]最长公共子序列有多长
        int[][] dp = new int[n][m];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < m; i++) { //第一行
            dp[0][i] = str1[0] == str2[i] ? 1 : dp[0][i - 1];
        }
        for (int i = 1; i < n; i++) { // 第一列
            dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
        }
        //普遍位置
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (str1[i] == str2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n - 1][m - 1];
    }
}
