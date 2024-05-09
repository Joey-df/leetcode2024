package com.joey.top_hot;

/**
 * 191. 位1的个数
 * 编写一个函数，输入是一个无符号整数（以二进制串的形式），
 * 返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）。
 */
public class Problem_0191_NumberOf1Bits {

    public static int hammingWeight(int n) {
        int ans = 0;
        while (n != 0) {
            int ro = n & (~n + 1); //提取出最右侧的1
            n ^= ro; //消掉n最右侧的1
            ans++;
        }
        return ans;
    }

}
