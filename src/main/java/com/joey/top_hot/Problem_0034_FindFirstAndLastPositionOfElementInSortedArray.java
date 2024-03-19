package com.joey.top_hot;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * <p>
 * 示例 1：
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * <p>
 * 示例 2：
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * <p>
 * 示例 3：
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 */
public class Problem_0034_FindFirstAndLastPositionOfElementInSortedArray {

    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        return new int[]{
                findLeft(nums, target),
                findRight(nums, target)
        };
    }


    public int findLeft(int[] arr, int target) {
        int l = 0, r = arr.length - 1, m;
        int ans = -1;
        while (l <= r) {
            m = l + ((r - l) >> 1);
            if (arr[m] > target) r = m - 1;
            else if (arr[m] < target) l = m + 1;
            else {
                ans = m;
                r = m - 1;
            }
        }
        return ans;
    }

    public int findRight(int[] arr, int target) {
        int l = 0, r = arr.length - 1, m, ans = -1;
        while (l <= r) {
            m = l + ((r - l) >> 1);
            if (arr[m] > target) r = m - 1;
            else if (arr[m] < target) l = m + 1;
            else {
                ans = m;
                l = m + 1;
            }
        }
        return ans;
    }
}
