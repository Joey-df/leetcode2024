package com.joey.top_hot;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 */

//提示：
//1 <= s.length <= 104
//s 仅由括号 '()[]{}' 组成
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
                if (q.isEmpty() || q.pollLast() != c)
                    return false;
            }
        }
        return q.isEmpty();
    }

}
