package com.joey.top_hot;

/**
 * 172. 阶乘后的零
 * 给定一个整数 n，返回 n! 结果尾数中零的数量。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 3
 * 输出: 0
 * 解释: 3! = 6, 尾数中没有零。
 * 示例 2:
 * <p>
 * 输入: 5
 * 输出: 1
 * 解释: 5! = 120, 尾数中有 1 个零.
 */

public class Problem_0172_FactorialTrailingZeroes {

    //显而易见的结论：
    //一个数n求阶乘，1*2*3*4*5*6*7*8*9...*n
    //2的因子一定比5的因子多
    //一个5结合一个2得到一个0
    //so，n阶乘后结果中0的数量等于5因子的数量
    public static int trailingZeroes(int n) {
        int ans = 0;
        while (n != 0) {
            ans += n / 5; // n/5  n/25  n/125
            n /= 5;
        }
        return ans;
    }

}
