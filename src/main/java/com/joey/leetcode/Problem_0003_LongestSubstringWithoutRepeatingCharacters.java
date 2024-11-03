package com.joey.leetcode;

import java.util.HashMap;

//给定一个字符串，请你找出其中不含有重复字符的最长子串的长度。
//示例1:
//输入: s = "abcabcbb"
//输出: 3
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
//<p>
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
public class Problem_0003_LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring1(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] str = s.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i < str.length; i++) map[str[i]] = -1;
        int ans = 1;
        int pre = 1; //前一个位置往左推的最大长度
        map[str[0]] = 0;
        for (int i = 1; i < str.length; i++) {
            int cur = Math.min(pre + 1, i - map[str[i]]);
            ans = Math.max(ans, cur);
            map[str[i]] = i;
            pre = cur;
        }
        return ans;
    }

    public int lengthOfLongestSubstring(String s) {
        char[] str = s.toCharArray();
        int n = str.length;
        int ans = 0;
        int r = 0;
        //窗口内的状态
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; ) { //枚举每一个位置i，求出每个位置的答案，求全局max
            //只要r不越界并且，r位置的字符不在窗口内出现，就继续
            while (r < n && !map.containsKey(str[r])) {
                map.put(str[r], 1);
                r++;
            }
            //从while出来，两种情况
            //1.r越界了
            //2.r没越界，但是map中已经包含了r位置的字符
            ans = Math.max(ans, r - i);
            //窗口左边界开始缩
            //1.r没越界，只要窗口内还包含r位置的字符，i就一直++
            //2.r越界了，i位置的元素出窗口，然后i++即可
            if (r < n) {
                while (map.containsKey(str[r])) {
                    map.remove(str[i]);
                    i++;
                    //在此期间这些位置的答案不用收集，因为都比之前收集到的短
                    //原因是r没动，i一直在往右动，距离比之前收集到的要短
                    //ans = Math.max(ans, r - i);
                }
            } else {
                map.remove(str[i]);
                i++;
            }
        }
        return ans;
    }

}
