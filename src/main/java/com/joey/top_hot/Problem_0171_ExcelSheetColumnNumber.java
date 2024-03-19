package com.joey.top_hot;

/**
 * 171. Excel表列序号
 * 给定一个Excel表格中的列名称，返回其相应的列序号。
 *
 * 例如，
 *
 *     A -> 1
 *     B -> 2
 *     C -> 3
 *     ...
 *     Z -> 26
 *     AA -> 27
 *     AB -> 28
 *     ...
 * 示例 1:
 *
 * 输入: "A"
 * 输出: 1
 * 示例 2:
 *
 * 输入: "AB"
 * 输出: 28
 * 示例 3:
 *
 * 输入: "ZY"
 * 输出: 701
 */
public class Problem_0171_ExcelSheetColumnNumber {

    public static int titleToNumber(String s) {
        if (s==null ||s.length()==0) {
            return 0;
        }
        int ans=0;
        char[] str = s.toCharArray();
        for (char c: str) {
            ans = ans*26 + (c-'A'+1);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(titleToNumber("ZY"));
        System.out.println("4321");
    }

    //整数字符串转成整数
    //例如"123",返回123
    public static int strToInt(String s) {
        if(s==null||s.length()==0) {
            return 0;
        }
        int ans=0;
        char[] str = s.toCharArray();
        for (char c: str) {
            ans = ans * 10 + (c-'0');
        }
        return ans;
    }
}
