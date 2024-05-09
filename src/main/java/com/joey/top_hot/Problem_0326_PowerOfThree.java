package com.joey.top_hot;

/**
 * 326. 3的幂
 * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。如果是，返回 true ；否则，返回 false 。
 *
 * 整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3^x
 */
public class Problem_0326_PowerOfThree {

    public boolean isPowerOfThree(int n) {
        while (n != 0 && n % 3 == 0) {
            n /= 3;
        }
        //如果不能被3整除，会直接跳出，比如n=10
        //如果能被3整除，每次n会变成n/3，最后会变为1
        return n == 1;
    }

}
