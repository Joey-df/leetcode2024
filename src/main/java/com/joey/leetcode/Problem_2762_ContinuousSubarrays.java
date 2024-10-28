package com.joey.leetcode;

import java.util.LinkedList;

/**
 * @author pei.liu
 */
//2762. 不间断子数组
//给定一个数组arr，对于arr中任意一个子数组，如果该子数组中任意两个位置的元素差值的绝对值<=2，称该子数组达标
//返回达标的子数组个数
//与1438几乎是同一道题
//示例 1：
//
//输入：nums = [5,4,2,4]
//输出：8
//解释：
//大小为 1 的不间断子数组：[5], [4], [2], [4] 。
//大小为 2 的不间断子数组：[5,4], [4,2], [2,4] 。
//大小为 3 的不间断子数组：[4,2,4] 。
//没有大小为 4 的不间断子数组。
//不间断子数组的总数目为 4 + 3 + 1 = 8 。
//除了这些以外，没有别的不间断子数组。
//提示：
//1 <= nums.length <= 10^5
//1 <= nums[i] <= 10^9
public class Problem_2762_ContinuousSubarrays {

    public static int[] arr;
    public static LinkedList<Integer> maxq = new LinkedList<>(); //大->小
    public static LinkedList<Integer> minq = new LinkedList<>(); //小->大

    public static long continuousSubarrays(int[] nums) {
        maxq.clear();
        minq.clear();
        arr = nums;
        long ans = 0;
        int n = arr.length;
        int r = 0;
        for (int i = 0; i < n; i++) { //枚举每一个开头位置，求一个答案，累加起来得到最终答案
            while (r < n && ok(r)) {
                push(r);
                r++;
            }
            //r来到第一个违规的位置
            //此时[i,r-1]范围上max-min <= 2
            //必须以i位置开头的子数组个数可以确定，r-i个
            ans += r - i;
            //System.out.printf("i=%d, r=%d\n", i, r);
            //i即将++，i位置的数要出窗口
            pop(i);
        }
        return ans;
    }

    //r位置的还没进窗口，如果进窗口的话，会不会违反max-min <= 2
    public static boolean ok(int r) {
        int max = maxq.isEmpty() ? arr[r] : Math.max(arr[r], arr[maxq.peekFirst()]);
        int min = minq.isEmpty() ? arr[r] : Math.min(arr[r], arr[minq.peekFirst()]);
        return max - min <= 2;
    }

    public static void push(int r) {
        while (!maxq.isEmpty() && arr[maxq.peekLast()] <= arr[r]) {
            maxq.pollLast();
        }
        maxq.addLast(r);
        while (!minq.isEmpty() && arr[minq.peekLast()] >= arr[r]) {
            minq.pollLast();
        }
        minq.addLast(r);
    }

    public static void pop(int l) {
        if (!maxq.isEmpty() && maxq.peekFirst() == l) {
            maxq.pollFirst();
        }
        if (!minq.isEmpty() && minq.peekFirst() == l) {
            minq.pollFirst();
        }
    }


    public static void main(String[] args) {
        int[] arr={5,4,2,4};
        System.out.println(continuousSubarrays(arr));
    }
}
