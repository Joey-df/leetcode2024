package com.joey.top_hot;

//子数组最大累加和
public class Problem_0053_MaximumSubarray {

    public static int maxSubArray(int[] nums) {
        assert (nums != null && nums.length > 0);
        // 思维传统：
        // 子数组->以每个位置结尾求一个答案，求整体max
        // 动态规划思维
        int ans = nums[0];
        int pre = nums[0]; //i-1位置结尾的子数组的最大累加和
        for (int i = 1; i < nums.length; i++) {
            int p1 = nums[i];//只包含i位置的数
            int p2 = nums[i] + pre;
            int cur = Math.max(p1, p2);
            ans = Math.max(ans, cur);
            pre = cur;
        }
        return ans;
    }

    public static int fun(int[] nums) {
        assert (nums != null && nums.length > 0);
        int ans = nums[0];
        int pre = nums[0];//必须以i-1位置结尾的子数组最大累加和
        for (int i = 1; i < nums.length; i++) {
            int cur = nums[i] + Math.max(0, pre); //子数组必须以当前i位置结尾的最大累加和
            ans = Math.max(ans, cur);
            pre = cur;
        }
        return ans;
    }

}
