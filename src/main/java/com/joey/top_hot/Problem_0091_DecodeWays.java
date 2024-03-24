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

    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        return fun(s.toCharArray(), 0);
    }

    //递归含义
    //str[0,index-1]已经搞定了不用再操心了，str[index...]有多少中解码方法返回
    private int fun(char[] str, int index) {
        if (index == str.length) {
            return 1;
        }
        //index位置还有字符
        if (str[index] == '0') return 0; // 不能让index位置单独面对0字符
        // str[index] != '0'
        // 1) str[index]单转
        int ans = fun(str, index + 1);
        // 2) index index+1一起转
        if (index + 1 < str.length) {
            int num = (str[index] - '0') * 10 + (str[index + 1] - '0');
            if (num <= 26) {
                ans += fun(str, index + 2);
            }
        }
        return ans;
    }

    //dp
    private int numDecodings2(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] str = s.toCharArray();
        int n = str.length;
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (str[i] != '0') {
                dp[i] = dp[i + 1];
                if (i + 1 < n && (str[i] - '0') * 10 + (str[i + 1] - '0') <= 26) {
                    dp[i] += dp[i + 2];
                }
            }
        }
        return dp[0];
    }

}
