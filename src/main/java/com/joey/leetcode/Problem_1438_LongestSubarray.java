package com.joey.leetcode;

import java.util.LinkedList;

/**
 * @author pei.liu
 */
//1438. 绝对差不超过限制的最长连续子数组
//与2762几乎是同一道题
//提示：
//1 <= nums.length <= 10^5
//1 <= nums[i] <= 10^9
//0 <= limit <= 10^9
public class Problem_1438_LongestSubarray {

    public static int[] arr;
    public static LinkedList<Integer> maxq = new LinkedList<>();
    public static LinkedList<Integer> minq = new LinkedList<>();

    //窗口内最大值最小值的更新结构
    public static int longestSubarray(int[] nums, int limit) {
        maxq.clear();
        minq.clear();
        arr = nums;
        int n = arr.length;
        int ans = -1;
        int r = 0;
        for (int i = 0; i < n; i++) { //枚举每一个开头的i位置，求一个答案，求全局max
            while (r < n && ok(limit, r)) {
                //push
                while (!maxq.isEmpty() && arr[maxq.peekLast()] <= arr[r]) {
                    maxq.pollLast();
                }
                maxq.addLast(r);
                while (!minq.isEmpty() && arr[minq.peekLast()] >= arr[r]) {
                    minq.pollLast();
                }
                minq.addLast(r);
                r++;
            }
            //r越界或者max-min > limit的初次位置
            ans = Math.max(ans, r - i); //收集以i位置开头的答案，这里有单调性
            //pop
            if (!maxq.isEmpty() && maxq.peekFirst() == i) maxq.pollFirst();
            if (!minq.isEmpty() && minq.peekFirst() == i) minq.pollFirst();
        }
        return ans;
    }

    private static boolean ok(int limit, int r) {
        int max = maxq.isEmpty() ? arr[r] : Math.max(arr[r], arr[maxq.peekFirst()]);
        int min = minq.isEmpty() ? arr[r] : Math.min(arr[r], arr[minq.peekFirst()]);
        return max - min <= limit;
    }

}
