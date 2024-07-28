package com.joey.leetcode;

/**
 * 190. 颠倒二进制位
 * 颠倒给定的 32 位无符号整数的二进制位。
 */
public class Problem_0190_ReverseBits {
    //每次取32位数的最后一位
    public int reverseBits(int n) {
        int ret = 0;
        for (int i = 0; i < 32; i++) {//循环32次
            if ((n & 1) == 0) { //判断最后一位是0还是1
                ret = ret * 2;
            } else {
                ret = ret * 2 + 1;
            }
            n = n >> 1; //将n右移
        }
        return ret;
    }
}
