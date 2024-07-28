package com.joey.leetcode;

/**
 * @author pei.liu
 */
//504. 七进制数
//给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。
//
//示例 1:
//输入: num = 100
//输出: "202"
//
//示例 2:
//输入: num = -7
//输出: "-10"
//
//提示：
//-10^7 <= num <= 10^7
public class Problem_0504_ConvertToBase7 {

    //先判断符号，如果是负数转为正数，最后再添加上负号
    //流程：每一步num对7取模，然后num变为num/7，直到num变为0
    public String convertToBase7(int num) {
        if (num == 0) {
            return "0";
        }
        boolean neg = false;
        if (num < 0) {
            neg = true;
            num = -num;
        }

        StringBuilder builder = new StringBuilder();
        while (num != 0) {
            builder.append(num % 7);
            num /= 7;
        }

        if (neg) {
            builder.append("-");
        }

        return builder.reverse().toString();
    }

}
