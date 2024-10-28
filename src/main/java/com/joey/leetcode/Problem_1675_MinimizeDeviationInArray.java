package com.joey.leetcode;

import java.util.TreeSet;

/**
 * @author pei.liu
 */

//1675. 数组的最小偏移量
//给你一个由 n 个正整数组成的数组 nums 。
//你可以对数组的任意元素执行任意次数的两类操作：
//如果元素是 偶数 ，除以 2
//例如，如果数组是 [1,2,3,4] ，那么你可以对最后一个元素执行此操作，使其变成 [1,2,3,2]
//如果元素是 奇数 ，乘上 2
//例如，如果数组是 [1,2,3,4] ，那么你可以对第一个元素执行此操作，使其变成 [2,2,3,4]
//数组的 偏移量 是数组中任意两个元素之间的 最大差值 。
//返回数组在执行某些操作之后可以拥有的 最小偏移量 。
public class Problem_1675_MinimizeDeviationInArray {

    //分析：
    //如果是奇数，乘以2，再除以2，又变回自己，来回震荡，如 3 6 3 6
    //如果是偶数，经过若干次的除2操作之后，趋于稳定状态来回震荡，如100，最后会变成 25 50 25 50
    //题目是求最大差值尽量小
    //so，用有序表存放元素，每次都能得到最大值和最小值，两者做差，没轮更新该指标
    //让所有奇数统一得到一次变偶数的机会
    //然后每轮用最大值-最小值
    //如果最大值是偶数，将偶数/2再放进去
    //如果最大值变为奇数，停止
    public static int minimumDeviation(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>((a, b) -> a - b);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if ((nums[i] & 1) == 1) {
                set.add(nums[i] << 1);
            } else {
                set.add(nums[i]);
            }
        }
        int ans = Integer.MAX_VALUE;
        int max = set.last();
        while ((max & 1) == 0) {
            int first = set.first();
            ans = Math.min(ans, max - first);
            if ((max & 1) == 0) {
                int curr = set.pollLast();
                set.add(curr / 2);
            }
            max = set.last();
        }
        ans = Math.min(ans, set.last() - set.first());
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(minimumDeviation(new int[]{1, 2, 3, 4}));
    }

}
