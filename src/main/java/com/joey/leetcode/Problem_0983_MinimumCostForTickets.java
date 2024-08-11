package com.joey.leetcode;

import com.joey.od.s200.二叉树计算;

import java.util.Arrays;

/**
 * @author pei.liu
 */
//983. 最低票价
//在一个火车旅行很受欢迎的国度，你提前一年计划了一些火车旅行。在接下来的一年里，你要旅行的日子将以一个名为 days 的数组给出。
//每一项是一个从 1 到 365 的整数。
//火车票有 三种不同的销售方式 ：
//一张 为期一天 的通行证售价为 costs[0] 美元；
//一张 为期七天 的通行证售价为 costs[1] 美元；
//一张 为期三十天 的通行证售价为 costs[2] 美元。
//通行证允许数天无限制的旅行。
//例如，如果我们在第 2 天获得一张 为期 7 天 的通行证，
//那么我们可以连着旅行 7 天：第 2 天、第 3 天、第 4 天、第 5 天、第 6 天、第 7 天和第 8 天。
//返回 你想要完成在给定的列表 days 中列出的每一天的旅行所需要的最低消费 。
public class Problem_0983_MinimumCostForTickets {

    public static int[] selects = {1, 7, 30};

    public static int mincostTickets(int[] days, int[] costs) {
        if (days == null || days.length == 0) {
            return 0;
        }
        int n = days.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return fun(days, 0, costs, dp);
    }

    //递归含义
    //从i位置出发，最低花费是多少
    public static int fun(int[] days, int i, int[] costs, int[] dp) {
        if (i == days.length) {
            return 0;
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        //i还没到结尾位置
        int ans = Integer.MAX_VALUE;
        //存在三种选择
        //可能性1：选择1天的票，管一天
        //可能性2：选择7天的票，管7天
        //可能性3：选择30天的票，管30天
        //3,4,6,8,10,20
        int j = i;
        for (int k = 0; k < 3; k++) {
            while (j < days.length && days[j] < days[i] + selects[k]) {
                j++;
            }
            ans = Math.min(ans, costs[k] + fun(days, j, costs, dp));
        }
        dp[i] = ans;
        return ans;
    }

    public static int mincostTickets2(int[] days, int[] costs) {
        if (days == null || days.length == 0) {
            return 0;
        }
        int n = days.length;
        int[] dp = new int[n + 1];
        dp[n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Integer.MAX_VALUE;
            int j = i;
            for (int k = 0; k < 3; k++) {
                while (j < days.length && days[j] < days[i] + selects[k]) {
                    j++;
                }
                dp[i] = Math.min(dp[i], costs[k] + dp[j]);
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        //days = [1,4,6,7,8,20], costs = [2,7,15]
        int[] days = new int[]{1, 4, 6, 7, 8, 20};
        int[] costs = new int[]{2, 7, 15};
        System.out.println(mincostTickets2(days, costs));
    }


}
