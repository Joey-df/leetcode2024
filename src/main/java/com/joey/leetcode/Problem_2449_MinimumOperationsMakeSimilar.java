package com.joey.leetcode;

import java.util.Arrays;

/**
 * @author pei.liu
 */

//2449. 使数组相似的最少操作次数
//给你两个正整数数组 nums 和 target ，两个数组长度相等。
//在一次操作中，你可以选择两个 不同 的下标 i 和 j ，其中 0 <= i, j < nums.length ，并且：
//令 nums[i] = nums[i] + 2 且
//令 nums[j] = nums[j] - 2 。
//如果两个数组中每个元素出现的频率相等，我们称两个数组是 相似 的。
//请你返回将 nums 变得与 target 相似的最少操作次数。测试数据保证 nums 一定能变得与 target 相似。
//
//示例 1：
//输入：nums = [8,12,6], target = [2,14,10]
//输出：2
//解释：可以用两步操作将 nums 变得与 target 相似：
//- 选择 i = 0 和 j = 2 ，nums = [10,12,4] 。
//- 选择 i = 1 和 j = 2 ，nums = [10,14,2] 。
//2 次操作是最少需要的操作次数。
public class Problem_2449_MinimumOperationsMakeSimilar {

    //其中 0 <= i, j < nums.length ，并且：
    //令 nums[i] = nums[i] + 2 且
    //令 nums[j] = nums[j] - 2 。
    //这个条件非常重要，说明每次操作都不会改变数组元素的奇偶性
    //所以将nums和target排序，对应位置元素一定是进行若干次 +2 或者 -2 之后的结果
    //而且每次就进行 +2 和 -2 两种操作，元素变化的绝对值都是4
    public static long makeSimilar(int[] nums, int[] target) {
        int n = nums.length;
        int idx1 = split(nums);
        int idx2 = split(target);
        Arrays.sort(nums, 0, idx1);
        Arrays.sort(nums, idx1, n);
        Arrays.sort(target, 0, idx2);
        Arrays.sort(target, idx2, n);

        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Math.abs(target[i] - nums[i]);
        }

        return sum / 4;
    }

    //将数组进行奇偶拆分，返回偶数元素下标的第一个位置
    public static int split(int[] arr) {
        int idx = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & 1) == 1) { //奇数
                swap(arr, i, idx++);
            }
        }
        return idx;
    }

    public static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }


    public static void main(String[] args) {
        int[] nums = {4,634,1059,798,736,1102,1798,1198,1566,1293,165,1146,1314,1068,1555,448,839};
        int[] target = {171,1775,1412,163,1004,854,716,1839,182,2000,696,1999,296,1152,478,306,1680};
        System.out.println(makeSimilar(nums, target));
    }
}
