package com.joey.top_hot;

/**
 * 371. 两整数之和
 * 不使用运算符 + 和 - ​​​​​​​，计算两整数 ​​​​​​​a 、b ​​​​​​​之和。
 * <p>
 * 示例 1:
 * 输入: a = 1, b = 2
 * 输出: 3
 * <p>
 * 示例 2:
 * 输入: a = -2, b = 3
 * 输出: 1
 */
public class Problem_0371_SumOfTwoIntegers {

    //a+b 等价于 a、b的进位信息 + a、b的进位信息
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
    public static int func(int a, int b) {
        if (b == 0) return a;
        return func(a ^ b, (a & b) << 1);
    }

    public static void main(String[] args) {
        System.out.println(getSum(22, 29));
        System.out.println(func(22, 29));
    }
}
