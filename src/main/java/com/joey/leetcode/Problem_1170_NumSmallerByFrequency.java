package com.joey.leetcode;

import java.util.Arrays;

/**
 * @author pei.liu
 */
//1170. 比较字符串最小字母出现频次
//定义一个函数 f(s)，统计 s  中（按字典序比较）最小字母的出现频次 ，其中 s 是一个非空字符串。
//例如，若 s = "dcce"，那么 f(s) = 2，因为字典序最小字母是 "c"，它出现了 2 次。
//现在，给你两个字符串数组待查表 queries 和词汇表 words 。
//对于每次查询 queries[i] ，需统计 words 中满足 f(queries[i]) < f(W) 的 词的数目 ，W 表示词汇表 words 中的每个词。
//请你返回一个整数数组 answer 作为答案，其中每个 answer[i] 是第 i 次查询的结果。
//
//示例 1：
//输入：queries = ["cbd"], words = ["zaaaz"]
//输出：[1]
//解释：查询 f("cbd") = 1，而 f("zaaaz") = 3 所以 f("cbd") < f("zaaaz")。
//示例 2：
//输入：queries = ["bbb","cc"], words = ["a","aa","aaa","aaaa"]
//输出：[1,2]
//解释：第一个查询 f("bbb") < f("aaaa")，第二个查询 f("aaa") 和 f("aaaa") 都 > f("cc")。
//
//提示：
//1 <= queries.length <= 2000
//1 <= words.length <= 2000
//1 <= queries[i].length, words[i].length <= 10
//queries[i][j]、words[i][j] 都由小写英文字母组成
public class Problem_1170_NumSmallerByFrequency {

    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int n = queries.length;
        int[] ans = new int[n];
        int m = words.length;
        int[] cache = new int[m];
        for (int i = 0; i < m; i++) {
            cache[i] = f(words[i]);
        }
        for (int i = 0; i < n; i++) {
            int q = f(queries[i]);
            int curr = 0;
            for (int j = 0; j < m; j++) {
                if (q < cache[j]) {
                    curr++;
                }
            }
            ans[i] = curr;
        }
        return ans;
    }

    public static int f(String s) {
        char[] str = s.toCharArray();
        Arrays.sort(str);
        int n = str.length;
        int l = 0;
        int r = n - 1;
        int ans = n;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (str[m] > str[0]) {
                ans = m; //找大于str[0]的最左位置
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

}
