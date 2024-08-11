package com.joey.leetcode;

//115. 不同的子序列
//给你两个字符串 s 和 t ，统计并返回在 s 的 子序列 中 t 出现的个数，结果需要对 109 + 7 取模。
//1 <= s.length, t.length <= 1000
//s 和 t 由英文字母组成
//115. 不同的子序列
//https://leetcode-cn.com/problems/distinct-subsequences/
//train_camp_04.class02.Code04_DistinctSubsequences
//coding_for_great_offer/class17/Code04_DistinctSubseq.java
public class Problem_0115_DistinctSubsequences {

    //S = "rabbbit", T = "rabbit"
    public static int numDistinct1(String s, String t) {
        char[] str = s.toCharArray();
        char[] target = t.toCharArray();
        int n = str.length;
        int m = target.length;
        //dp[i][j]含义：
        //s取前i个长度，有多少个子序列等于t取前j个长度的字符串
        int[][] dp = new int[n + 1][m + 1];
        //左上角：s取0个（空集）有多少个子序列等于t取前0个长度的字符串，1个
        dp[0][0] = 1;
        for (int i = 1; i < m; i++) {
            dp[0][i] = 0; //s取前0个有多少个子序列等于t取前1，2，3个，空集不可能等于长于大于0的字符串，所以0个
        }
        //第一列
        for (int i = 1; i < n; i++) {
            //s取前k个，有多少个子序列等于t取前0个，t取前0个就是空集，所以空集对空集，就是一个
            dp[i][0] = 1;
        }
        //普通位置
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                //不要最后一个位置
                dp[i][j] = dp[i - 1][j];
                //要最后一个位置
                if (str[i - 1] == target[j - 1]) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }
        return dp[n][m];
    }

    public static int numDistinct(String s, String t) {
        char[] str = s.toCharArray();
        char[] target = t.toCharArray();
        int n = str.length;
        int m = target.length;
        //dp[i][j]:
        //str[0,i]范围有多少个子序列完全等于target[0,j]这一段
        int[][] dp = new int[n][m];
        //左上角:str[0,0] -> target[0,0]
        dp[0][0] = str[0] == target[0] ? 1 : 0;
        //第一行：str[0,0] -> target[0,j]: 无结果，全是0
        //第一列：str[0,i] -> target[0,0]：str[0,i]范围上target[0]字符的个数
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + (str[i] == target[0] ? 1 : 0);
        }
        //普遍位置
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = dp[i - 1][j]; //不要i位置的字符
                //要i位置的字符
                if (str[i] == target[j]) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }
        return dp[n - 1][m - 1];
    }

}
