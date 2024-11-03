package com.joey.leetcode;

/**
 * @author pei.liu
 */
//493. 翻转对
//给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
//你需要返回给定数组中的重要翻转对的数量。
//示例 1:
//输入: [1,3,2,3,1]
//输出: 2
//示例 2:
//输入: [2,4,3,5,1]
//输出: 3
//注意:
//给定数组的长度不会超过50000。
//输入数组中的所有数字都在32位整数的表示范围内。
//标签: 归并分治
public class Problem_0493_ReversePairs {

    public static int reversePairs(int[] nums) {
        return reversePairs(nums, 0, nums.length - 1);
    }

    //返回arr[l,r]翻转对数量
    //并且arr[l,r]变有序
    public static int reversePairs(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int m = l + (r - l) / 2;
        return reversePairs(arr, l, m)
                + reversePairs(arr, m + 1, r)
                + merge(arr, l, m, r);
    }

    //arr[l,m], arr[m+1,r]已经变有序
    private static int merge(int[] arr, int l, int m, int r) {
        //统计部分
        int ans = 0;
        //10,20,40, 2,4,9
        //l      m      r
        //遍历[l,m]每个位置求一个数量，累加
        int j = m + 1;
        for (int i = l; i <= m; i++) {
            while (j <= r && arr[j] * 2L < arr[i]) {
                j++;
            }
            //获取i位置的答案
            ans += j - (m + 1);
        }
        //归并部分
        int[] help = new int[r - l + 1];
        int a = l;
        int b = m + 1;
        int ci = 0;
        while (a <= m && b <= r) {
            help[ci++] = arr[a] <= arr[b] ? arr[a++] : arr[b++];
        }
        while (a <= m) {
            help[ci++] = arr[a++];
        }
        while (b <= r) {
            help[ci++] = arr[b++];
        }
        //刷回去
        for (int i = 0; i < help.length; i++) {
            arr[i + l] = help[i];
        }
        return ans;
    }

}
