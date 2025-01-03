package com.joey.leetcode;

/**
 * @author pei.liu
 */
//1150. 检查一个数是否在数组中占绝大多数
//给出一个按 非递减 顺序排列的数组 nums，和一个目标数值 target。
//假如数组 nums 中绝大多数元素的数值都等于 target，则返回 True，否则请返回 False。
//所谓占绝大多数，是指在长度为 N 的数组中出现必须 超过 N/2 次。
//
//示例 1：
//输入：nums = [2,4,5,5,5,5,5,6,6], target = 5
//输出：true
//解释：
//数字 5 出现了 5 次，而数组的长度为 9。
//所以，5 在数组中占绝大多数，因为 5 次 > 9/2。
//示例 2：
//输入：nums = [10,100,101,101], target = 101
//输出：false
//解释：
//数字 101 出现了 2 次，而数组的长度是 4。
//所以，101 不是 数组占绝大多数的元素，因为 2 次 = 4/2。
//
//提示：
//1 <= nums.length <= 1000
//1 <= nums[i] <= 10^9
//1 <= target <= 10^9
public class Problem_1150_IsMajorityElement {

    //假如 x 超过了一半，n/2 位置的元素一定是 x
    public static boolean isMajorityElement(int[] nums, int target) {
        int n = nums.length;
        int left = left(nums, nums[n >> 1]);
        int right = left(nums, nums[n >> 1] + 1);
        return target == nums[n >> 1] && right - left > n / 2;
    }

    public static int left(int[] nums, int x) {
        int n = nums.length;
        int l = 0;
        int r = n - 1;
        int hit = n;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] >= x) {
                hit = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return hit;
    }

    public static void main(String[] args) {
        int[] nums = {3};
        int x = 4;
        System.out.println(isMajorityElement(nums, x));
    }

}
