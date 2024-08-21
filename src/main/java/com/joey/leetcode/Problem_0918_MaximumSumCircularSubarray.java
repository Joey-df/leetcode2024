package com.joey.leetcode;

/**
 * @author pei.liu
 */
//918. 环形子数组的最大和
//给定一个长度为 n 的环形整数数组 nums ，返回 nums 的非空 子数组 的最大可能和 。
//环形数组 意味着数组的末端将会与开头相连呈环状。形式上， nums[i] 的下一个元素是 nums[(i + 1) % n] ， nums[i] 的前一个元素是 nums[(i - 1 + n) % n] 。
//子数组 最多只能包含固定缓冲区 nums 中的每个元素一次。形式上，对于子数组 nums[i], nums[i + 1], ..., nums[j] ，不存在 i <= k1, k2 <= j 其中 k1 % n == k2 % n 。
//
//示例 1：
//输入：nums = [1,-2,3,-2]
//输出：3
//解释：从子数组 [3] 得到最大和 3
//示例 2：
//输入：nums = [5,-3,5]
//输出：10
//解释：从子数组 [5,5] 得到最大和 5 + 5 = 10
//示例 3：
//输入：nums = [3,-2,2,-3]
//输出：3
//解释：从子数组 [3] 和 [3,-2,2] 都可以得到最大和 3
//
//提示：
//n == nums.length
//1 <= n <= 3 * 10^4
//-3 * 10^4 <= nums[i] <= 3 * 10^4
public class Problem_0918_MaximumSumCircularSubarray {

    //分析
    //环形数组的最大累加和
    //答案的可能性有两种情况
    //1.答案来着数组中间的一段：如 [-1,2,3,4,-5],这种情况就是非环形数组的最大累加和问题
    //2.答案来自两端，即被截断，如 [1,3,-7,-6,3,5],这种情况的答案就是数组整体的累加和-数组的最小累加和
    //特例：数组全为负数的情况，此种情况，数组的最小累加和=数组的累加和，答案为整个数组上的最大累加和,如：[-1,-2,-3]
    public static int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;
        int all = nums[0];
        int maxSum = nums[0]; //整个数组上的最大累加和
        int premax = nums[0];
        int minSum = nums[0];
        int premin = nums[0]; //整个数组上的最小累加和
        for (int i = 1; i < n; i++) {
            all += nums[i];
            int curMaxSum = Math.max(nums[i], nums[i] + premax);
            maxSum = Math.max(maxSum, curMaxSum);
            premax = curMaxSum;
            int curMinSum = Math.min(nums[i], nums[i] + premin);
            minSum = Math.min(minSum, curMinSum);
            premin = curMinSum;
        }
        // 1) maxsum
        // 2) all - minsum
        // 两种情况取最大
        return all == minSum ? maxSum : Math.max(maxSum, all - minSum);
    }

    public static void main(String[] args) {
        int[] arr= {-1,2,3,4,-5};
        System.out.println(maxSubarraySumCircular(arr));
    }

}
