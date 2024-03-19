package com.joey.top_hot;

/**
 * 300. 最长递增子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 */
public class Problem_0300_LongestIncreasingSubsequence {

    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        int[] dp = new int[N];
        int[] ends = new int[N];
        dp[0] = 1;
        ends[0] = nums[0];//ends[i]:所有找到的长度为i+1的递增子序列中最小结尾是啥
        int ans = dp[0];
        int right = 0;//ends的有效区
        for (int i = 1; i < N; i++) {
            int l = 0;
            int r = right;
            //在ends[0,right]上找>=nums[i]最左的位置
            while (l <= r) {
                int m = l + ((r - l) >> 1);
                if (ends[m] >= nums[i]) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            right = Math.max(right, l);
            ends[l] = nums[i];
            dp[i] = l + 1;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{3,2,43,13,22}));
    }
}
