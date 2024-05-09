package com.joey.top_hot;

/**
 * 115. 不同的子序列
 * 给定两个字符串S和T，返回S子序列等于T的不同子序列个数有多少个?
 * 如果得到子序列A删除的位置与得到子序列B删除的位置不同，那么认为A和B就是不同的。
 * 【例子】
 * S = "rabbbit", T = "rabbit"
 * 返回: 3
 * 是以下三个S的不同子序列，没有^的位置表示删除的位置，因为删除的位置不同，所以这三个子序列是不一样的
 * (上箭头符号 ^ 表示选取的字母)
 * rabbbit
 * ^^^^ ^^
 * rabbbit
 * ^^ ^^^^
 * rabbbit
 * ^^^ ^^^
 */
//115. 不同的子序列
//https://leetcode-cn.com/problems/distinct-subsequences/
//train_camp_04.class02.Code04_DistinctSubsequences
//coding_for_great_offer/class17/Code04_DistinctSubseq.java
public class Problem_0115_DistinctSubsequences {

    //S = "rabbbit", T = "rabbit"
    public static int numDistinct(String s, String t) {
        if (s.length() < t.length()) return 0;
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        int n = str1.length;
        int m = str2.length;
        int[][] dp = new int[n][m];
        //dp[i][j]含义
        //str1[0...i]，通过删除得到str2[0...j]的方法数
        dp[0][0] = str1[0] == str2[0] ? 1 : 0; //相等：一种方法（不删），不等：0种方法
        //第一列
        for (int i = 1; i < n; i++) {
            //dp[i][0] = str1[0...i]范围上，str2[0]字符的个数
            dp[i][0] = dp[i - 1][0] + (str1[i] == str2[0] ? 1 : 0);
        }
        //第一行
        //str[0]删除得到str2[0,j]的方法数：无方案
        for (int j = 1; j < m; j++) {
            dp[0][j] = 0;
        }
        //普通位置
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = dp[i - 1][j]; //不考虑str1[i]
                if (str1[i] == str2[j]) { //保留str1[i]，有条件的：str1[i] == str2[j]
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }
        return dp[n - 1][m - 1];
    }

}
