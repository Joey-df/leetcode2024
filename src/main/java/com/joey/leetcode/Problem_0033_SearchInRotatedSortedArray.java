package com.joey.leetcode;

// hard
// ignore
//33. 搜索旋转排序数组
//整数数组 nums 按升序排列，数组中的值 互不相同 。
//在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，
//使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
//例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
//给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
//示例 1：
//输入：nums = [4,5,6,7,0,1,2], target = 0
//输出：4
//在有序旋转数组中找到一个数
public class Problem_0033_SearchInRotatedSortedArray {

    // arr，原本是有序数组，旋转过，而且左部分长度不知道
    // 找num
    // num所在的位置返回
    public static int search(int[] arr, int num) {
        int L = 0;
        int R = arr.length - 1;
        int M = 0;
        while (L <= R) {
            // M = L + ((R - L) >> 1)
            M = (L + R) / 2;
            if (arr[M] == num) {
                return M;
            }
            // ...
            // arr[M] != num
            // [L] [M] [R] 不都一样的情况, 如何二分的逻辑
            if (arr[L] != arr[M]) {
                if (arr[M] > arr[L]) { // L...M 一定有序
                    if (num >= arr[L] && num < arr[M]) { //  找3  [L] == 1    [M] = 5   L...M - 1 去左侧二分
                        R = M - 1;
                    } else { // 找9    [L] == 2    [M] = 7   M... R 去右侧二分
                        L = M + 1;
                    }
                } else { // [L] > [M]    L....M  存在断点
                    if (num > arr[M] && num <= arr[R]) {
                        L = M + 1;
                    } else {
                        R = M - 1;
                    }
                }
            } else { // [L] [M] [R] 不都一样，  [L] === [M] -> [M]!=[R]
                if (arr[M] < arr[R]) {
                    if (num > arr[M] && num <= arr[R]) { //去右侧二分
                        L = M + 1;
                    } else {
                        R = M - 1; //去左侧二分
                    }
                } else {
                    if (num >= arr[L] && num < arr[M]) {
                        R = M - 1;
                    } else {
                        L = M + 1;
                    }
                }
            }
        }
        return -1;
    }


}
