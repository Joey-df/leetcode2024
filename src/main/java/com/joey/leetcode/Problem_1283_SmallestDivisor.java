package com.joey.leetcode;

/**
 * @author pei.liu
 */
//1283. 使结果不超过阈值的最小除数
//给你一个整数数组 nums 和一个正整数 threshold ，你需要选择一个正整数作为除数，然后将数组里每个数都除以它，并对除法结果求和。
//请你找出能够使上述结果小于等于阈值 threshold 的除数中 最小 的那个。
//每个数除以除数后都向上取整，比方说 7/3 = 3 ， 10/2 = 5 。
//题目保证一定有解。
//
//示例 1
//输入：nums = [1,2,5,9], threshold = 6
//输出：5
//解释：如果除数为 1 ，我们可以得到和为 17 （1+2+5+9）。
//如果除数为 4 ，我们可以得到和为 7 (1+1+2+3) 。如果除数为 5 ，和为 5 (1+1+1+2)。
//示例 2：
//输入：nums = [2,3,5,7,11], threshold = 11
//输出：3
//示例 3：
//输入：nums = [19], threshold = 5
//输出：4
//
//提示：
//1 <= nums.length <= 5 * 10^4
//1 <= nums[i] <= 10^6
//nums.length <= threshold <= 10^6
public class Problem_1283_SmallestDivisor {

    //除数越大，结果越小
    //除数越小，结果越大
    //对于除数m，如果得到的结果ret >= threshold，说明小于m的都满足
    //除数的范围：
    public static int smallestDivisor(int[] nums, int threshold) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        int l = 1;
        int r = max;
        int ans = max;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (f(nums, m) <= threshold) {
                ans = m; //说明大于m的都满足，m越大，得到的结果越小
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    //当除数为p时，返回得到的结果
    //每次除完向上取整
    public static int f(int[] nums, int p) {
        int ans = 0;
        for (int num : nums) {
            ans += (num + p - 1) / p;
        }
        return ans;
    }

}
