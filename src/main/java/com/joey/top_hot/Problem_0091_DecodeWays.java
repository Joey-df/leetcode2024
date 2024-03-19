package com.joey.top_hot;

/**
 * 91. 解码方法
 * 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。
 * 例如，"11106" 可以映射为：
 * <p>
 * "AAJF" ，将消息分组为 (1 1 10 6)
 * "KJF" ，将消息分组为 (11 10 6)
 * 注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
 * <p>
 * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
 * <p>
 * 题目数据保证答案肯定是一个 32 位 的整数。
 * <p>
 * 示例 1：
 * 输入：s = "12"
 * 输出：2
 * 解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
 * <p>
 * 示例 2：
 * 输入：s = "226"
 * 输出：3
 * 解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 */
public class Problem_0091_DecodeWays {

    public static int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        return func(str, 0);
    }

    //递归含义
    //str输入固定参数 如"226"对应的char[]
    //str[0...index-1]已经搞定了，不用操心了！
    //str[index...]解码的方法数，返回
    private static int func(char[] str, int index) {
        if (index == str.length) {
            //表示str[0...str.length-1]已经搞定了，此时代表一种方法数
            return 1;
        }
        //index还有字符
        if (str[index] == '0') {
            return 0;//当前位置面对0字符是不可以的
        }
        //str[index] -> '1'~'9'
        int ways = func(str, index + 1);// str[index]独立转
        if (index + 1 < str.length) { //index+1位置还有字符
            int num = (str[index] - '0') * 10 + (str[index + 1] - '0');
            if (num <= 26) {
                ways += func(str, index + 2);//str[index]与str[index+1]组合转
            }
        }
        return ways;
    }

    //改动态规划
    private static int dp(char[] str) {
        int N = str.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            if (str[i] != '0') {
                //str[i] -> '1'~'9'
                dp[i] = dp[i + 1];// str[i]独立转
                if (i + 1 < str.length) { //i+1位置还有字符
                    int num = (str[i] - '0') * 10 + (str[i + 1] - '0');
                    if (num <= 26) {
                        dp[i] += dp[i + 2];//str[index]与str[index+1]组合转
                    }
                }
            }
        }
        return dp[0];
    }


    public static void main(String[] args) {
        String s = "111111111111111111111111111111111";
        System.out.println(dp(s.toCharArray()));
        System.out.println(numDecodings(s));
    }
}
