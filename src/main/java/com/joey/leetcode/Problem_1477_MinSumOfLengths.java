package com.joey.leetcode;

import java.util.*;

/**
 * @author pei.liu
 */
//1477. 找两个和为目标值且不重叠的子数组
//给你一个整数数组 arr 和一个整数值 target 。
//请你在 arr 中找 两个互不重叠的子数组 且它们的和都等于 target 。可能会有多种方案，请你返回满足要求的两个子数组长度和的 最小值 。
//请返回满足要求的最小长度和，如果无法找到这样的两个子数组，请返回 -1 。
public class Problem_1477_MinSumOfLengths {

    //利用前缀和
    //对于每个位置，求左边的最小长度，以及 右边的最小长度
    //抓全局最小长度和
    public static int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        //key：sum
        //value：index
        HashMap<Integer, Integer> map = new HashMap<>();
        int left = Integer.MAX_VALUE;
        int sum = 0;
        map.put(0, -1);
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            map.put(sum, i);
        }
        int ans = Integer.MAX_VALUE;
        int currSum = 0;
        for (int i = 0; i < n; i++) {
            currSum += arr[i];
            if (map.containsKey(currSum - target)) {
                left = Math.min(left, i - map.get(currSum - target));
            }
            if (map.containsKey(currSum + target) && left != Integer.MAX_VALUE) {
                ans = Math.min(ans, map.get(currSum + target) - i + left);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

}
