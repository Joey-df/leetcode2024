package com.joey.leetcode;

/**
 * @author pei.liu
 */
//209. 长度最小的子数组
//给定一个含有 n 个正整数的数组和一个正整数 target 。
//找出该数组中满足其总和大于等于 target 的长度最小的子数组
//返回其长度。如果不存在符合条件的子数组，返回 0 。
public class Problem_0209_MinimumSizeSubarraySum {

    // 1，2，4，5，6， target=5
    public static int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int l=0;
        int r=0; // [l,r)
        int sum=0;
        int ans = Integer.MAX_VALUE;
        while (r < n) {
            //每次r都往右动
            sum += nums[r++];
            //r每动一下，就检查一下l能不能往右动
            while (sum -nums[l] >= target) {
                sum -= nums[l++];
            }
            if (sum >= target) {
                //System.out.println(l + ", " + r);
                ans = Math.min(ans, r - l);
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    public static void main(String[] args) {
        System.out.println(minSubArrayLen(5, new int[]{1,2,4,5,6}));
    }

}
