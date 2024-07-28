package com.joey.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 70. 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 * <p>
 * 示例 1：
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * <p>
 * 示例 2：
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 */
public class Problem_0070_ClimbingStairs {

    public static int climbStairs(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        //dp[i]：爬上i阶台阶的方法数
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    //总共n步要走，还有rest步要走
    //沿途的路径存在path里
    //ans收集答案
    public static void f(int n, int rest, List<String> path, List<List<String>> ans) {
        if (rest < 0) return;
        if (rest == 0) {
            ans.add(new ArrayList<>(path));
        } else { //rest>=1
            path.add("1阶");
            f(n, rest - 1, path, ans);
            path.remove(path.size() - 1);
            path.add("2阶");
            f(n, rest - 2, path, ans);
            path.remove(path.size() - 1);
        }
    }
}
