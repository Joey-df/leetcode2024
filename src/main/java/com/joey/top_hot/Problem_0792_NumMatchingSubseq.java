package com.joey.top_hot;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pei.liu
 */
//792. 匹配子序列的单词数
//给定字符串 s 和字符串数组 words, 返回  words[i] 中是s的子序列的单词个数 。
//字符串的 子序列 是从原始字符串中生成的新字符串，可以从中删去一些字符(可以是none)，而不改变其余字符的相对顺序。
//例如， “ace” 是 “abcde” 的子序列。
//
//示例 1:
//输入: s = "abcde", words = ["a","bb","acd","ace"]
//输出: 3
//解释: 有三个是 s 的子序列的单词: "a", "acd", "ace"。
//Example 2:
//输入: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
//输出: 2
//
//提示:
//1 <= s.length <= 5 * 10^4
//1 <= words.length <= 5000
//1 <= words[i].length <= 50
//words[i]和 s 都只由小写字母组成。
public class Problem_0792_NumMatchingSubseq {

    //时间复杂度：O((words所有字符的总长度)*log(n))
    //空间复杂度：O(n)
    public int numMatchingSubseq(String s, String[] words) {
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c)) {
                List<Integer> l = new ArrayList<>();
                l.add(i);
                map.put(c, l);
            } else {
                map.get(c).add(i);
            }
        }
        int ans = 0;
        for (String word : words) {
            if (isSubsequence(word, map)) {
                ans++;
            }
        }
        return ans;
    }

    public boolean isSubsequence(String w, Map<Character, List<Integer>> map) {
        int pre = -1; // 前一个位置字符出现的位置
        for (int i = 0; i < w.length(); i++) {
            char c = w.charAt(i);
            List<Integer> list = map.get(c);
            if (list == null) {
                return false;
            }
            int pos = findLeft(list, pre);
            if (pos == -1 || list.get(pos) <= pre) {
                return false;
            }
            pre = list.get(pos);
        }
        return true;
    }

    //有序数组上找 >target 最左的位置
    public int findLeft(List<Integer> list, int target) {
        int l = 0;
        int r = list.size() - 1;
        int m;
        int ans = -1;
        while (l <= r) {
            m = l + ((r - l) >> 1);
            if (list.get(m) > target) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }
}
