package com.joey.top_hot;

import java.util.ArrayList;
import java.util.LinkedList;
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

    public static List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        boolean[][] dp = isP(s);
        LinkedList<String> path = new LinkedList<>();
        func(s, 0, path, ans, dp);
        return ans;
    }

    //递归含义
    //str为固定输入
    //当前来到index位置
    //str[0...index-1]已经搞定了，不用操心了
    //str[0...index-1]做的决定，沿途形成的路径，存在path里
    //ans用来收集答案
    public static void func(String str, int index, LinkedList<String> path, List<List<String>> ans, boolean[][] dp) {
        if (index == str.length()) {
            //表示str[0...N-1]已经搞定了，path为沿途的路径
            ans.add(new ArrayList<>(path));
        } else {
            for (int end = index; end < str.length(); end++) {
                if (dp[index][end]){
                    path.addLast(str.substring(index, end + 1));
                    func(str, end + 1, path, ans, dp);
                    path.pollLast();
                }
            }
        }
    }

    //s.length>0
    private static boolean[][] isP(String s) {
        char[] str = s.toCharArray();
        int N = str.length;
        boolean[][] dp = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            dp[i][i] = true;
            if (i > 0) {
                dp[i - 1][i] = str[i - 1] == str[i];
            }
        }
        for (int i = N - 3; i >= 0; i--) {
            for (int j = i + 2; j < N; j++) {
                dp[i][j] = str[i]==str[j] && dp[i+1][j-1];
            }
        }
        return dp;
    }

    public static void main(String[] args) {
        System.out.println(partition("abaKFK"));
    }
}
