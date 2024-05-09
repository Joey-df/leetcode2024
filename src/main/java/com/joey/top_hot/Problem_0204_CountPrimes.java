package com.joey.top_hot;

// ignore
/**
 * 204. 计数质数
 * 统计所有小于非负整数 n 的质数的数量。
 *
 * 示例 1：
 * 输入：n = 10
 * 输出：4
 * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 *
 * 示例 2：
 * 输入：n = 0
 * 输出：0
 *
 * 示例 3：
 * 输入：n = 1
 * 输出：0
 */
public class Problem_0204_CountPrimes {

    public int countPrimes(int n) {
        int ans = 0;
        for (int i = 2; i < n; i++) {
            ans += isPrime(i) ? 1 : 0;
        }
        return ans;
    }

    //判断x是否为质数（素数）
    //如果一个是y是x的因子，那么分解x的两个数一定是成对出现，较小的那个数一定在[2,根号x]之间
    //如100，//所有的因子为 2 5 10 20 50
    public boolean isPrime(int x) {
        //在[2,根号x]之间枚举
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;

    }

}
