package com.joey.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * @author pei.liu
 */
//532. 数组中的 k-diff 数对
//给你一个整数数组 nums 和一个整数 k，请你在数组中找出 不同的 k-diff 数对，并返回不同的 k-diff 数对 的数目。
//k-diff 数对定义为一个整数对 (nums[i], nums[j]) ，并满足下述全部条件：
//0 <= i, j < nums.length
//i != j
//|nums[i] - nums[j]| == k
//注意，|val| 表示 val 的绝对值。
//
//示例 1：
//输入：nums = [3, 1, 4, 1, 5], k = 2
//输出：2
//解释：数组中有两个 2-diff 数对, (1, 3) 和 (3, 5)。
//尽管数组中有两个 1 ，但我们只应返回不同的数对的数量。
//示例 2：
//输入：nums = [1, 2, 3, 4, 5], k = 1
//输出：4
//解释：数组中有四个 1-diff 数对, (1, 2), (2, 3), (3, 4) 和 (4, 5) 。
//示例 3：
//输入：nums = [1, 3, 1, 5, 4], k = 0
//输出：1
//解释：数组中只有一个 0-diff 数对，(1, 1) 。
//
//提示：
//1 <= nums.length <= 10^4
//-10^7 <= nums[i] <= 10^7
//0 <= k <= 10^7
public class Problem_0532_KDiffPairsInAnArray {

    //算法原型：求数组中差的绝对值<=k的数对个数
    //这道题要求对数对去重，只要保证每个开头的i位置为不同的元素即可
    public int findPairs(int[] nums, int k) {
        if (k == 0) {
            return f(nums);
        }
        Arrays.sort(nums);
        //数组元素去重
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        int[] arr = new int[set.size()];
        int i = 0;
        while (!set.isEmpty()) {
            arr[i++] = set.pollFirst();
        }
        return fun(arr, k) - fun(arr, k - 1);
    }

    //返回数组中词频大于1的元素个数
    public static int f(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int ans = 0;
        for (int key : map.keySet()) {
            ans += map.get(key) > 1 ? 1 : 0;
        }
        return ans;
    }

    //排序不会影响结果，so，排序利用单调性
    //算法原型：求数组中差的绝对值<=k的数对个数
    public static int fun(int[] arr, int k) {
        int n = arr.length;
        int ans = 0;
        int r = 1;
        for (int i = 0; i < n - 1; i++) { //枚举每个开头位置
            while (r < n && Math.abs(arr[r] - arr[i]) <= k) {
                r++;
            }
            //while出来两种情况：
            //1.r越界了
            //2.r没越界，但是Math.abs(arr[r]-arr[i]) > k
            //1 1 3 4 5 k=2
            ans += r - i - 1;
        }
        return ans;
    }

}
