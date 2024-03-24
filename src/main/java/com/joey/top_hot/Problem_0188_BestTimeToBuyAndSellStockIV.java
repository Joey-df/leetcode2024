package com.joey.top_hot;

//188. 买卖股票的最佳时机 IV
//给你一个整数数组 prices 和一个整数 k ，其中 prices[i] 是某支给定的股票在第 i 天的价格。
//设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。也就是说，你最多可以买 k 次，卖 k 次。
//注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
public class Problem_0188_BestTimeToBuyAndSellStockIV {

    public static int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        int n = prices.length;
        if (k >= n / 2) {
            int ans = 0;
            for (int i = 1; i < prices.length; i++) {
                ans += Math.max(prices[i] - prices[i - 1], 0);
            }
            return ans;
        }
        //dp[i][j]的含义：
        //在prices(0,i)上做不超过j次交易获取的最大收益
        int[][] dp = new int[n][k + 1];
        // 第一行：在prices(0,0)上做不超过k次交易的最大收益，全0
        // 第一列：在prices(0...)上做不超过0次交易的最大收益，全0
        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j <= k; j++) {
                //1)prices[i]不参与
                dp[i][j] = dp[i - 1][j];
                //2)prices[i]参与：参与最后一次交易的卖出时机（买入减，卖出加）
                //枚举最后一次的买入时机
                for (int p = 0; p <= i; p++) {
                    dp[i][j] = Math.max(dp[i][j], dp[p][j - 1] - prices[p] + prices[i]);
                }
            }
        }
        return dp[n - 1][k];
    }
}
