package com.joey.leetcode;

/**
 * 88. 合并两个有序数组
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。
 * <p>
 * 示例 1：
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 * <p>
 * 示例 2：
 * 输入：nums1 = [1], m = 1, nums2 = [], n = 0
 * 输出：[1]
 */
public class Problem_0088_MergeSortedArray {

    public void merge(int[] arr1, int m, int[] arr2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int index = arr1.length - 1;
        while (p1 >= 0 && p2 >= 0) {
            if (arr1[p1] >= arr2[p2]) {
                arr1[index--] = arr1[p1--];
            } else {
                arr1[index--] = arr2[p2--];
            }
        }
        while (p1 >= 0) arr1[index--] = arr1[p1--];
        while (p2 >= 0) arr1[index--] = arr2[p2--];
    }
}
