package com.joey.top_hot;

import java.util.HashMap;

/**
 * 387. 字符串中的第一个唯一字符
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 *
 * 示例：
 *
 * s = "leetcode"
 * 返回 0
 *
 * s = "loveleetcode"
 * 返回 2
 */
public class Problem_0387_FirstUniqueCharacterInString {

    public static int firstUniqChar(String s) {
        if (s==null || s.length()==0) {
            return -1;
        }
        char[] str = s.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length; i++) {
            map.put(str[i], map.getOrDefault(str[i], 0) + 1);
        }
        int ans = -1;
        for (int i = 0; i< str.length; i++) {
            if (map.get(str[i])==1) {
                ans = i;
                break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(firstUniqChar("aabbcc"));
    }
}
