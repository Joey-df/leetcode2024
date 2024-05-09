package com.joey.top_hot;

/**
 * 581. 最短无序连续子数组
 * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 * <p>
 * 请你找出符合题意的 最短 子数组，并输出它的长度。
 * <p>
 * 示例 1：
 * 输入：nums = [2,6,4,8,10,9,15]
 * 输出：5
 * 解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,3,4]
 * 输出：0
 * <p>
 * 示例 3：
 * 输入：nums = [1]
 * 输出：0
 */
public class Problem_0581_ShortestUnsortedContinuousSubarray {

    //一趟找到右边界
    //一趟找到左边界
    public static int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        // max > 当前数，认为不达标
        // 从左往右遍历，记录最右不达标的位置
        int right = -1;
        int max = nums[0];
        for (int i = 1; i < n; i++) {
            if (max > nums[i]) { //从左往右，出现下降趋势
                right = i;
            }
            max = Math.max(max, nums[i]);
        }
        int min = nums[n-1];
        // min < 当前数，认为不达标
        // 从右往左遍历，记录最左不达标的位置
        int left = n;
        for (int i = n - 2; i >= 0; i--) {
            if (min < nums[i]) { //从右往左，出现上升趋势
                left = i;
            }
            min = Math.min(min, nums[i]);
        }
        return Math.max(0, right - left + 1);
    }

}
