package com.joey.top_hot;


/**
 * 67. 二进制求和
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 * 输入为 非空 字符串且只包含数字 1 和 0。
 * 示例 1:
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 */
//和leetcode2同类型的题
public class Problem_0067_AddBinary {

    public static String addBinary(String a, String b) {
        assert (a != null && a.length() > 0 && b != null && b.length() > 0);
        char[] str1 = a.toCharArray();
        char[] str2 = b.toCharArray();
        int N = str1.length;
        int M = str2.length;
        int i = N - 1;
        int j = M - 1;
        int carry = 0;
        int sum = 0; //每一步的sum
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0) {
            sum = carry;
            sum += (i >= 0) ? (str1[i--] - '0') : 0;
            sum += (j >= 0) ? (str2[j--] - '0') : 0;
            sb.append(sum % 2);
            carry = sum / 2;
        }
        if (carry == 1) {
            sb.append(1);
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(addBinary("11","1"));
    }
}
