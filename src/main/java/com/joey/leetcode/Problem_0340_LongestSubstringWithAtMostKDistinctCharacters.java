package com.joey.leetcode;

/**
 * Given a string, find the length of the longest substring T that contains at most k distinct characters.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "eceba", k = 2
 * Output: 3
 * Explanation: T is "ece" which its length is 3.
 * Example 2:
 * <p>
 * Input: s = "aa", k = 1
 * Output: 2
 * Explanation: T is "aa" which its length is 2.
 * 加锁的题 这个是好题
 * 需要反复练习
 * 24.leetcode高频题目全讲（二十四）讲解
 */
public class Problem_0340_LongestSubstringWithAtMostKDistinctCharacters {

    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k < 1) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] count = new int[256];
        int diff = 0; //收集到的字符种类
        int R = 0;
        int ans = 0;
        for (int L = 0; L < N; L++) {
            // R 窗口的右边界
            while (R < N && (diff < k || (diff == k && count[str[R]] > 0))) {
                diff += count[str[R]] == 0 ? 1 : 0;
                count[str[R++]]++;
            }
            // R 来到违规的第一个位置
            ans = Math.max(ans, R - L);
            diff -= count[str[L]] == 1 ? 1 : 0;
            count[str[L]]--;
        }
        return ans;
    }
}
