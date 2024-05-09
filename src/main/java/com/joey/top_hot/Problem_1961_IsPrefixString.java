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
        //思路
        //对s使用一个p指向当前处理到的位置
        //遍历words单词表，对于每一个单词word，和s[p]比较，看能够完全对上，并且p到越界位置了，words中某个word也同步到了越界位置
        //这两个条件同时满足，才能返回true
        char[] arr = s.toCharArray();
        int p = 0;
        for (String word : words) {
            char[] cur = word.toCharArray();
            for (int i = 0; i < cur.length; i++) {
                //遍历到某一个单词，单词还没到结尾，s先消耗完了，false
                if (p == arr.length || arr[p++] != cur[i]) {
                    return false;
                }
            }
            if (p == arr.length) {
                return true;
            }
        }
        return false;
    }

}
