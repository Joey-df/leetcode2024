package com.joey.top_hot;

/**
 * 69. x 的平方根
 * 实现 int sqrt(int x) 函数。
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * <p>
 * 示例 1:
 * 输入: 4
 * 输出: 2
 * <p>
 * 示例 2:
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 * 由于返回类型是整数，小数部分将被舍去。
 */
public class Problem_0069_SqrtX {

    public static int mySqrt(int x) {
        if (x == 0) return 0;
        if (x < 4) return 1;
        int l = 2, r = x;
        int ans = 2;
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            if (Math.pow(m, 2) > x) {
                r = m - 1;
            } else { // Math.pow(m, 2)<=x时记录答案，然后在右边二分
                ans = m;
                l = m + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(mySqrt(100));
    }
}
