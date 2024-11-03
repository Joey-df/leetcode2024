package com.joey.leetcode;

/**
 * @author pei.liu
 */
//209. 长度最小的子数组
//给定一个含有 n 个正整数的数组和一个正整数 target 。
//找出该数组中满足其总和大于等于 target 的长度最小的子数组
//返回其长度。如果不存在符合条件的子数组，返回 0 。
public class Problem_0209_MinimumSizeSubarraySum {

    //正数数组，范围越大，累加和只会变大，存在单调性
    //so 滑动窗口
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;
        int r = 0;
        int windowSum = 0;
        for (int i = 0; i < n; i++) {
            while (r < n && windowSum < target) {
                windowSum += nums[r++];
            }
            //while出来两种情况
            //1 r越界了
            //2 r不越界但是windowSum >= target
            //clt ans
            if (windowSum >= target)
                ans = Math.min(ans, r - i);
            //i即将++
            windowSum -= nums[i];
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }


}
