package com.joey.od.s200;

/**
 * @author pei.liu
 */


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 中文分词模拟器 {

    public static String joinString(List<String> arr, String s) {
        StringBuilder res = new StringBuilder();
        for (String x : arr) {
            res.append(x).append(s);
        }
        return res.substring(0, res.length() - s.length());
    }

    // 动态规划求解字符串s是否能被某单词列表组成
    // 在有多种拆分情况下，考虑从左到右长度尽量长的单词去切分
    public static String solve(String s, List<String> words) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1]; // dp[i]表示s[i:]这个后缀能被某单词列表组成
        dp[n] = true; // 空串能被某单词列表组成

        for (int i = n; i >= 0; i--) { // 从后往前求解
            for (String x : words) { // 遍历单词列表
                int m = x.length(); // 单词长度
                if (i + m <= n && s.substring(i, i + m).equals(x) && dp[i + m]) { // 如果s[i : i + m]是单词x且s[i + m:]能被某单词列表组成
                    dp[i] = true; // 则s[i:]能被某单词列表组成
                    break;
                }
            }
        }

        // 接下来考虑如何从f这个动态规划数组中拆出具体方案
        List<String> res = new ArrayList<>(); // 记录分解方式
        if (!dp[0]) { // 如果s不能被某单词列表组成, 直接返回本身，用逗号拼接
            StringBuilder result = new StringBuilder();
            for (char c : s.toCharArray()) {
                result.append(c).append(",");
            }
            return result.substring(0, result.length() - 1);
        }

        int i = 0;
        // 从前往后求解
        while (i < n) {
            int mx = -1; // 记录从i开始，最长可能的长度，需要满足两个条件: 这个前缀出现在单词中，去掉这个前缀，后缀还是能被组成
            // 遍历单词列表
            for (String x : words) {
                // 单词长度
                int m = x.length();
                // 如果s[i : i + m]是单词x且s[i + m:]能被某单词列表组成
                if (i + m <= n && s.substring(i, i + m).equals(x) && dp[i + m]) {
                    mx = Math.max(mx, m);
                }
            }
            // 记录分解方式
            res.add(s.substring(i, i + mx));
            i += mx;
        }
        // 返回分解方式
        return joinString(res, ",");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String s = scanner.nextLine();

        String wordsInput = scanner.nextLine();
        List<String> words = new ArrayList<>();
        String[] wordsArray = wordsInput.split(",");
        for (String word : wordsArray) {
            words.add(word);
        }

        // 对s split先
        String[] asksArray = s.split(",");
        List<String> asks = new ArrayList<>();
        for (String ask : asksArray) {
            asks.add(ask);
        }

        // 一个个单词去dp
        List<String> res = new ArrayList<>();
        for (String x : asks) {
            res.add(solve(x, words));
        }
        System.out.println(joinString(res, ","));
    }
}
