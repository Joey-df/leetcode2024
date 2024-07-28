package com.joey.leetcode;

/**
 * @author pei.liu
 */
//392. 判断子序列
//给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
//字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
//进阶：
//如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
//
//示例 1：
//输入：s = "abc", t = "ahbgdc"
//输出：true
//示例 2：
//输入：s = "axc", t = "ahbgdc"
//输出：false
//
//提示：
//
//0 <= s.length <= 100
//0 <= t.length <= 10^4
//两个字符串都只由小写字符组成。
public class Problem_0392_IsSubsequence {

    public boolean isSubsequence(String s, String t) {
        if (s.isEmpty()) return true;
        if (t.isEmpty()) return false;

        //都不为空
        int n = s.length();
        int m = t.length();
        if (n > m) return false;
        int i = 0;
        int j = 0;
        while (i < n && j < m) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else {
                j++;
            }
        }
        return i == n;
    }

}
