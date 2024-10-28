package com.joey.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author pei.liu
 */
//1153. 字符串转化
//给出两个长度相同的字符串 str1 和 str2。请你帮忙判断字符串 str1 能不能在 零次 或 多次 转化 后变成字符串 str2。
//每一次转化时，你可以将 str1 中出现的 所有 相同字母变成其他 任何 小写英文字母。
//只有在字符串 str1 能够通过上述方式顺利转化为字符串 str2 时才能返回 true .
//
//示例 1：
//输入：str1 = "aabcc", str2 = "ccdee"
//输出：true
//解释：将 'c' 变成 'e'，然后把 'b' 变成 'd'，接着再把 'a' 变成 'c'。注意，转化的顺序也很重要。
//示例 2：
//输入：str1 = "leetcode", str2 = "codeleet"
//输出：false
//解释：我们没有办法能够把 str1 转化为 str2。
//
//提示：
//1 <= str1.length == str2.length <= 10^4
//str1 和 str2 中都只会出现小写英文字母
public class Problem_1153_StringTransformsIntoAnotherString {

    public boolean canConvert(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        if (str1.equals(str2)) {
            return true;
        }
        Map<Character, Character> map = new HashMap<>(); //存放str1和str2中字符的对应关系
        int n = str1.length();
        Set<Character> set = new HashSet<>(); //存str2中字符
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(str1.charAt(i))) {
                map.put(str1.charAt(i), str2.charAt(i));
                set.add(str2.charAt(i));
            } else {
                if (map.get(str1.charAt(i)) != str2.charAt(i)) {
                    return false;
                }
            }
        }
        return set.size() < 26;
    }

}
