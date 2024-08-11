package com.joey.leetcode;

/**
 * @author pei.liu
 */
//1312. 让字符串成为回文串的最少插入次数
//给你一个字符串 s ，每一次操作你都可以在字符串的任意位置插入任意字符。
//请你返回让 s 成为回文串的 最少操作次数 。
//「回文串」是正读和反读都相同的字符串。
//
//示例 1：
//输入：s = "zzazz"
//输出：0
//解释：字符串 "zzazz" 已经是回文串了，所以不需要做任何插入操作。
//示例 2：
//输入：s = "mbadm"
//输出：2
//解释：字符串可变为 "mbdadbm" 或者 "mdbabdm" 。
//示例 3：
//输入：s = "leetcode"
//输出：5
//解释：插入 5 个字符后字符串变为 "leetcodocteel" 。
//
//提示：
//1 <= s.length <= 500
//s 中所有字符都是小写字母。
public class Problem_1312_MinimumInsertionToPalindrome {

    //区间dp
    //范围上的尝试模型
    //s[l,r]范围上变成回文最少需要添加几个字符
    public int minInsertions(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int n = str.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 0; //对角线
            if (i > 0) {
                dp[i - 1][i] = str[i - 1] == str[i] ? 0 : 1; //倒数第二条对角线
            }
        }
        //普通位置
        for (int l = n - 3; l >= 0; l--) {
            for (int r = l + 2; r < n; r++) {
                if (str[l] == str[r]) {
                    dp[l][r] = dp[l + 1][r - 1];
                } else {
                    dp[l][r] = Math.min(dp[l + 1][r], dp[l][r - 1]) + 1;
                }
            }
        }
        return dp[0][n - 1];
    }

}
