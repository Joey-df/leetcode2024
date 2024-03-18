package com.joey.sort;

/**
 * @author pei.liu
 */

//912. 排序数组
//https://leetcode.cn/problems/sort-an-array/description/
public class Problem_0912_QuickSort {

    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length < 2) return nums;
        int n = nums.length;
        sort(nums, 0, n - 1);
        return nums;
    }

    private void sort(int[] arr, int l, int r) {
        if (l >= r) return;
        int base = arr[l + (int) (Math.random() * (r - l + 1))];
        int[] range = partition(arr, l, r, base);
        sort(arr, l, range[0] - 1);
        sort(arr, range[1] + 1, r);
    }

    private int[] partition(int[] arr, int l, int r, int base) {
        if (l > r) return new int[]{-1, -1};
        if (l == r) return new int[]{l, r};
        int less = l - 1;// 小于区的右边界
        int more = r + 1;//大于区的左边届
        int i = l;
        while (i < more) {
            if (base == arr[i]) {
                i++;
            } else if (arr[i] < base) {
                swap(arr, ++less, i++);
            } else {
                swap(arr, --more, i);
            }
        }
        return new int[]{less + 1, more - 1};
    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
