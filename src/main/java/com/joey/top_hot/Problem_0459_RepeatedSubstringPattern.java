package com.joey.top_hot;

/**
 * @author pei.liu
 */
//459. 重复的子字符串
//给定一个非空的字符串 s ，检查是否可以通过由它的一个子串重复多次构成。
//
//示例 1:
//输入: s = "abab"
//输出: true
//解释: 可由子串 "ab" 重复两次构成。
//示例 2:
//输入: s = "aba"
//输出: false
//示例 3:
//输入: s = "abcabcabcabc"
//输出: true
//解释: 可由子串 "abc" 重复四次构成。 (或子串 "abcabc" 重复两次构成。)
//
//提示：
//1 <= s.length <= 10^4
//s 由小写英文字母组成
public class Problem_0459_RepeatedSubstringPattern {

    //方法1：
    //枚举所有以0位置开头的子串，是否可以分解s
    //注意：每个子串的长度 需要被s的长度整除
    //复杂度：O(N^2)
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        //只需要枚举到一半的位置即可，因为如果能分解，s至少被分解为2段
        //[0,i-1]为枚举的子串
        for (int i = 1; i <= n / 2; i++) {
            if (n % i == 0) { //n必须是i的倍数，当前子串才有可能被分解
                //当前的子串串是否可以分解s
                boolean match = true;
                for (int j = i; j < n; j++) {
                    if (s.charAt(j) != s.charAt(j - i)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    return true;
                }
            }
        }
        return false;
    }

    //拼接+移除匹配
    //复杂度：O(2N)
    public boolean repeatedSubstringPattern2(String s) {
        String str = s + s;
        int n = str.length();
        return str.substring(1, n - 1).contains(s);
    }

}
