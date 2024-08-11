package com.joey.leetcode;

import java.util.Arrays;

/**
 * @author pei.liu
 */
//面试题 08.14. 布尔运算
//给定一个布尔表达式和一个期望的布尔结果 result，布尔表达式由 0 (false)、1 (true)、& (AND)、 | (OR) 和 ^ (XOR) 符号组成。
//实现一个函数，算出有几种可使该表达式得出 result 值的括号方法。
//示例 1:
//输入: s = "1^0|0|1", result = 0
//输出: 2
//解释: 两种可能的括号方法是
//1^(0|(0|1))
//1^((0|0)|1)
//示例 2:
//输入: s = "0&0&0&1^1|0", result = 1
//输出: 10
//提示：
//运算符的数量不超过 19 个
public class Problem_0814_BooleanEvaluationLcci {

    public int countEval(String s, int result) {
        char[] str = s.toCharArray();
        int n = str.length;
        int[][][] dp = new int[n][n][2];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], null);
        }
        return fun(str, 0, n - 1, dp)[result];
    }

    //递归含义：
    //str[l,r]范围上表达式的值[false数,true数]返回
    public static int[] fun(char[] str, int l, int r, int[][][] dp) {
        if (dp[l][r] != null) {
            return dp[l][r];
        }
        if (l == r) {
            int f = str[l] == '0' ? 1 : 0;
            int t = str[l] == '0' ? 0 : 1;
            return new int[]{f, t};
        }
        //l < r
        int f = 0;
        int t = 0;
        for (int p = l + 1; p < r; p += 2) { //枚举每个分割点，分割点必压中运算符
            int[] left = fun(str, l, p - 1, dp);
            int a = left[0]; // false
            int b = left[1]; // true
            int[] right = fun(str, p + 1, r, dp);
            int c = right[0]; // false
            int d = right[1]; // true
            if (str[p] == '|') {
                f += a * c;
                t += a * d + b * c + b * d;
            } else if (str[p] == '&') {
                f += a * c + a * d + b * c;
                t += b * d;
            } else { //str[p]=='^'
                f += a * c + b * d;
                t += a * d + b * c;
            }
        }
        int[] ans = new int[]{f, t};
        dp[l][r] = ans;
        return ans;
    }

}
