package com.joey.leetcode;

//415. 字符串相加(大数加法)
//给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
//你不能使用任何內建的用于处理大整数的库（比如 BigInteger），也不能直接将输入的字符串转换为整数形式。
//示例 1：
//输入：num1 = "11", num2 = "123"
//输出："134"
//示例 2：
//输入：num1 = "456", num2 = "77"
//输出："533"
//示例 3：
//输入：num1 = "0", num2 = "0"
//输出："0"
//提示：
//1 <= num1.length, num2.length <= 10^4
//num1 和num2 都只包含数字 0-9
//num1 和num2 都不包含任何前导零
public class Problem_0415_AddStrings {

    public String addStrings(String s, String t) {
        if (s.length() == 0 && t.length() == 0) return "";
        if (s.length() == 0 ^ t.length() == 0) {
            return s.length() == 0 ? t : s;
        }
        //都不为空
        char[] arr1 = s.toCharArray();
        char[] arr2 = t.toCharArray();
        //先将两个数组翻转，放假处理进位。最后再把结果翻转回来即可
        reverse(arr1);
        reverse(arr2);

        int carry = 0;
        int p1 = 0;
        int p2 = 0;

        StringBuilder sb = new StringBuilder();

        //两个数组长度可能不一样长，先处理公共长度的
        while (p1 < arr1.length && p2 < arr2.length) {
            int sum = (arr1[p1++] - '0') + (arr2[p2++] - '0') + carry;
            sb.append(sum % 10);
            carry = sum / 10;
        }

        //以下两个while循环只会执行一个，类似归并排序的代码结构
        while (p1 < arr1.length) {
            int sum = (arr1[p1++] - '0') + carry;
            sb.append(sum % 10);
            carry = sum / 10;
        }
        while (p2 < arr2.length) {
            int sum = (arr2[p2++] - '0') + carry;
            sb.append(sum % 10);
            carry = sum / 10;
        }
        if (carry == 1) {
            sb.append(1);
        }
        return sb.reverse().toString();

    }

    public void reverse(char[] str) {
        int l = 0;
        int r = str.length - 1;
        while (l < r) {
            char temp = str[l];
            str[l] = str[r];
            str[r] = temp;
            l++;
            r--;
        }
    }

}
