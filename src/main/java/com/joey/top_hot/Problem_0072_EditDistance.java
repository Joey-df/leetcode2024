package com.joey.top_hot;

/**
 * 72. 编辑距离
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * <p>
 * 示例 1：
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * <p>
 * 示例 2：
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 * <p>
 * 提示：
 * 0 <= word1.length, word2.length <= 500
 * word1 和 word2 由小写英文字母组成
 */
public class Problem_0072_EditDistance {

    public int minDistance(String word1, String word2) {
        if (word1 == null && word2 == null) return 0;
        int d = 1, r = 1, a = 1;
        if (word1 == null ^ word2 == null) {
            return word1 == null ? word2.length() * a : word1.length() * d;
        }
        return fun(word1.toCharArray(), word2.toCharArray(), 1, 1, 1);
    }

    //每一步，删除代价d、替换代价r、插入代价a，保留代价0
    //将str1变成str2，最小的编辑代价，返回
    public int fun(char[] str1, char[] str2, int d, int r, int a) {
        int n = str1.length;
        int m = str2.length;
        int[][] dp = new int[n + 1][m + 1];
        //dp[i][j]含义：
        //str前i个字符，变成str2前j个字符，最小编辑代价是多少
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) { //第一列
            dp[i][0] = i * d; //编辑为空串，所以是删除
        }
        for (int j = 1; j <= m; j++) { //第一行
            dp[0][j] = j * a; //空串编辑为str2的前j个，所以全是添加
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (str1[i - 1] == str2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1]; //保留最后一个字符
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + r; //最后一个字符不同，替换
                }
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + d); //先变 再删一个字符
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + a); //先变 再添加一个字符
            }
        }
        return dp[n][m];
    }
}
