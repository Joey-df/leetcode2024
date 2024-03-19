package com.joey.top_hot;

/**
 * 152. 乘积最大子数组
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），
 * 并返回该子数组所对应的乘积。
 * <p>
 * 示例 1:
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * <p>
 * 示例 2:
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 */
public class Problem_0152_MaximumProductSubarray {

    public static int maxProduct(int[] nums) {
        assert (nums != null && nums.length > 0);
        int preMin = nums[0]; // i-1位置的最小累乘积
        int preMax = nums[0]; // i-1位置的最大累乘积
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int curMin = Math.min(nums[i], Math.min(nums[i] * preMin, nums[i] * preMax));
            int curMax = Math.max(nums[i], Math.max(nums[i] * preMin, nums[i] * preMax));
            ans = Math.max(ans, curMax);
            preMin = curMin;
            preMax = curMax;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{-2,0,-1}));
    }
}
