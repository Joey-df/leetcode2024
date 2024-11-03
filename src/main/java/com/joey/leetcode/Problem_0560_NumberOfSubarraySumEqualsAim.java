package com.joey.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pei.liu
 */
// 返回无序数组中累加和为给定值的子数组个数
// 测试链接 : https://leetcode.cn/problems/subarray-sum-equals-k/
public class Problem_0560_NumberOfSubarraySumEqualsAim {

    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int sum = 0;
        //key: 当前的累加和s
        //value: s出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); //0这个累加和出现了1次
        int ans = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i]; //当前包含i位置的累加和
            if (map.containsKey(sum - k)) { // sum=1000， k=100，找前缀和900出现的次数
                ans += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return ans;
    }

}
