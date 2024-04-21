package com.joey.top_hot;

/**
 * 680. 验证回文串 II
 * 给你一个字符串 s，最多 可以从中删除一个字符。
 * 请你判断 s 是否能成为回文字符串：如果能，返回 true ；否则，返回 false 。
 */
public class Problem_0680_ValidPalindrome {

    public boolean validPalindrome(String s) {
        if (s == null || s.length() < 2) return true;
        char[] str = s.toCharArray();
        int n = str.length;
        int l = 0, r = n - 1;
        while (l < r) {
            if (str[l] != str[r]) {
                return is(str, l + 1, r) || is(str, l, r - 1);
            }
            l++;
            r--;
        }
        return true;
    }

    public boolean is(char[] str, int l, int r) {
        while (l < r) {
            if (str[l] != str[r]) return false;
            l++;
            r--;
        }
        return true;
    }

}
