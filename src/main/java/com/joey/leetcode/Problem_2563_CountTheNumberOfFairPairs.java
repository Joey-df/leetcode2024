package com.joey.leetcode;

import java.util.Arrays;

/**
 * @author pei.liu
 */
//2563. 统计公平数对的数目
//给你一个下标从 0 开始、长度为 n 的整数数组 nums ，和两个整数 lower 和 upper ，返回 公平数对的数目 。
//如果 (i, j) 数对满足以下情况，则认为它是一个 公平数对 ：
//0 <= i < j < n，且
//lower <= nums[i] + nums[j] <= upper
//示例 1：
//输入：nums = [0,1,7,4,4,5], lower = 3, upper = 6
//输出：6
//解释：共计 6 个公平数对：(0,3)、(0,4)、(0,5)、(1,3)、(1,4) 和 (1,5) 。
//示例 2：
//输入：nums = [1,7,9,2,5], lower = 11, upper = 11
//输出：1
//解释：只有单个公平数对：(2,3) 。
//
//提示：
//1 <= nums.length <= 10^5
//nums.length == n
//-10^9 <= nums[i] <= 10^9
//-10^9 <= lower <= upper <= 10^9
//二分查找
public class Problem_2563_CountTheNumberOfFairPairs {


    //0 <= i < j < n，且
    //lower <= nums[i] + nums[j] <= upper
    //要求数对的数量，对于一个固定的i位置数对的数量是确定的，数组可以排序
    //排序后，对于一个位置i，当nums[i]作为右数姿态情况下，
    //只需要在[0,i)范围上找 >= lower - nums[i]最左的位置，以及 <= upper - nums[i]最右的位置
    //本质上是两数之和
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        int n = nums.length;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int curr = nums[i];
            long left = left(nums, 0, i - 1, lower - curr);
            long right = right(nums, 0, i - 1, upper - curr);
            ans += left > right ? 0
                    : right - left + 1;
        }
        return ans;
    }

    public long left(int[] nums, int start, int end, int aim) {
        int l = start;
        int r = end;
        int ans = end + 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] >= aim) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    public int right(int[] nums, int start, int end, int aim) {
        int l = start;
        int r = end;
        int ans = start - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] <= aim) {
                ans = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }


    //lower <= nums[i] + nums[j] <= upper
    //等价于 <= upper 的数量 - <= lower-1 的数量
    //转化为求两数之和小于等于aim的数量
    //nums[i] + nums[j] <= aim
    //进一步转化为，固定下一个位置i，nums[i]作为右数的姿态，即求 <= aim - nums[i]最右的位置
    public long countFairPairs2(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        int n = nums.length;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int curr = nums[i];
            int r1 = right(nums, 0, i - 1, upper - curr);
            int r2 = right(nums, 0, i - 1, lower - curr - 1);
            ans += r1-r2;
        }
        return ans;
    }

}
