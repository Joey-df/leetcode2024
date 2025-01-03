package com.joey.leetcode;

import java.util.*;

/**
 * @author pei.liu
 */
//3113. 边界元素是最大值的子数组数目
//给你一个 正 整数数组 nums 。
//请你求出 nums 中有多少个子数组，满足子数组中 第一个 和 最后一个 元素都是这个子数组中的 最大 值。
//提示：
//1 <= nums.length <= 10^5
//1 <= nums[i] <= 10^9
public class Problem_3113_NumberOfSubarrays {

    public static long numberOfSubarrays(int[] nums) {
        int n = nums.length;
        // Monotonic stack to calculate the `f` array
        int[] f = new int[n];
        Arrays.fill(f, -1);
        Stack<Integer> stk = new Stack<>(); //大 -> 小
        for (int i = 0; i < n; i++) {
            while (!stk.isEmpty() && nums[stk.peek()] <= nums[i]) {
                stk.pop();
            }
            if (!stk.isEmpty()) { //元素进入单调栈时求答案
                f[i] = stk.peek();
            }
            stk.push(i);
        }

        // Store the indices of each element in the map
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        long ans = 0;
        // 分别考虑每种元素
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            List<Integer> indices = entry.getValue();
            // indices[i]：子数组的右端点
            // indices[j]：子数组的左端点最左能到哪里
            for (int i = 0, j = 0; i < indices.size(); i++) {
                // 子数组里不能包含下标 f[indices[i]]
                while (j < i && indices.get(j) <= f[indices.get(i)]) {
                    j++;
                }
                ans += i - j + 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {3, 4, 3, 3};
        System.out.println(numberOfSubarrays(nums));
    }

}
