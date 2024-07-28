package com.joey.leetcode;

/**
 * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
 */
//manacher
public class Problem_0516_LongestPalindromicSubsequence {

    public int longestPalindromeSubseq(String s) {
        if (s == null || "".equals(s)) return 0;
        char[] str = s.toCharArray();
        int n = str.length;
        //dp[i][j]的含义：str[i...j]这个范围上的最长回文子序列有多长
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
            if (i < n - 1) {
                dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
            }
        }
        //普遍位置
        for (int i = n - 3; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                dp[i][j] = Math.max(dp[i + 1][j - 1], Math.max(dp[i][j - 1], dp[i + 1][j]));
                if (str[i] == str[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1] + 2);
                }
            }
        }
        return dp[0][n - 1];
    }

}
