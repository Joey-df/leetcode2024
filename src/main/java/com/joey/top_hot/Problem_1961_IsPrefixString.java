package com.joey.top_hot;

/**
 * @author pei.liu
 */
//1961. 检查字符串是否为数组前缀
//给你一个字符串 s 和一个字符串数组 words ，请你判断 s 是否为 words 的 前缀字符串 。
//字符串 s 要成为 words 的 前缀字符串 ，需要满足：s 可以由 words 中的前 k（k 为 正数 ）个字符串按顺序相连得到，且 k 不超过 words.length 。
//如果 s 是 words 的 前缀字符串 ，返回 true ；否则，返回 false 。
//
//示例 1：
//输入：s = "iloveleetcode", words = ["i","love","leetcode","apples"]
//输出：true
//解释：
//s 可以由 "i"、"love" 和 "leetcode" 相连得到。
//示例 2：
//输入：s = "iloveleetcode", words = ["apples","i","love","leetcode"]
//输出：false
//解释：
//数组的前缀相连无法得到 s 。
public class Problem_1961_IsPrefixString {

    public boolean isPrefixString(String s, String[] words) {
        int n = s.length();
        int i = 0; // 当前要处理的s中的位置
        char[] str = s.toCharArray();
        for (String word : words) {
            char[] w = word.toCharArray();
            for (int j = 0; j < w.length; j++) {
                if (i < n && str[i] == w[j]) {
                    i++;
                } else { //i越界 或者 str[i] != w[j]
                    return false;
                }
            }
            //一个单词遍历完了，如果i来到越界位置，说明合法
            if (i == n) {
                return true;
            }
        }
        //走到这里，说明所有字符串都处理完了，但i还没到越界位置
        return false;
    }

}
