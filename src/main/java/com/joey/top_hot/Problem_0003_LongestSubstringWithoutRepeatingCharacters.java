package com.joey.top_hot;

import java.util.Arrays;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的最长子串的长度。
 * 示例1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 */
public class Problem_0003_LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] str = s.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i < str.length; i++) map[str[i]] = -1;
        int ans = 1;
        int pre = 1; //前一个位置往左推的最大长度
        map[str[0]] = 0;
        for (int i = 1; i < str.length; i++) {
            int cur = Math.min(pre + 1, i - map[str[i]]);
            ans = Math.max(ans, cur);
            map[str[i]] = i;
            pre = cur;
        }
        return ans;
    }

}
