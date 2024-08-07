package com.joey.leetcode;

/**
 * 394. 字符串解码
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 *
 * 示例 1：
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 *
 * 示例 2：
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 *
 * 示例 3：
 * 输入：s = "2[abc]3[cd]ef"
 * 输出："abcabccdcdcdef"
 *
 * 示例 4：
 * 输入：s = "abc3[cd]xyz"
 * 输出："abccdcdcdxyz"
 */
//和字符串算式是同一个递归结构（leetcode高频（十九）第一题）
public class Problem_0394_DecodeString {

    public static String decodeString(String s) {
        char[] str = s.toCharArray();
        return process(str, 0).ans;
    }

    public static class Info {
        public String ans;
        public int end;

        public Info(String a, int e) {
            ans = a;
            end = e;
        }
    }

    // s[i....]  何时停？遇到   ']'  或者遇到 s的终止位置，停止
    // 返回Info：ans：这一段的答案，end：算到了哪里
    public static Info process(char[] s, int i) {
        StringBuilder ans = new StringBuilder();
        int times = 0;
        while (i < s.length && s[i] != ']') {
            if ((s[i] >= 'a' && s[i] <= 'z') || (s[i] >= 'A' && s[i] <= 'Z')) {
                ans.append(s[i++]);
            } else if (s[i] >= '0' && s[i] <= '9') {
                times = times * 10 + s[i++] - '0';
            } else { // str[index] = '['
                Info next = process(s, i + 1);
                ans.append(timesString(times, next.ans));
                times = 0;
                i = next.end + 1;
            }
        }
        return new Info(ans.toString(), i);
    }

    public static String timesString(int times, String str) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < times; i++) {
            ans.append(str);
        }
        return ans.toString();
    }

}
