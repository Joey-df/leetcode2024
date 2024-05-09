package com.joey.top_hot;

import java.util.Arrays;

/**
 * 300. 最长递增子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 */
public class Problem_0300_LongestIncreasingSubsequence {

    //平凡解
    //时间复杂度：O(n^2)
    //dp[i]的含义：以i位置结尾的最长递增子序列是多长
    public static int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1); //先初始化每个位置的值为1（最小是1）
        for (int i = 1; i < nums.length; i++) {
            // 每个位置求lis 需要枚举i前面的所有位置
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int ans = 1;
        for (int i = 0; i < dp.length; i++) {
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    //最优解
    //时间复杂度：O(nlogn)
    public static int lengthOfLIS2(int[] nums) {
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

}
