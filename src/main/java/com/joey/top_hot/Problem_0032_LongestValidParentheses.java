package com.joey.top_hot;

//最长有效括号
//大厂14节
public class Problem_0032_LongestValidParentheses {
    public static int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] dp = new int[N];//dp[i]:以i位置结尾的有效子串有多长
        dp[0] = 0;
        int pre;
        int ans = 0;
        for (int i = 1; i < N; i++) {
            if (str[i] == ')') {
                //pre的含义：当前i位置的) 应该找哪个位置的(
                pre = i - dp[i - 1] - 1;
                if (pre >= 0 && str[pre] == '(') {
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
                ans = Math.max(ans, dp[i]);
            }
        }
        return ans;
    }
}
