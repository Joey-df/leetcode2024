package com.joey.leetcode;

/**
 * 76. 最小覆盖子串
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。
 * 如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * <p>
 * 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * <p>
 * 示例 1：
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * <p>
 * 示例 2：
 * 输入：s = "a", t = "a"
 * 输出："a"
 */
public class Problem_0076_MinimumWindowSubstring {

    //ADOBECODEBANC
    //ABC
    //思路：
    //滑动窗口，[l,r) l==r是不包含任何字符
    //r往右动直到欠帐表还清，停，l开始往右动，直到又开始欠账了，停，此时收集答案
    //r继续往右动
    public static String minWindow(String s, String t) {
        char[] source = s.toCharArray();
        char[] target = t.toCharArray();
        int n = source.length;
        int m = target.length;
        if (n < m) return "";
        int[] debt = new int[256]; //欠帐表
        int cnt = 0; //欠的总数
        for (char c : target) {
            debt[c]++;
            cnt++;
        }
        int len = Integer.MAX_VALUE;
        int start = 0;
        int l = 0;
        int r = 0;
        while (r < n) {
            if (debt[source[r]] > 0) {
                cnt--;
            }
            debt[source[r]]--;
            r++; //每一步r都往右动
            //当cnt==0时尝试l能不能往右动
            if (cnt == 0) {
                while (debt[source[l]] < 0) {
                    debt[source[l]]++;
                    l++;
                }
                //while出来的时候l来到debt[source[l]-'a']==0的地方
                if (r - l < len) {
                    len = r - l;
                    start = l;
                }
                //debt[source[l]]==0
                //l往右动一个位置，尝试以新位置开头的答案
                debt[source[l]]++;
                cnt++;
                l++;
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC", t = "ABC";
        System.out.println(minWindow(s, t));
    }

}
