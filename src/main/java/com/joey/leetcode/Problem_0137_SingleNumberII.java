package com.joey.leetcode;

/**
 * 137. 只出现一次的数字 II
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。
 * 请你找出并返回那个只出现了一次的元素。
 * <p>
 * 示例 1：
 * 输入：nums = [2,2,3,2]
 * 输出：3
 * <p>
 * 示例 2：
 * 输入：nums = [0,1,0,1,0,1,99]
 * 输出：99
 */
public class Problem_0137_SingleNumberII {
    public int singleNumber(int[] nums) {
        return func(nums, 3, 1);
    }

    //数组中只有一种元素出现n次，其他元素都出现k次，k>n
    //找出出现n次的数
    public int func(int[] nums, int k, int n) {
        int[] bits = new int[32];
        for (int num : nums) {
            for (int i = 31; i >= 0; i--) {
                bits[i] += ((num >>> i) & 1);
            }
        }
        int ans = 0;
        for (int i = 31; i >= 0; i--) {
            ans |= (((bits[i] % k) / n) << i);
        }
        return ans;
    }

}
