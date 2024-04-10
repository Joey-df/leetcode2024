package com.joey.top_hot;

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 */
//manacher
public class Problem_0005_LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        if (s.length() == 1) return s;
        char[] str = s.toCharArray();
        int n = str.length;
        int[][] dp = getDP(str);
        int maxLen = 0;
        int start = -1, end = -1;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (dp[i][j] > maxLen) {
                    maxLen = dp[i][j];
                    start = i;
                    end = j;
                }
            }
        }
        return s.substring(start, end + 1);
    }

    private int[][] getDP(char[] str) {
        //dp[i][j]含义：str[i][j]这一段如果是回文，长度是多少
        int n = str.length;
        int[][] dp = new int[n][n];
        //倒数第一二条对角线
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
            if (i < n - 1)
                dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 0;
        }
        //普通位置
        for (int i = n - 3; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                dp[i][j] = str[i] == str[j] && dp[i + 1][j - 1] > 0 ? dp[i + 1][j - 1] + 2 : 0;
            }
        }
        return dp;
    }

}
