package com.joey.top_hot;


//387. 字符串中的第一个唯一字符
//给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
//示例：
//s = "leetcode"
//返回 0
//s = "loveleetcode"
//返回 2
//提示:
//1 <= s.length <= 10^5
//s 只包含小写字母
public class Problem_0387_FirstUniqueCharacterInString {

    //思路：使用hashmap先统计每个字符的词频
    //遍历找到第一个词频为1的位置返回
    public int firstUniqChar(String s) {
        int[] map = new int[26]; //s 只包含小写字母
        char[] str = s.toCharArray();
        for (char c : str) {
            map[c - 'a']++;
        }
        for (int i = 0; i < str.length; i++) {
            if (map[str[i] - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

}
