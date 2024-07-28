package com.joey.leetcode;

//132. 分割回文串 II
//给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。
//返回符合要求的 最少分割次数 。
//示例 1：
//输入：s = "aab"
//输出：1
//解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
//示例 2：
//输入：s = "a"
//输出：0
//示例 3：
//输入：s = "ab"
//输出：1
//提示：
//1 <= s.length <= 2000
//s 仅由小写英文字母组成
// 给定字符串s，至少切几刀，使得到的部分都是回文串
public class Problem_0132_PalindromePartitioningII {

    //暴力尝试
    public static int minCut(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        boolean[][] dp = isP(s);
        return fun(s, 0, dp) - 1;
    }

    //递归含义
    //s[i...]从i出发到字符串结尾，分割成若干个回文串，最少会被切成几个
    public static int fun(String s, int i, boolean[][] dp) {
        int n = s.length();
        if (i == n) { //空串
            return 0;
        } else {
            int ans = Integer.MAX_VALUE;
            for (int end = i; end < n; end++) {
                if (dp[i][end]) {
                    int cur = fun(s, end + 1, dp);
                    ans = Math.min(ans, cur + 1);
                }
            }
            return ans;
        }
    }

    public static boolean[][] isP(String s) {
        char[] str = s.toCharArray();
        int n = str.length;
        boolean[][] dp = new boolean[n][n];
        dp[n - 1][n - 1] = true;
        for (int l = 0; l < n - 1; l++) {
            dp[l][l] = true;
            dp[l][l + 1] = str[l] == str[l + 1];
        }
        for (int l = n - 3; l >= 0; l--) {
            for (int r = l + 2; r < n; r++) {
                dp[l][r] = str[l] == str[r] && dp[l + 1][r - 1];
            }
        }
        return dp;
    }

    //动态规划正式方法
    public static int minCut2(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        boolean[][] isP = isP(s);
        char[] str = s.toCharArray();
        int n = str.length;
        int[] dp = new int[n + 1]; //dp[i]: s[i...]从i出发到字符串结尾，分割成若干个回文串，最少会被切成几个
        dp[n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            int ans = Integer.MAX_VALUE;
            for (int end = i; end < n; end++) {
                if (isP[i][end]) {
                    int cur = dp[end + 1];
                    ans = Math.min(ans, cur + 1);
                }
            }
            dp[i] = ans;
        }
        return dp[0] - 1;
    }

}
