package com.joey.leetcode;

import java.util.Arrays;

/**
 * @author pei.liu
 */
//462. 最小操作次数使数组元素相等 II
//给你一个长度为 n 的整数数组 nums ，返回使所有数组元素相等需要的最小操作数。
//在一次操作中，你可以使数组中的一个元素加 1 或者减 1 。
//
//示例 1：
//输入：nums = [1,2,3]
//输出：2
//解释：
//只需要两次操作（每次操作指南使一个元素加 1 或减 1）：
//[1,2,3]  =>  [2,2,3]  =>  [2,2,2]
//示例 2：
//输入：nums = [1,10,2,9]
//输出：16
//
//提示：
//n == nums.length
//1 <= nums.length <= 10^5
//-10^9 <= nums[i] <= 10^9
public class Problem_0462_MinMoves2 {

    //思路：
    //每次可以对一个数组元素加一或者减一，求最小的改变次数。
    //这是个典型的相遇问题，移动距离最小的方式是所有元素都移动到中位数。理由如下：
    //设 m 为中位数（排序后处于中点的数）。a 和 b 是 m 两边的两个元素，且 b > a。
    //要使 a 和 b 相等，它们总共移动的次数为 b - a，这个值等于 (b - m) + (m - a)，
    //也就是把这两个数移动到中位数的移动次数。
    //设数组长度为 N，则可以找到 N/2 对 a 和 b 的组合，使它们都移动到 m 的位置。
    //时间复杂度：O(n*log(n))
    //空间复杂度：O(log(n))。排序需要O(log(n))的递归栈空间。
    public int minMoves2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        int n = nums.length;
        int ans = 0;
        int mid = nums[n >> 1]; // 中位数
        for (int i=0;i < n; i++) {
            ans += Math.abs(mid - nums[i]);
        }
        return ans;
    }

}
