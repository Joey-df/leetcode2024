package com.joey.leetcode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 139. 单词拆分
 * 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，
 * 判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * <p>
 * 说明：
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * <p>
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 */
// lintcode也有测试，数据量比leetcode大很多 : https://www.lintcode.com/problem/107/
public class Problem_0139_WordBreak {
    //暴力尝试
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) return false;
        return fun(s, 0, new HashSet<>(wordDict)) > 0;
    }

    //递归含义：
    //s[0,index-1]已经被分解了，不用再操心了
    //s从index出发，被set中单词表分解的方法数返回
    public int fun(String s, int index, Set<String> set) {
        if (index == s.length()) {
            return 1;
        }
        int ans = 0;
        for (int end = index; end < s.length(); end++) {
            String cur = s.substring(index, end + 1);
            if (set.contains(cur)) {
                ans += fun(s, end + 1, set);
            }
        }
        return ans;
    }

    //动态规划的方法
    //时间复杂度：O(N^3) 2阶调度+1阶求子串并且查hash表
    //使用前缀树可以优化到O(N^2)
    public boolean wordBreak2(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) return false;
        int n = s.length();
        //dp[i]含义：s[i...]被单词表分解的方法数
        int[] dp = new int[n + 1];
        HashSet<String> set = new HashSet<>(wordDict);
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int end = i; end < s.length(); end++) {
                String cur = s.substring(i, end + 1);
                if (set.contains(cur)) {
                    dp[i] += dp[end + 1];
                }
            }
        }
        return dp[0] > 0;
    }

    public static class Node {
        public boolean end;
        public Node[] nexts;

        public Node() {
            end = false;
            nexts = new Node[26];
        }
    }

    //使用前缀树优化
    public boolean wordBreak3(String s, List<String> wordDict) {
        Node root = new Node();
        for (String str : wordDict) {
            char[] chs = str.toCharArray();
            Node node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (node.nexts[index] == null) {
                    node.nexts[index] = new Node();
                }
                node = node.nexts[index];
            }
            node.end = true;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            Node cur = root;
            for (int end = i; end < N; end++) {
                cur = cur.nexts[str[end] - 'a'];
                if (cur == null) {
                    break;
                }
                if (cur.end) {
                    dp[i] += dp[end + 1];
                }
            }
        }
        return dp[0] > 0;
    }

}