package com.joey.leetcode;

/**
 * @author pei.liu
 */
//97. 交错字符串
//给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
//两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串
//示例 1：
//输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
//输出：true
//示例 2：
//输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
//输出：false
//示例 3：
//输入：s1 = "", s2 = "", s3 = ""
//输出：true
//
//提示：
//0 <= s1.length, s2.length <= 100
//0 <= s3.length <= 200
//s1、s2、和 s3 都由小写英文字母组成
public class Problem_0097_InterleavingString {


    //思路：s1的前i个，s2的前j个，能否交错组成s3的前i+j个
    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length();
        int m = s2.length();
        int len = s3.length();
        if (n + m != len) {
            return false;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        char[] str3 = s3.toCharArray();
        boolean[][] dp = new boolean[n + 1][m + 1];
        //左上角
        dp[0][0] = true; //s2前0个+s2前0个能组成s3的前0个
        //第一行:s1[前0个] + s2[前j个] -> s3[前j个]
        for (int j = 1; j <= m; j++) {
            dp[0][j] = dp[0][j - 1] & (str2[j - 1] == str3[j - 1]);
        }
        //第一列:s1[前i个] + s2[前0个] -> s3[前i个]
        for (int i = 1; i <= n; i++) {
            dp[i][0] = dp[i - 1][0] & (str1[i - 1] == str3[i - 1]);
        }
        //普通位置
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = false;
                if (str1[i - 1] == str3[i + j - 1]) {
                    dp[i][j] |= dp[i - 1][j];
                }
                if (str2[j - 1] == str3[i + j - 1]) {
                    dp[i][j] |= dp[i][j - 1];
                }
            }
        }
        return dp[n][m];
    }

}
