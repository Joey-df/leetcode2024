package com.joey.leetcode;

//581. 最短无序连续子数组
//给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
//请你找出符合题意的 最短 子数组，并输出它的长度。
//
//示例 1：
//输入：nums = [2,6,4,8,10,9,15]
//输出：5
//解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
//示例 2：
//输入：nums = [1,2,3,4]
//输出：0
//示例 3：
//输入：nums = [1]
//输出：0
//
//提示：
//1 <= nums.length <= 10^4
//-10^5 <= nums[i] <= 10^5
public class Problem_0581_ShortestUnsortedContinuousSubarray {

    //让数组整体有序，最短需要排序的子数组是多长
    //一趟找到右边界
    //一趟找到左边界
    public static int findUnsortedSubarray(int[] nums) {
        //[2,  6,4,8,10,9, 15]
        int n = nums.length;
        int r = -1; //需要排序的右边界
        int l = n; //需要排序的左边界
        int lmax = nums[0];
        int rmin = nums[n - 1];
        for (int i = 1; i < n; i++) {
            if (nums[i] < lmax) {
                r = i;
            }
            lmax = Math.max(lmax, nums[i]);
        }
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] > rmin) {
                l = i;
            }
            rmin = Math.min(rmin, nums[i]);
        }
        return Math.max(0, r - l + 1);
    }

}
