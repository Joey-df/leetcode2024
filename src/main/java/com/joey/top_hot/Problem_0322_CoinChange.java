package com.joey.top_hot;

/**
 * 322. 零钱兑换
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。
 * 你可以认为每种硬币的数量是无限的。
 * <p>
 * 示例 1：
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * <p>
 * 示例 2：
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * <p>
 * 示例 3：
 * 输入：coins = [1], amount = 0
 * 输出：0
 * <p>
 * 示例 4：
 * 输入：coins = [1], amount = 1
 * 输出：1
 * <p>
 * 示例 5：
 * 输入：coins = [1], amount = 2
 * 输出：2
 */
public class Problem_0322_CoinChange {

    public static int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount < 0) {
            return 0;
        }
        return func(coins, amount);
    }


    public static int func(int[] arr, int aim) {
        //dp[i][j]含义：
        //arr[0..i]自由选择，凑出j元，所需最少硬币数
        int[][] dp = new int[arr.length][aim + 1];


        //第一列:arr[0,i]自由选择，凑出0元所需0张
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 0;
        }
        //第一行:arr[0,0]自由选择，凑出j元  看是否能整除
        for (int i = 1; i < aim + 1; i++) {
            dp[0][i] = ((i % arr[0] == 0) ? i / arr[0] : -1);
        }
        //普遍位置
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < aim + 1; j++) {
                //涉及枚举行为优化
                dp[i][j] = Integer.MAX_VALUE;
                if (dp[i - 1][j] != -1) {
                    dp[i][j] = dp[i - 1][j];
                }
                if (j - arr[i] >= 0 && dp[i][j - arr[i]] != -1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - arr[i]] + 1);
                }
                if (dp[i][j] == Integer.MAX_VALUE) {
                    dp[i][j] = -1;
                }
            }
        }
        return dp[arr.length - 1][aim];
    }

    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{1, 2, 5}, 11));
    }
}
