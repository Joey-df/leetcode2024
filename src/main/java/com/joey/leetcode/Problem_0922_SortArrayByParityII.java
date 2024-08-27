package com.joey.leetcode;

/**
 * @author pei.liu
 */
//922. 按奇偶排序数组 II
//给定一个非负整数数组 nums，  nums 中一半整数是 奇数 ，一半整数是 偶数 。
//对数组进行排序，以便当 nums[i] 为奇数时，i 也是 奇数 ；当 nums[i] 为偶数时， i 也是 偶数 。
//你可以返回 任何满足上述条件的数组作为答案 。
public class Problem_0922_SortArrayByParityII {

    //奇数放奇数位，偶数放偶数位
    public static int[] sortArrayByParityII(int[] nums) {
        int even = 0; //偶数位
        int odd = 1; //奇数位
        int n = nums.length;
        int p = n - 1;
        for (; even < n && odd < n; ) {
            if ((nums[p] & 1) == 0) {
                //偶数
                swap(nums, p, even);
                even += 2;
            } else {
                swap(nums, p, odd);
                odd += 2;
            }
        }
        return nums;
    }

    public static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        print(sortArrayByParityII(new int[]{2, 5}));
    }
}
