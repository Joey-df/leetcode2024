package com.joey.binarysearch;

/**
 * @author pei.liu
 */

//162. 寻找峰值
//给你一个相邻不等的整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
public class Problem162_FindPeakElement {

    public static int findPeakElement(int[] arr) {
        if (arr == null || arr.length == 0) return -1;
        if (arr.length == 1) return 0;
        if (arr.length == 2) return arr[0] > arr[1] ? 0 : 1;
        if (arr[0] > arr[1]) return 0;
        int n = arr.length;
        if (arr[n - 1] > arr[n - 2]) return n - 1;
        int l = 1, r = n - 2, m;
        while (l <= r) {
            m = l + (r - l) / 2;
            if (arr[m] < arr[m - 1]) {
                r = m - 1;
            } else if (arr[m] < arr[m + 1]) {
                l = m + 1;
            } else {
                return m;
            }
        }
        return -1;
    }
}
