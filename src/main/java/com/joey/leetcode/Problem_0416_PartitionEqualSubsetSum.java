package com.joey.leetcode;

/**
 * 416. 分割等和子集
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * <p>
 * 示例 1：
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：数组不能分割成两个元素和相等的子集。
 */
//01背包问题
//https://leetcode.com/problems/partition-equal-subset-sum/discuss/90592/01-knapsack-detailed-explanation
public class Problem_0416_PartitionEqualSubsetSum {

    //arr中包含正数和0时有效
    public boolean canPartition(int[] arr) {
        if (arr == null || arr.length < 2) {
            return false;
        }
        int sum = 0;
        for (int cur : arr) {
            sum += cur;
        }
        //如果累加和sum是奇数，直接false
        if ((sum & 1) == 1) {
            return false;
        }
        //sum 变成 sum/2
        sum = sum >> 1;
        //求arr中是否存在累加和为sum的子集
        int n = arr.length;
        //dp[i][j]: arr前缀长度为i的所有子集，累加和等于j有多少中方法
        int[][] dp = new int[n + 1][sum + 1];

        dp[0][0] = 1; //空集累加出0，有1种方法
        //dp[0][1...] = 0 //前缀长度0，累加出1，2，3...，0种方法
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                int p1 = dp[i - 1][j]; //arr[i-1]不参与(前缀长度为i，最后一个元素是arr[i-1])
                int p2 = 0; //arr[i-1]参与
                if (j - arr[i - 1] >= 0) { //p2是有条件的，必须满足j >= arr[i-1]才有p2
                    p2 = dp[i - 1][j - arr[i - 1]];
                }
                dp[i][j] = p1 + p2;
            }
            if (dp[i][sum] > 0) {
                return true;
            }
        }
        return false;
    }

}
