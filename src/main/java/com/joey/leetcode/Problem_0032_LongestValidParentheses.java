package com.joey.leetcode;

//最长有效括号
//大厂14节
public class Problem_0032_LongestValidParentheses {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) return 0;
        char[] str = s.toCharArray();
        int n = str.length;
        int[] dp = new int[n];//dp[i]:以i位置结尾的有效括号有多长
        int pre; //含义：当前的) 应该找哪个 (配对
        int ans = 0;
        dp[0] = 0;
        for (int i = 1; i < n; i++) {
            if (str[i] == ')') {
                pre = i - dp[i - 1] - 1;
                if (pre >= 0 && str[pre] == '(') {
                    dp[i] = dp[i - 1] + 2;
                    if (pre > 0) {
                        dp[i] += dp[pre - 1];
                    }
                    ans = Math.max(ans, dp[i]);
                }
            }
        }
        return ans;
    }
}
