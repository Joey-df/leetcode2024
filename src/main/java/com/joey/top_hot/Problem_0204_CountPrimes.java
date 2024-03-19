package com.joey.top_hot;

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

    public static int countPrimes(int n) {
        if (n < 3) {
            return 0;
        }
        // j已经不是素数了，f[j] = true;
        boolean[] f = new boolean[n];
        int count = n / 2; // 所有偶数都不要，还剩几个数
        // 跳过了1、2    3、5、7、
        for (int i = 3; i * i < n; i += 2) {
            if (f[i]) {
                continue;
            }
            // 3 -> 3 * 3 = 9   3 * 5 = 15   3 * 7 = 21
            // 7 -> 7 * 7 = 49  7 * 9 = 63
            // 13 -> 13 * 13  13 * 15
            for (int j = i * i; j < n; j += 2 * i) {
                if (!f[j]) {
                    --count;
                    f[j] = true;
                }
            }
        }
        return count;
    }

}
