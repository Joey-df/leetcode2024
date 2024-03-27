package com.joey.top_hot;

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


    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) return false;
        return fun(s, 0, new HashSet<>(wordDict)) > 0;
    }

    //递归含义：
    //s[0,index-1]已经搞定了不用再操心了
    //s从0出发，被set中单词表分解的方法数返回
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


}