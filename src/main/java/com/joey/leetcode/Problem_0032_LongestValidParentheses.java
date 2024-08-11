package com.joey.leetcode;

//32. 最长有效括号
//给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
public class Problem_0032_LongestValidParentheses {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) return 0;
        char[] str = s.toCharArray();
        int n = str.length;
        int[] dp = new int[n]; //dp[i]:必须以i位置结尾的最长有效括号串有多长
        dp[0] = 0;
        int ans = 0;
        for (int i = 1; i < n; i++) {
            if (str[i] == ')') { //以(结尾的答案一定是0
                int p = i - dp[i - 1] - 1; //目标位置
                if (p >= 0 && str[p] == '(') {
                    dp[i] = dp[i - 1] + 2;
                    if (p - 1 >= 0) {
                        dp[i] += dp[p - 1];
                    }
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
