package com.joey.top_hot;

/**
 * 334. 递增的三元子序列
 * 给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。
 *
 * 如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k，
 * 使得 nums[i] < nums[j] < nums[k] ，返回 true ；
 * 否则，返回 false 。
 *
 * 示例 1：
 * 输入：nums = [1,2,3,4,5]
 * 输出：true
 * 解释：任何 i < j < k 的三元组都满足题意
 *
 * 示例 2：
 * 输入：nums = [5,4,3,2,1]
 * 输出：false
 * 解释：不存在满足题意的三元组
 *
 * 示例 3：
 * 输入：nums = [2,1,5,0,4,6]
 * 输出：true
 * 解释：三元组 (3, 4, 5) 满足题意，因为 nums[3] == 0 < nums[4] == 4 < nums[5] == 6
 */
public class Problem_0334_IncreasingTripletSubsequence {

    public boolean increasingTriplet(int[] nums) {
        if (nums==null || nums.length < 3) {
            return false;
        }
        int N = nums.length;
        int[] dp = new int[N];
        int[] ends = new int[N];
        dp[0] = 1;
        ends[0] = nums[0];
        int right=0;
        for (int i = 0; i < N; i++) {
            int l = 0;
            int r = right;
            while(l<=r) {
                int m = l +((r-l)>>1);
                if (ends[m] >= nums[i]) {
                    r = m-1;
                } else {
                    l = m+1;
                }
            }
            right = Math.max(right, l);
            ends[l] = nums[i];
            dp[i] = l+1;
            if (dp[i] == 3) {
                return true;
            }
        }
        return false;
    }
}
