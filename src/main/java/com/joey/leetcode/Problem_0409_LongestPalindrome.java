package com.joey.leetcode;

/**
 * @author pei.liu
 */
//409. 最长回文串
//给定一个包含大写字母和小写字母的字符串 s ，返回 通过这些字母构造成的 最长的回文串 。
//在构造过程中，请注意 区分大小写 。比如 "Aa" 不能当做一个回文字符串。
//
//示例 1:
//输入:s = "abccccdd"
//输出:7
//解释:
//我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
//示例 2:
//输入:s = "a"
//输出:1
//示例 3：
//输入:s = "aaaaaccc"
//输出:7
public class Problem_0409_LongestPalindrome {
    public int longestPalindrome(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] str = s.toCharArray();
        int[] map = new int[128];//因为只包含大小写字符，所以128就够了
        for (char c : str) {
            map[c]++;
        }
        int ans = 0;
        int mark = 0; //如果某个字符有奇数个，标记为1，说明将这个字符放在中间位置可构成回文
        for (int cnt: map) {
            int mod = cnt % 2;
            if (mod == 0) {
                ans += cnt;
            } else {
                ans += cnt - 1;
                mark = 1;
            }
        }
        return ans + mark;
    }

    public static int longestPalindrome2(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] str = s.toCharArray();
        int[] map = new int[128];//因为只包含大小写字符，所以128就够了
        for (char c : str) {
            map[c]++;
        }
        int max = 0;
        for (int freq : map) {
            //如果某个字符的数量大于1，比如说是m，则加上<=m的偶数
            max += freq > 1 ? (freq >> 1) << 1 : 0;
        }
        //max == str.length：说明所有字符个数都是偶数
        //否则说明有奇数个的字符，要加上1
        return max == str.length ? max : max + 1;
    }
}
