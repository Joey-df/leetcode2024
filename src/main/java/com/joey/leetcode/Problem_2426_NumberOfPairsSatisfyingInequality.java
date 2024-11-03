package com.joey.leetcode;

/**
 * @author pei.liu
 */
//2426. 满足不等式的数对数目
//给你两个下标从 0 开始的整数数组 nums1 和 nums2 ，两个数组的大小都为 n ，同时给你一个整数 diff ，统计满足以下条件的 数对 (i, j) ：
//0 <= i < j <= n - 1 且
//nums1[i] - nums1[j] <= nums2[i] - nums2[j] + diff.
//请你返回满足条件的 数对数目 。
//示例 1：
//输入：nums1 = [3,2,5], nums2 = [2,2,1], diff = 1
//输出：3
//解释：
//总共有 3 个满足条件的数对：
//1. i = 0, j = 1：3 - 2 <= 2 - 2 + 1 。因为 i < j 且 1 <= 1 ，这个数对满足条件。
//2. i = 0, j = 2：3 - 5 <= 2 - 1 + 1 。因为 i < j 且 -2 <= 2 ，这个数对满足条件。
//3. i = 1, j = 2：2 - 5 <= 2 - 1 + 1 。因为 i < j 且 -3 <= 2 ，这个数对满足条件。
//所以，我们返回 3 。
//示例 2：
//输入：nums1 = [3,-1], nums2 = [-2,2], diff = -1
//输出：0
//解释：
//没有满足条件的任何数对，所以我们返回 0 。
//
//提示：
//n == nums1.length == nums2.length
//2 <= n <= 10^5
//-10^4 <= nums1[i], nums2[i] <= 10^4
//-10^4 <= diff <= 10^4
public class Problem_2426_NumberOfPairsSatisfyingInequality {

    public long numberOfPairs(int[] nums1, int[] nums2, int diff) {
        //TODO
        return 0;
    }

}
