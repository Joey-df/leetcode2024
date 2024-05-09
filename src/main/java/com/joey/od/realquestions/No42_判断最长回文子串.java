package com.joey.od.realquestions;

/**
 * @author pei.liu
 */
public class No42_判断最长回文子串 {

    public String longest(String s) {
        if (s == null || s.length() == 0) return "";
        char[] str = s.toCharArray();
        int[][] dp = dp(str);
        int start = -1, end = -1, len = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (dp[i][j] > len) {
                    len = dp[i][j];
                    start = i;
                    end = j;
                }
            }
        }
        return s.substring(start, end + 1);
    }

    //dp[i][j]含义：如果str(i,j)范围是回文，那么它是多长
    public int[][] dp(char[] str) {
        int n = str.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
            if (i < n - 1) {
                dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 0;
            }
        }
        for (int i = n - 3; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                if (str[i] == str[j] && dp[i + 1][j - 1] > 0) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                }
            }
        }
        return dp;
    }
}
