package com.joey.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 41. 缺失的第一个正数
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 * 示例 1：
 * 输入：nums = [1,2,0]
 * 输出：3
 * 示例 2：
 * 输入：nums = [3,4,-1,1]
 * 输出：2
 * 示例 3：
 * 输入：nums = [7,8,9,11,12]
 * 输出：1
 */
//leetcode268同一类题
public class Problem_0041_FirstMissingPositive {

    public static int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            if (n > 0)
                set.add(n);
        }
        int ans = 1;
        while (set.contains(ans)) {
            ans++;
        }
        return ans;
    }

    //时间复杂度：O(n)，
    //额外空间复杂度：O(1)
    //初始l=0；r=越界位置
    //表示：[0,l-1]范围上每一个位置i上已经放好了i+1的数
    //r表示预期的是收集1~r的数
    public static int firstMissingPositive2(int[] nums) {
        int l = 0;
        int r = nums.length;
        while (l < r) { //l==r时停
            if (nums[l] == l + 1) {
                l++;
            } else if (nums[l] <= l || nums[l] > r || nums[nums[l] - 1] == nums[l]) {
                swap(nums, l, --r);
            } else {
                swap(nums, l, nums[l] - 1); //把i放到i-1位置
            }
        }
        //[0,l-1]范围上每一个位置i上已经放好了i+1的数，即已经收集好[0,l]，所以返回l+1
        return l + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        System.out.println(firstMissingPositive(new int[]{1, 7, 8, 9, 11, 12}));
        System.out.println(firstMissingPositive2(new int[]{1, 7, 8, 9, 11, 12}));
    }
}
