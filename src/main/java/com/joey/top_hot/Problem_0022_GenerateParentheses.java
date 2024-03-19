package com.joey.top_hot;

import java.util.*;

/**
 * 22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：["()"]
 */
public class Problem_0022_GenerateParentheses {

    private static Set<Character> set = new HashSet<>(Arrays.asList('(', ')'));

    public static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        char[] path = new char[n * 2];
        fun(n, 0, path, ans);
        return ans;
    }

    //n 表示n对括号,固定参数
    //当前来到index位置做决定，[0,index-1]已经做好决定了，沿途形成的路径存在path里
    //ans收集答案
    public static void fun(int n, int index, char[] path, List<String> ans) {
        //base case
        if (index == n * 2) {
            //collect ans
            if (isValid(path))
                ans.add(new String(path));
        } else {
            //主逻辑
            for (char c : set) {
                path[index] = c;
                fun(n, index + 1, path, ans);
                path[index] = '0'; //clear, not important
            }
        }
    }

    private static boolean isValid(char[] str) {
        int count = 0;
        for (char c : str) {
            if (c == '(') count++;
            else count--;
            if (count < 0) return false;
        }
        return count == 0;
    }

}
