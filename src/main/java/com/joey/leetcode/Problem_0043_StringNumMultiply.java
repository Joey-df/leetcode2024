package com.joey.leetcode;

//43. 字符串相乘
//给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
//注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
//示例 1:
//输入: num1 = "2", num2 = "3"
//输出: "6"
//示例 2:
//输入: num1 = "123", num2 = "456"
//输出: "56088"
//
//提示：
//1 <= num1.length, num2.length <= 200
//num1 和 num2 只能由数字组成。
//num1 和 num2 都不包含任何前导零，除了数字0本身。
public class Problem_0043_StringNumMultiply {

    // 其实在相乘或者相加计算过程的每一位，我们可以考虑先不去满10进位，
    // 等到计算完所有的相乘结果以后，最终将其加到一块，再去满10进位 ，
    // 最后的结果和普通竖式 一样，但却可以大大简化我们的模拟过程。(如下图所示)
    public String multiply(String num1, String num2) {
        int n = num1.length(), m = num2.length();
        int[] arr1 = new int[n], arr2 = new int[m];
        for (int i = n - 1; i >= 0; i--) {
            arr1[n - 1 - i] = num1.charAt(i) - '0'; //逆序并转为int
        }
        for (int i = m - 1; i >= 0; i--) {
            arr2[m - 1 - i] = num2.charAt(i) - '0'; //逆序并转为int
        }

        //num1*num2 的结果不会超过 n+m 位
        int[] arr = new int[n + m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i + j] += arr1[i] * arr2[j];
            }
        }
        int carry = 0; //进位信息
        for (int i = 0; i < arr.length; i++) {
            carry += arr[i];
            arr[i] = carry % 10;
            carry /= 10;
        }
        int k = arr.length - 1;
        while (k > 0 && arr[k] == 0) {
            k--;   //去除前导0
        }
        StringBuilder sb = new StringBuilder();
        //反转形成结果
        while (k >= 0) {
            sb.append((char) (arr[k--] + '0'));
        }
        return sb.toString();

    }
}
