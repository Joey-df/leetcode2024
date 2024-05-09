package com.joey.prepare;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringJoiner;

/**
 * @author pei.liu
 */
//给出一个字符串 s（仅含有小写英文字母和括号）。
//请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。
//注意，您的结果中 不应 包含任何括号。
//
//示例 1：
//输入：s = "(abcd)"
//输出："dcba"
//示例 2：
//输入：s = "(u(love)i)"
//输出："iloveu"
//解释：先反转子字符串 "love" ，然后反转整个字符串。
//示例 3：
//输入：s = "(ed(et(oc))el)"
//输出："leetcode"
//解释：先反转子字符串 "oc" ，接着反转 "etco" ，然后反转整个字符串。
//示例 4：
//输入：s = "a(bcdefghijkl(mno)p)q"
//输出："apmnolkjihgfedcbq"
//
//提示：
//1 <= s.length <= 2000
//s 中只有小写英文字母和括号
//题目测试用例确保所有括号都是成对出现的
public class No20_Lc_1190_括号内字符串翻转 {

    //一个栈搞定
    public static String reverseParentheses(String s) {
        if (s.isEmpty()) return s;
        char[] str = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        ArrayList<Character> buffer = new ArrayList<>();
        int n = str.length;
        for (int i = 0; i < n; i++) {
            if (str[i] != ')') {
                stack.push(str[i]);
            } else { //遇到 )
                while (stack.peek() != '(') {
                    buffer.add(stack.pop());
                }
                stack.pop(); // 把 ( pop出
                for (Character c : buffer) {
                    stack.push(c);
                }
                buffer.clear();
            }
        }
        StringBuilder builder = new StringBuilder();
        for (char c: stack) builder.append(c);
        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(reverseParentheses("(ed(et(oc))el)"));
    }

}
