package com.joey.leetcode;

/**
 * @author pei.liu
 */
//467. 环绕字符串中唯一的子字符串
//定义字符串 base 为一个 "abcdefghijklmnopqrstuvwxyz" 无限环绕的字符串，所以 base 看起来是这样的：
//"...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
//给你一个字符串 s ，请你统计并返回 s 中有多少 不同非空子串 也在 base 中出现。
//示例 1：
//输入：s = "a"
//输出：1
//解释：字符串 s 的子字符串 "a" 在 base 中出现。
//示例 2：
//输入：s = "cac"
//输出：2
//解释：字符串 s 有两个子字符串 ("a", "c") 在 base 中出现。
//示例 3：
//输入：s = "zab"
//输出：6
//解释：字符串 s 有六个子字符串 ("z", "a", "b", "za", "ab", and "zab") 在 base 中出现。
//
//提示：
//1 <= s.length <= 10^5
//s 由小写英文字母组成
public class Problem_0467_UniqueSubstringsWraparoundString {

    //考虑以每种字符开头或结尾的可能性
    //将每种字符结尾的可能性累加起来就是最终答案
    public static int findSubstringInWraproundString(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int[] dp = new int[26]; //初始每种字符结尾的答案都是0
        dp[str[0] - 'a'] = 1;
        //len=1表示当前位置的答案至少是1
        for (int i = 1, len = 1; i < str.length; i++) {
            int curr = str[i] - 'a';
            int pre = str[i - 1] - 'a';
            //边界条件就是a
            //如果当前位置是a，判断前一个位置是否是z，如果是累加上z的答案
            //如果是其他字母，判断前一个位置是否前一个字母，如果是就累加上前一个字母的答案（取max）
            if ((curr == 0 && pre == 25) || curr - pre == 1) {
                len++;
            } else {
                len = 1;
            }
            dp[curr] = Math.max(dp[curr], len);
        }
        int ans = 0;
        for (int i = 0; i < 26; i++) {
            ans += dp[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(findSubstringInWraproundString("zab"));
    }

}
