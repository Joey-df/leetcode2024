package com.joey.leetcode;

//53. 最大子数组和
//给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
//子数组，是数组中的一个连续部分。
public class Problem_0053_MaximumSubarray {

    public int maxSubArray1(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        dp[0] = arr[0];
        int ans = arr[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(arr[i], arr[i] + dp[i - 1]);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    //空间压缩
    public int maxSubArray(int[] arr) {
        int n = arr.length;
        int pre = arr[0];
        int ans = arr[0];
        for (int i = 1; i < n; i++) {
            int cur = Math.max(arr[i], arr[i] + pre);
            ans = Math.max(ans, cur);
            pre = cur;
        }
        return ans;
    }

}
