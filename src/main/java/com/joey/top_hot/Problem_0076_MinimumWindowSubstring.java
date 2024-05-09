package com.joey.top_hot;

/**
 * 76. 最小覆盖子串
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。
 * 如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *
 * 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *
 * 示例 1：
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 *
 * 示例 2：
 * 输入：s = "a", t = "a"
 * 输出："a"
 */
public class Problem_0076_MinimumWindowSubstring {

    public static String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        char[] str = s.toCharArray();
        char[] target = t.toCharArray();
        int[] map = new int[128];//欠账表
        for (char c : target) {
            map[c]++;
        }
        int all = target.length;//欠账总数
        int L = 0, R = 0;
        int minLen = -1, ansL = -1, ansR = -1;

        while (R < str.length) {
            map[str[R]]--;
            if (map[str[R]] >= 0) {
                all--; //map[str[R]]减1之后 >=0 时，all--
            }
            //all==0说明，以当前L开头的窗口首次包含了target全部字符
            if (all == 0) {
                while (map[str[L]] < 0) { //L来到初次包含target中字符的位置
                    map[str[L]]++;
                    L++;
                }
                //收集答案
                if (minLen == -1 || minLen > R - L + 1) {
                    minLen = R - L + 1;
                    ansL = L;
                    ansR = R;
                }
                //收集完答案之后，尝试下一个位置，重新开始欠账
                all++;
                map[str[L]]++;
                L++;
            }
            R++;
        }
        return minLen == -1 ? "" : s.substring(ansL, ansR + 1);
    }

    public static void main(String[] args) {
       String s = "ADOBECODEBANC", t = "ABC";
        System.out.println(minWindow(s, t));
    }

}
