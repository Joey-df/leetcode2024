package com.joey.leetcode;

//72. 编辑距离
//给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
//你可以对一个单词进行如下三种操作：
//插入一个字符
//删除一个字符
//替换一个字符
//
//示例 1：
//输入：word1 = "horse", word2 = "ros"
//输出：3
//解释：
//horse -> rorse (将 'h' 替换为 'r')
//rorse -> rose (删除 'r')
//rose -> ros (删除 'e')
//示例 2：
//输入：word1 = "intention", word2 = "execution"
//输出：5
//解释：
//intention -> inention (删除 't')
//inention -> enention (将 'i' 替换为 'e')
//enention -> exention (将 'n' 替换为 'x')
//exention -> exection (将 'n' 替换为 'c')
//exection -> execution (插入 'u')
//
//提示：
//0 <= word1.length, word2.length <= 500
//word1 和 word2 由小写英文字母组成
public class Problem_0072_EditDistance {

    public int minDistance(String word1, String word2) {
        int d = 1; //删除代价1
        int r = 1; //替换代价1
        int a = 1; //添加代价1
        if (word1 == null || word2 == null) {
            return word1 == null ? word2.length() * a : word1.length() * d;
        }
        return fun(word1.toCharArray(), word2.toCharArray(), d, r, a);
    }

    //每一步，删除代价d、替换代价r、插入代价a，保留代价0
    //将str1变成str2，最小的编辑代价，返回
    public int fun(char[] str1, char[] str2, int d, int r, int a) {
        int n = str1.length;
        int m = str2.length;
        //dp[i][j]含义
        //str1[前i个] -> str2[前j个]的最小代价
        int[][] dp = new int[n + 1][m + 1];
        dp[0][0] = 0;//前0个前前0个，代价0
        //第一行:str1[前0个] -> str2[前j个]: 全部添加
        for (int j = 1; j <= m; j++) {
            dp[0][j] = j * a;
        }
        //第一列：str1[前i个] -> str2[前0]：全部删除
        for (int i = 1; i <= n; i++) {
            dp[i][0] = i * d;
        }
        //普通位置
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (str1[i - 1] == str2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];//保留最后一个
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + r, Math.min(dp[i - 1][j] + d, dp[i][j - 1] + a));
                }
            }
        }
        return dp[n][m];
    }
}
