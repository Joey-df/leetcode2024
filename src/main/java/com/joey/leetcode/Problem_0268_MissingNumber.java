package com.joey.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 268. 丢失的数字
 * 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
 *
 * 进阶：
 * 你能否实现线性时间复杂度、仅使用额外常数空间的算法解决此问题?
 */
//leetcode41同一类题
public class Problem_0268_MissingNumber {

    //额外空间复杂度O(n)
    public int missingNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int n : nums) set.add(n);
        int ans = 0;
        while (set.contains(ans)) {
            ans++;
        }
        return ans;
    }

    //额外空间复杂度O(1)
    public int missingNumber2(int[] nums) {
        int L = 0;//[0,L-1]上已经放好了i的数
        int R = nums.length; // [R...]为垃圾区
        while (L < R) {
            if (nums[L] == L) {
                L++;
            } else if (nums[L] < L || nums[L] >= R || nums[nums[L]] == nums[L]) {
                swap(nums, L, --R);
            } else {
                swap(nums, L, nums[L]);
            }
        }
        return L;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
