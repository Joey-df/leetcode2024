package com.joey.leetcode;

//125. 验证回文串
//如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。
//字母和数字都属于字母数字字符。
//给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false 。
//
//示例 1：
//输入: s = "A man, a plan, a canal: Panama"
//输出：true
//解释："amanaplanacanalpanama" 是回文串。
public class Problem_0125_ValidPalindrome {

    public static boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) return true;
        char[] str = s.toCharArray();
        //预处理一下
        StringBuilder builder = new StringBuilder();
        for (char c : str) {
            if (c >= 'a' && c <= 'z') {
                builder.append(c);
            }
            if (c >= '0' && c <= '9') {
                builder.append(c);
            }
            if (c >= 'A' && c <= 'Z') {
                builder.append((char) (c + 32)); //to lower case
            }
        }
        return judge(builder.toString().toCharArray());
    }

    public static boolean judge(char[] arr) {
        if (arr == null || arr.length == 0) return true;
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            if (arr[l] != arr[r]) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}
