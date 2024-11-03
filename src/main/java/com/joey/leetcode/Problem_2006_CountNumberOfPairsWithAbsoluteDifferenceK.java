package com.joey.leetcode;

import java.util.Arrays;

/**
 * @author pei.liu
 */
//2006. 差的绝对值为 K 的数对数目
//给你一个正数数组 nums 和一个整数 k ，请你返回数对 (i, j) 的数目，满足 i < j 且 |nums[i] - nums[j]| == k 。
//|x| 的值定义为：
//如果 x >= 0 ，那么值为 x 。
//如果 x < 0 ，那么值为 -x 。
public class Problem_2006_CountNumberOfPairsWithAbsoluteDifferenceK {

    //算法原型：差值的绝对值<=K的数对个数
    public static int countKDifference(int[] nums, int k) {
        return fun(nums, k)
                - fun(nums, k - 1);
    }

    //给定数组arr，元素可正可负可0
    //返回任意两数差的绝对值<=k的数对个数
    public static int fun(int[] arr, int k) {
        //排序与不排序不会影响结果，so，排序，产生单调性
        Arrays.sort(arr);
        // arr: [1 1 2 2]
        // k=1
        int n = arr.length;
        int ans = 0;
        int r = 1;
        for (int i = 0; i < n; i++) { //枚举每个i位置
            while (r < n && Math.abs(arr[r] - arr[i]) <= k) {
                r++;
            }
            //while出来两种情况
            //1.r=n即越界
            //2.r<n，但是，Math.abs(arr[r]- arr[i]) > k
            ans += r - i; //收集i位置的答案
        }
        return ans;
    }

}
