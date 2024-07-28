package com.joey.leetcode;

/**
 * 371. 两整数之和
 * 不使用运算符 + 和 - ，计算两整数 a 、b 之和。
 * 示例 1:
 * 输入: a = 1, b = 2
 * 输出: 3
 * 示例 2:
 * 输入: a = -2, b = 3
 * 输出: 1
 */
public class Problem_0371_SumOfTwoIntegers {

    //a+b 等价于 (a、b的无进位信息) + (a、b的进位信息)
    public static int getSum(int a, int b) {
        //进位信息变成0，停，此时的a就是结果
        while (b != 0) {
            int eor = a ^ b; //无进位相加的信息
            b = (a & b) << 1; //进位信息
            a = eor;
        }
        return a;
    }

    //递归含义
    //返回a+b的值
    //a: 无进位相加的信息
    //b: 进位信息
    public static int getSum2(int a, int b) {
        //进位信息为0时，直接返回无进位相加的信息
        if (b == 0) return a;
        return getSum2(a ^ b, (a & b) << 1);
    }

}
