package com.joey.leetcode;

/**
 * 123. 买卖股票的最佳时机 III
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 */
public class Problem_0123_BestTimeToBuyAndSellStockIII {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        int onceDoneMinusBuyMax = -prices[0]; // 做完一笔交易并且完成第二次买入的最大化收益
        int min = prices[0]; // 为了更新onceDoneMax
        int onceDoneMax = 0; // 做完一笔交易的最大收益
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            onceDoneMax = Math.max(onceDoneMax, prices[i] - min);
            min = Math.min(min, prices[i]); // update min
            ans = Math.max(ans, onceDoneMinusBuyMax + prices[i]);
            onceDoneMinusBuyMax = Math.max(onceDoneMinusBuyMax, onceDoneMax - prices[i]);
        }
        return ans;
    }

}
