package com.joey.top_hot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 140. 单词拆分 II
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，
 * 在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。
 * <p>
 * 说明：
 * 分隔时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * <p>
 * 示例 1：
 * 输入:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * 输出:
 * [
 * "cats and dog",
 * "cat sand dog"
 * ]
 */
public class Problem_0140_WordBreakII {


    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) return ans;
        fun(s, 0, new HashSet<>(wordDict), new ArrayList<>(), ans);
        return ans;
    }

    //递归含义
    //s[0,index-1]已经搞定了不用操心了
    //沿途形成的路径为path
    //ans收集答案
    private void fun(String s, int index, Set<String> words, ArrayList<String> path, List<String> ans) {
        if (index == s.length()) {
            //collect ans
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < path.size(); i++) {
                builder.append(path.get(i));
                if (i < path.size() - 1) builder.append(" ");
            }
            ans.add(builder.toString());
        } else {
            for (int end = index; end < s.length(); end++) {
                String cur = s.substring(index, end + 1);
                if (words.contains(cur)) {
                    path.add(cur);
                    fun(s, end + 1, words, path, ans);
                    path.remove(path.size() - 1);//clear
                }
            }
        }
    }

}
