package com.joey.top_hot;

// 快速幂
// x的n次方，n可能是负数
public class Problem_0050_PowXN {

    public static double myPow(double x, int n) {
        if (n == 0) {
            return 1d;
        }
        //系统最小的绝对值比系统最大的绝对值大1个
        int pow = Math.abs(n == Integer.MIN_VALUE ? n + 1 : n);
        double ans = 1;
        double t = x;
        while (pow != 0) {
            if ((pow & 1) == 1) { //判断pow二进制的最后一位是否为1
                ans *= t;
            }
            t *= t;
            pow = pow >> 1;
        }
        if (n == Integer.MIN_VALUE) {
            ans = ans * x;
        }
        boolean neg = ((n >>> 31) & 1) == 1;//判断是否负数
        return neg ? (1d / ans) : ans;
    }

    public static void main(String[] args) {
        System.out.println(myPow(2,11));
    }
}
