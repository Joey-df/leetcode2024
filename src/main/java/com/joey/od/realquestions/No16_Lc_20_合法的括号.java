package com.joey.od.realquestions;

/**
 * @author pei.liu
 */
public class No16_Lc_20_合法的括号 {

    public static boolean isValid(String s) {
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
