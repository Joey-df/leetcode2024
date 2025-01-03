package com.joey.leetcode;

/**
 * @author pei.liu
 */
//3110. 字符串的分数
//给你一个字符串 s 。一个字符串的 分数 定义为相邻字符 ASCII 码差值绝对值的和。
//请你返回 s 的 分数 。
//提示：
//2 <= s.length <= 100
//s 只包含小写英文字母。
public class Problem_3110_ScoreOfString {
    public int scoreOfString(String s) {
        int ans = 0;
        char[] str = s.toCharArray();
        int n = str.length;
        for (int i = 1; i < n; i++) {
            ans += Math.abs(str[i]-str[i-1]);
        }
        return ans;
    }
}
