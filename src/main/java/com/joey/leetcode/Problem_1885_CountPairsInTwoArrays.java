package com.joey.leetcode;

import java.util.Arrays;

/**
 * @author pei.liu
 */
//1885. 统计数对
//给你两个长度为 n 的整数数组 nums1 和 nums2 ，
//找出所有满足 i < j 且 nums1[i] + nums1[j] > nums2[i] + nums2[j] 的数对 (i, j) 。
//返回满足条件数对的 个数 。
public class Problem_1885_CountPairsInTwoArrays {


    //nums1[i] + nums1[j] > nums2[i] + nums2[j]
    //nums1[i] - nums2[i] > nums2[j] - nums1[j]
    //(nums1[i] - nums2[i]) + (nums1[j] - nums2[j]) > 0
    //两个数组做差得到新数组nums
    //转化为在nums上求，nums[i] + nums[j] > 0 的数量
    //和i,j次序无关，所以先排序
    //nums[i] > -nums[j]
    //到每一个位置i，求[0,i-1]范围上 > -nums[i]的数量
    public static long countPairs(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = nums1[i] - nums2[i];
        }
        Arrays.sort(nums);
        long ans = 0;
        for (int i = 1; i < n; i++) {
            int l = left(nums, 0, i - 1, -nums[i]); //找大于-nums[i]最左的位置
            ans += l != -1 ? i - l : 0;
        }
        return ans;
    }

    public static int left(int[] nums, int l, int r, int target) {
        int ans = -1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] > target) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

}
