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

    //潜台词：a b都不为空
    public static String addBinary(String a, String b) {
        char[] str1 = a.toCharArray();
        char[] str2 = b.toCharArray();
        reverse(str1);
        reverse(str2);
        int N = str1.length;
        int M = str2.length;
        int i = 0;
        int j = 0;
        int carry = 0;
        int sum = 0; //每一步的sum
        StringBuilder sb = new StringBuilder();
        while (i < N || j < M) {
            sum = carry;
            sum += (i < N) ? (str1[i++] - '0') : 0;
            sum += (j < M) ? (str2[j++] - '0') : 0;
            sb.append(sum % 2);
            carry = sum / 2;
        }
        if (carry == 1) {
            sb.append(1);
        }
        return sb.reverse().toString();
    }

    private static void reverse(char[] str) {
        int l = 0;
        int r = str.length - 1;
        while (l < r) {
            swap(str, l++, r--);
        }
    }

    private static void swap(char[] str, int i, int j) {
        char t = str[i];
        str[i] = str[j];
        str[j] = t;
    }

}
