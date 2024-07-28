package com.joey.leetcode;

import java.util.LinkedList;

/**
 * @author pei.liu
 */
//1438. 绝对差不超过限制的最长连续子数组
//给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit 。
//如果不存在满足条件的子数组，则返回 0 。
//
//示例 1：
//输入：nums = [8,2,4,7], limit = 4
//输出：2
//解释：所有子数组如下：
//[8] 最大绝对差 |8-8| = 0 <= 4.
//[8,2] 最大绝对差 |8-2| = 6 > 4.
//[8,2,4] 最大绝对差 |8-2| = 6 > 4.
//[8,2,4,7] 最大绝对差 |8-2| = 6 > 4.
//[2] 最大绝对差 |2-2| = 0 <= 4.
//[2,4] 最大绝对差 |2-4| = 2 <= 4.
//[2,4,7] 最大绝对差 |2-7| = 5 > 4.
//[4] 最大绝对差 |4-4| = 0 <= 4.
//[4,7] 最大绝对差 |4-7| = 3 <= 4.
//[7] 最大绝对差 |7-7| = 0 <= 4.
//因此，满足题意的最长子数组的长度为 2 。
//示例 2：
//输入：nums = [10,1,2,4,7,2], limit = 5
//输出：4
//解释：满足题意的最长子数组是 [2,4,7,2]，其最大绝对差 |2-7| = 5 <= 5 。
//示例 3：
//输入：nums = [4,2,2,2,4,4,2,2], limit = 0
//输出：3
//
//提示：
//1 <= nums.length <= 10^5
//1 <= nums[i] <= 10^9
//0 <= limit <= 10^9
public class Problem_1438_LongestSubarray {

    //窗口内最大值最小值的更新结构
    public static int longestSubarray(int[] arr, int limit) {
        if (arr == null || arr.length == 0 || limit < 0) {
            return 0;
        }
        int N = arr.length;
        int ans = 0;
        LinkedList<Integer> maxWindow = new LinkedList<>();
        LinkedList<Integer> minWindow = new LinkedList<>();
        int R = 0;
        for (int L = 0; L < N; L++) { //枚举每一个窗口的左边界
            while (R < N) {
                while (!maxWindow.isEmpty() && arr[maxWindow.peekLast()] <= arr[R]) {
                    maxWindow.pollLast();
                }
                maxWindow.addLast(R);
                while (!minWindow.isEmpty() && arr[minWindow.peekLast()] >= arr[R]) {
                    minWindow.pollLast();
                }
                minWindow.addLast(R);
                if (arr[maxWindow.peekFirst()] - arr[minWindow.peekFirst()] > limit) { //R来到初次不达标的位置
                    break;
                } else {
                    R++;
                }
            }
            ans = Math.max(ans, R - L);
            if (maxWindow.peekFirst() == L) {
                maxWindow.pollFirst();
            }
            if (minWindow.peekFirst() == L) {
                minWindow.pollFirst();
            }
        }
        return ans;
    }

}
