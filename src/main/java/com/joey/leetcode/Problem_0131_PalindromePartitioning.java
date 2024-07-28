package com.joey.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 131. 分割回文串
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 * <p>
 * 回文串 是正着读和反着读都一样的字符串。
 * <p>
 * 示例 1：
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * <p>
 * 示例 2：
 * 输入：s = "a"
 * 输出：[["a"]]
 */
public class Problem_0131_PalindromePartitioning {

    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        if (s == null || s.length() == 0) return ans;
        boolean[][] dp = is(s);
        fun(s, 0, ans, new ArrayList<>(), dp);
        return ans;
    }

    //str[0,index-1]已经搞定了，沿途的路径存在path里
    //ans收集答案
    private void fun(String str, int index, List<List<String>> ans, ArrayList<String> path, boolean[][] dp) {
        if (index == str.length()) {
            //collect ans
            ans.add(new ArrayList<>(path));
        } else {
            for (int end = index; end < str.length(); end++) {
                if (dp[index][end]) {
                    path.add(str.substring(index, end + 1));
                    fun(str, end + 1, ans, path, dp);
                    path.remove(path.size() - 1); // clear
                }
            }
        }
    }

    private boolean[][] is(String s) {
        char[] str = s.toCharArray();
        int n = str.length;
        //dp[i][j]: str(i,j)范围是不是回文
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = true; // 对角线
            if (i < n - 1) {
                dp[i][i + 1] = str[i] == str[i + 1]; //倒数第二条对角线
            }
        }
        for (int i = n - 3; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                dp[i][j] = str[i] == str[j] && dp[i + 1][j - 1];
            }
        }
        return dp;
    }

}
