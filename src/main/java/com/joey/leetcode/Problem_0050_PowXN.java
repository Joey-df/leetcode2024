package com.joey.leetcode;

// 快速幂
// x的n次方，n可能是负数
// 实现 pow(x, n) ，即计算 x 的整数 n 次幂函数（即，x^n ）。
//
//示例 1：
//输入：x = 2.00000, n = 10
//输出：1024.00000
//示例 2：
//输入：x = 2.10000, n = 3
//输出：9.26100
//示例 3：
//输入：x = 2.00000, n = -2
//输出：0.25000
//解释：2-2 = 1/22 = 1/4 = 0.25
//
//提示：
//-100.0 < x < 100.0
//-2^31 <= n <= 2^31 -1
//n 是一个整数
//要么 x 不为零，要么 n > 0 。
//-10^4 <= x^n <= 10^4
public class Problem_0050_PowXN {

    public static double myPow(double x, int n) {
        if (n == 0) return 1d;
        int pow = n;
        // Integer.MIN_VALUE的绝对值比系统最大大1个，+1之后就可以转为正数处理
        if (n == Integer.MIN_VALUE) {
            pow = Integer.MIN_VALUE + 1;
        }
        pow = pow < 0 ? -pow : pow;
        double ans = 1d;
        double t = x;
        // pow已经是正数
        while (pow != 0) {
            if ((pow & 1) == 1) {
                ans *= t;
            }
            pow >>= 1;
            t *= t;
        }
        if (n == Integer.MIN_VALUE) ans = ans * x;
        return n < 0 ? 1d / ans : ans;
    }

}
