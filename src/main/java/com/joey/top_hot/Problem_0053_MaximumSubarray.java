package com.joey.top_hot;

//53. 最大子数组和
//给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
//子数组，是数组中的一个连续部分。
public class Problem_0053_MaximumSubarray {

    public int maxSubArray(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int n = arr.length;
        int[] dp = new int[n];
        dp[0] = arr[0];
        int ans = dp[0];
        for (int i = 1; i < n; i++) {
            int p1 = arr[i]; // 只包含i位置
            int p2 = arr[i] + dp[i - 1];
            dp[i] = Math.max(p1, p2);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }


}
