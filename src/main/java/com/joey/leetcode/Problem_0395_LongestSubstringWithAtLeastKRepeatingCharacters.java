package com.joey.leetcode;

import java.util.HashMap;
import java.util.Map;

// 395.至少有K个重复字符的最长子串
// 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串
// 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度
// 如果不存在这样的子字符串，则返回 0。
// 测试链接 : https://leetcode.cn/problems/longest-substring-with-at-least-k-repeating-characters/
public class Problem_0395_LongestSubstringWithAtLeastKRepeatingCharacters {

    //必须只包含1种字符，每种字符至少出现k次
    //必须只包含2种字符，每种字符至少出现k次
    //必须只包含3种字符，每种字符至少出现k次
    //...
    //必须只包含26种字符，每种字符至少出现k次
    public static int longestSubstring(String s, int k) {
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i <= 26; i++) {
            ans = Math.max(ans, fun(s, k, i));
        }
        return ans == Integer.MIN_VALUE ? 0 : ans;
    }

    //维护窗口内每种字符出现的次数
    //key:字符
    //value:出现的次数
    public static Map<Character, Integer> cnts = new HashMap<>();
    //窗口内满足的字符个数
    public static int satisfy = 0;

    //必须只包含kinds种字符
    //先假设 kinds=2 举例
    public static int fun(String s, int k, int kinds) {
        //因为是全局变量，所以每次要清空
        cnts.clear();
        satisfy = 0;
        char[] str = s.toCharArray();
        int n = str.length;
        int r = 0;
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) { //枚举每个位置i开头的情况
            //两种情况
            //1.收集到了字符种类没够kinds种，就继续
            //2.收集到了kinds种字符，但是没有达到每种字符都够k次就继续
            while (r < n && ok(str[r], kinds)) {
                int pre = cnts.getOrDefault(str[r], 0);
                if (pre == k - 1) { //如果str[r]进来之前的词频是k-1，进来之后，statify就增加一种
                    satisfy++;
                }
                cnts.put(str[r], pre + 1);
                r++;
            }
            //从while出来，两种情况
            //1 r越界
            //2 r指向违规的位置（str[r]如果进入窗口的话，种类就超kinds种了）
            //收集答案的时机：需要判断窗口内满足的种类是否等于kinds种
            if (satisfy == kinds) {
                ans = Math.max(ans, r - i);
            }
            //i即将++，i位置出窗口
            int prei = cnts.getOrDefault(str[i], 0);
            if (prei == k) {
                satisfy--;
            }
            if (prei == 1) {
                cnts.remove(str[i]);
            } else {
                cnts.put(str[i], prei - 1);
            }
        }
        return ans;
    }

    //c是否可以进窗口
    //只要c进入后窗口内的种类不超过kinds就可以进
    public static boolean ok(char c, int kinds) {
        int collect = cnts.size();
        return collect < kinds || cnts.containsKey(c);
    }

    public static void main(String[] args) {
        String s = "ababbc";
        //System.out.println(fun(s, 2, 2));
        System.out.println(longestSubstring(s, 2));
    }

}
