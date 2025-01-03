package com.joey.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author pei.liu
 */
//2389. 和有限的最长子序列
//给你一个长度为 n 的整数数组 nums ，和一个长度为 m 的整数数组 queries 。
//返回一个长度为 m 的数组 answer ，其中 answer[i] 是 nums 中 元素之和小于等于 queries[i] 的 子序列 的 最大 长度  。
//子序列 是由一个数组删除某些元素（也可以不删除）但不改变剩余元素顺序得到的一个数组。
public class Problem_2389_AnswerQueries {

    public static int[] answerQueries(int[] nums, int[] queries) {
        int n = nums.length;
        Arrays.sort(nums);
        int[] preSum = new int[n];
        preSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }
        int len = queries.length;
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            int l = 0;
            int r = n - 1;
            int curr = -1;
            while (l <= r) {
                int m = l + (r - l) / 2;
                if (preSum[m] <= queries[i]) { //找<=queries[i]的最右位置
                    curr = m;
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            ans[i] = curr == -1 ? 0 : curr + 1;
        }
        return ans;
    }

}
