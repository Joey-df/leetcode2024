package com.joey.leetcode;

import java.util.Arrays;

// 410. 分割数组的最大值(画匠问题)
// 给定一个非负整数数组 nums 和一个整数 m
// 你需要将这个数组分成 m 个非空的连续子数组。
// 设计一个算法使得这 m 个子数组各自和的最大值最小。
// 测试链接 : https://leetcode.cn/problems/split-array-largest-sum/
// 此问题还有dp的解法(四边形不等式优化)，在体系学习班里有讲
public class Problem_0410_SplitArrayLargestSum {

    //画匠问题
    //分成若干个部分，每部分必须连续，让每部分的累加和的最大值尽量的小
    //这个指标有范围：[0,sum]，整个数组分成一段，就是sum
    //在这个范围上进行二分，对每一个m，求每部分累加和不超过m（即最大值是m）的情况下，整个数组能分成几段，这个过程O(n)可以求出
    //单调性分析
    //如果对于m，求出段数如果>k，说明m小了，m的左边求出的段数只会更加的大于k，不符合要求
    //反之，求出的段数<=k，记录答案，在左边继续二分，寻找更小的值
    //最终求出题目答案
    public static int splitArray(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        int l = 0;
        int r = sum;
        int ans = Integer.MAX_VALUE;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (parts(nums, m) <= k) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    //每部分累加和在不超过m的前提小
    //数组能分成几个部分，返回
    public static int parts(int[] nums, int limit) {
        for (int num : nums) {
            if (num > limit) {
                return Integer.MAX_VALUE;
            }
        }
        int n = nums.length;
        int windowSum = 0;
        int ans = 0;
        for (int i = 0; i < n; ) {
            while (i < n && windowSum <= limit && windowSum + nums[i] <= limit) {
                windowSum += nums[i++];
            }
            //while出来两种情况
            //1 i越界了
            //2 i没越界，但是i来到了windowSum > limit的第一个位置
            ans++;
            windowSum = 0;
        }
        return ans;
    }

}

