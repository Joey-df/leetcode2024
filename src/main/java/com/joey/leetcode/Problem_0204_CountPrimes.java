package com.joey.leetcode;

// ignore

/**
 * 204. 计数质数
 * 统计所有小于非负整数 n 的质数的数量。
 * <p>
 * 示例 1：
 * 输入：n = 10
 * 输出：4
 * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 * <p>
 * 示例 2：
 * 输入：n = 0
 * 输出：0
 * <p>
 * 示例 3：
 * 输入：n = 1
 * 输出：0
 */
public class Problem_0204_CountPrimes {

    //求小于n的质数的数量
    //埃氏筛
    public int countPrimes(int n) {
        n--;
        if (n<2) return 0;
        boolean[] visit = new boolean[n + 1];//true:合数，false:质数，一开始认为都是质数
        for (int i = 2; i * i <= n; i++) {
            if (!visit[i]) { //如果发现一个质数，把这个质数往后的一批数都标记为合数
                for (int j = i * i; j <= n; j += i) {
                    visit[j] = true;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n + 1; i++) {
            if (!visit[i]) {
                ans++;
            }
        }
        return ans;
    }

}
