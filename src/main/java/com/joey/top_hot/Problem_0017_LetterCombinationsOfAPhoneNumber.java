package com.joey.top_hot;

import java.util.*;

/**
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * <p>
 * 示例 1：
 * <p>
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 */
public class Problem_0017_LetterCombinationsOfAPhoneNumber {

    private static char[][] map = {
            {},
            {},
            {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'},
            {'j', 'k', 'l'},
            {'m', 'o', 'n'},
            {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'},
            {'w', 'x', 'y', 'z'}
    };


    public static List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits == null || digits.length() == 0) return ans;
        fun(digits.toCharArray(), 0, new ArrayList<>(), ans);
        return ans;
    }


    // input  固定输入
    // 当前来到index位置做决定，[0,index-1]已经做好决定了，沿途形成的路径放在path里
    // ans 收集答案
    private static void fun(char[] input, int index, ArrayList<Character> path, List<String> ans) {
        if (index == input.length) { // base case
            //collect ans from path
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < path.size(); i++) {
                builder.append(path.get(i));
            }
            ans.add(builder.toString());
        } else {
            // 主逻辑
            int num = input[index] - '0';
            char[] cur = map[num];
            for (char c : cur) {
                path.add(c);
                fun(input, index + 1, path, ans);
                path.remove(path.size() - 1); // clear
            }
        }
    }


}
