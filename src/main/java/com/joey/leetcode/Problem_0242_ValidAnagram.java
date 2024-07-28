package com.joey.leetcode;

/**
 * 242. 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * <p>
 * 示例 1:
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * <p>
 * 示例 2:
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 说明:
 * 你可以假设字符串只包含小写字母。
 * <p>
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 */
//判断两个字符串是否互为变形词
public class Problem_0242_ValidAnagram {

    //思路
    //通过hash表统计词频，看词频是否完全
    public boolean isAnagram(String s, String t) {
        //同时为空，true
        if (s == null && t == null) {
            return true;
        }
        //一个为空一个不为空，false
        if (s == null ^ t == null) {
            return false;
        }
        //长度不同直接false
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        int[] count = new int[256];
        for (char c : str1) {
            count[c]++;
        }
        for (char c : str2) {
            if (--count[c] < 0) {
                return false;
            }
        }
        return true;
    }
}
