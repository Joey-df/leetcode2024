package com.joey.leetcode;

import java.util.HashMap;

/**
 * @author pei.liu
 */
//1553. 吃掉 N 个橘子的最少天数
//厨房里总共有 n 个橘子，你决定每一天选择如下方式之一吃这些橘子：
//吃掉一个橘子。
//如果剩余橘子数 n 能被 2 整除，那么你可以吃掉 n/2 个橘子。
//如果剩余橘子数 n 能被 3 整除，那么你可以吃掉 2*(n/3) 个橘子。
//每天你只能从以上 3 种方案中选择一种方案。
//请你返回吃掉所有 n 个橘子的最少天数。
public class Problem_1553_MinimumNumberEatOranges {

    public int minDays(int n) {
        HashMap<Integer, Integer> dp = new HashMap<>();
        return f(n, dp);
    }

    //能按比例吃就按比例尺
    //一天吃一个，只是为了按比例吃服务的
    public int f(int n, HashMap<Integer, Integer> dp) {
        if (n <= 1) {
            return n;
        }
        if (dp.containsKey(n)) {
            return dp.get(n);
        }
        //14
        int p1 = n % 2 + 1 + f(n / 2, dp);
        int p2 = n % 3 + 1 + f(n / 3, dp);
        int ans = Math.min(p1, p2);
        dp.put(n, ans);
        return ans;
    }

}
