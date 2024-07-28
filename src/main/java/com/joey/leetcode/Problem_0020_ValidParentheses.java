package com.joey.leetcode;

import java.util.LinkedList;

//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
//
//有效字符串需满足：
//左括号必须用相同类型的右括号闭合。
//左括号必须以正确的顺序闭合。
//
//示例 1：
//输入：s = "()"
//输出：true
//示例 2：
//输入：s = "()[]{}"
//输出：true
//示例 3：
//输入：s = "(]"
//输出：false
public class Problem_0020_ValidParentheses {

    // use stack
    public static boolean isValid(String s) {
        if (s == null || s.length() < 2) return false;
        char[] str = s.toCharArray();
        LinkedList<Character> q = new LinkedList<>();
        for (char c : str) {
            if (c == '(' || c == '[' || c == '{') {
                q.offerLast(c == '(' ? ')' : (c == '[' ? ']' : '}'));
            } else {
                if (q.isEmpty() || q.pollLast() != c) {
                    return false;
                }
            }
        }
        return q.isEmpty();
    }

    //方法2：自己用数组实现栈
    public static boolean isValid2(String s) {
        if (s == null || s.length() == 0) return true;
        char[] str = s.toCharArray();
        int n = str.length;
        int[] stack = new int[n];
        int r = 0; // 即将入栈的话放在r位置,r==0表示栈为空
        for (char c : str) {
            if (c == '(' || c == '[' || c == '{') {
                stack[r++] = c == '(' ? ')' : (c == '[' ? ']' : '}');
            } else { // 右括号
                if (r == 0 || stack[r - 1] != c) return false;
                r--; // 出栈
            }
        }
        return r == 0;
    }
}
