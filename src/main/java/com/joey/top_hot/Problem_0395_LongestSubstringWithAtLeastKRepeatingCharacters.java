package com.joey.top_hot;

//395. 至少有 K 个重复字符的最长子串
//给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串，
//要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
//示例 1：
//输入：s = "aaabb", k = 3
//输出：3
//解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
//示例 2：
//输入：s = "ababbc", k = 2
//输出：5
//解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
public class Problem_0395_LongestSubstringWithAtLeastKRepeatingCharacters {

    //暴力解
    //时间复杂度: O(n^2)
    public static int longestSubstring(String s, int k) {
        char[] str = s.toCharArray();
        int N = str.length;
        int max = 0;
        for (int L = 0; L < N; L++) { //枚举每个窗口的开头位置
            int[] count = new int[256];
            int collect = 0; //目前窗口内收集了几种字符了
            int satisfy = 0; //目前窗口内出现次数>=k次的字符，满足了几种
            for (int r = L; r < N; r++) {
                if (count[str[r]] == 0) { //str[r]要进窗口，如果之前的词频为0，说明新出现了一种字符
                    collect++;
                }
                if (count[str[r]] == k - 1) { //str[r]要进窗口，如果之前的词频为k-1，说明str[r]的词频到达k
                    satisfy++;
                }
                count[str[r]]++;
                //收集答案
                if (collect == satisfy) {
                    max = Math.max(max, r - L + 1);
                }
            }
        }
        return max;
    }

    //好方法，充分利用只有26中字符的条件
    public static int longestSubstring2(String s, int k) {
        char[] str = s.toCharArray();
        int N = str.length;
        int max = 0;
        for (int require = 1; require <= 26; require++) {
            // 3种
            // a~z 出现次数
            int[] count = new int[26];
            // 目前窗口内收集了几种字符了
            int collect = 0;
            // 目前窗口内出现次数>=k次的字符，满足了几种
            int satisfy = 0;
            // 窗口右边界
            int R = -1;
            for (int L = 0; L < N; L++) { // L要尝试每一个窗口的最左位置
                // [L..R] R+1
                while (R + 1 < N && !(collect == require && count[str[R + 1] - 'a'] == 0)) {
                    R++;
                    if (count[str[R] - 'a'] == 0) {
                        collect++;
                    }
                    if (count[str[R] - 'a'] == k - 1) {
                        satisfy++;
                    }
                    count[str[R] - 'a']++;
                }
                // [L...R]
                if (satisfy == require) {
                    max = Math.max(max, R - L + 1);
                }
                // L++
                if (count[str[L] - 'a'] == 1) {
                    collect--;
                }
                if (count[str[L] - 'a'] == k) {
                    satisfy--;
                }
                count[str[L] - 'a']--;
            }
        }
        return max;
    }

}
