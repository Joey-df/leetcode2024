package com.joey.leetcode;

import java.util.HashMap;
import java.util.Map;

//76.最小覆盖子串
//给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串
//如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
//示例 1：
//输入：s = "ADOBECODEBANC", t = "ABC"
//输出："BANC"
//解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
//示例 2：
//输入：s = "a", t = "a"
//输出："a"
//解释：整个字符串 s 是最小覆盖子串。
//示例 3:
//输入: s = "a", t = "aa"
//输出: ""
//解释: t 中两个字符 'a' 均应包含在 s 的子串中，
//因此没有符合条件的子字符串，返回空字符串。
//
//提示：
//m == s.length
//n == t.length
//1 <= m, n <= 10^5
//s 和 t 由英文字母组成
//测试链接 : https://leetcode.cn/problems/minimum-window-substring/
public class Problem_0076_MinimumWindowSubstring {

    public static String minWindow1(String s, String t) {
        char[] src = s.toCharArray();
        char[] tar = t.toCharArray();
        int n = src.length;
        int m = tar.length;
        if (n < m) return "";
        int all = m; //总共欠的数量
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            map.put(tar[i], map.getOrDefault(tar[i], 0) + 1);
        }
        int start = -1;
        int len = Integer.MAX_VALUE;
        int r = 0;
        for (int i = 0; i < n; i++) { //枚举每一个开头位置
            //没还完就继续
            while (r < n && all > 0) {
                if (map.containsKey(src[r]) && map.get(src[r]) > 0) {
                    all--; //如果在欠账表中，就还一个
                }
                map.put(src[r], map.getOrDefault(src[r],0) - 1);
                r++;
            }
            //while出来两种情况
            //1.r越界了
            //2.all == 0
            if (all == 0) {
                if (r - i < len) { //当前i位置发现的长度 r-i
                    len = r - i;
                    start = i;
                }
            }
            //i即将++
            if (map.get(src[i]) == 0) {
                //map.get(src[i]) == 0 代表之前欠的，src[i]即将出窗口，又开始欠
                all++;
            }
            map.put(src[i], map.get(src[i]) + 1);
        }
        return start == -1 ? "" : s.substring(start, start + len);
    }

    public static String minWindow(String s, String t) {
        char[] src = s.toCharArray();
        char[] tar = t.toCharArray();
        int n = src.length;
        int m = tar.length;
        if (n < m) return "";
        int all = m; //总共欠的数量
        int[] cnts = new int[256]; //欠账表
        for (int i = 0; i < m; i++) {
            cnts[tar[i]]++;
        }
        int start = -1;
        int len = Integer.MAX_VALUE;
        int r = 0;
        for (int i = 0; i < n; i++) { //枚举每一个开头位置
            //没还完就继续
            while (r < n && all > 0) {
                if (cnts[src[r]] > 0) {
                    all--; //如果在欠账表中，就还一个
                }
                cnts[src[r]]--; //不再欠账表中就会减为负数
                r++;
            }
            //while出来两种情况
            //1.r越界了
            //2.all == 0
            if (all == 0) {
                if (r - i < len) { //当前i位置发现的长度 r-i
                    len = r - i;
                    start = i;
                }
            }
            //i即将++
            if (cnts[src[i]]++ == 0) { //++之前==0说明在欠账表中存在，i++后重新开始欠账
                all++;
            }
        }
        return start == -1 ? "" : s.substring(start, start + len);
    }

}
