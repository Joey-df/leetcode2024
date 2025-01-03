package com.joey.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums和一个整数目标值 target，
 * 请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 * 示例 1：
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 */
public class Problem_0001_TwoSum {

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> entry = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (entry.containsKey(target - nums[i]))
                return new int[]{entry.get(target - nums[i]), i};
            entry.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    //杀鸡用牛刀搞一把，练一把二分查找
    static class Info {
        int v;
        int i;

        public Info(int v, int i) {
            this.v = v;
            this.i = i;
        }
    }

    public static int[] twoSum2(int[] nums, int target) {
        int n = nums.length;
        Info[] infos = new Info[n];
        for (int i = 0; i < n; i++) {
            infos[i] = new Info(nums[i], i);
        }
        Arrays.sort(infos, (a, b) -> a.v - b.v);

        for (int i = 1; i < n; i++) {
            //两数之和，目标只可能在左边
            int p = find(infos, 0, i - 1, target - infos[i].v);
            if (p != -1) {
                return new int[]{infos[i].i, p};
            }
        }
        return new int[]{-1, -1};
    }

    public static int find(Info[] infos, int l, int r, int target) {
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (infos[m].v == target) {
                return infos[m].i;
            } else if (infos[m].v < target) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return -1;
    }

}
