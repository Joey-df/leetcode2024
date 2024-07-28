package com.joey.leetcode;

//171. Excel表列序号
//给定一个Excel表格中的列名称，返回其相应的列序号。
//例如，
//A -> 1
//B -> 2
//C -> 3
//...
//Z -> 26
//AA -> 27
//AB -> 28
//...
//示例 1:
//输入: "A"
//输出: 1
//示例 2:
//输入: "AB"
//输出: 28
//示例 3:
//输入: "ZY"
//输出: 701
public class Problem_0171_ExcelSheetColumnNumber {

    public static int titleToNumber(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int ans = 0;
        char[] str = s.toCharArray();
        for (char c : str) {
            ans = ans * 26 + (c - 'A' + 1);
        }
        return ans;
    }

}
