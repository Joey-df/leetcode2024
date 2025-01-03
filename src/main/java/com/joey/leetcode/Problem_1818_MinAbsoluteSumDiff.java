package com.joey.leetcode;

import java.util.Arrays;

/**
 * @author pei.liu
 */
//1818. 绝对差值和
//给你两个正整数数组 nums1 和 nums2 ，数组的长度都是 n 。
//数组 nums1 和 nums2 的 绝对差值和 定义为所有 |nums1[i] - nums2[i]|（0 <= i < n）的 总和（下标从 0 开始）。
//你可以选用 nums1 中的 任意一个 元素来替换 nums1 中的 至多 一个元素，以 最小化 绝对差值和。
//在替换数组 nums1 中最多一个元素 之后 ，返回最小绝对差值和。因为答案可能很大，所以需要对 10^9 + 7 取余 后返回。
//|x| 定义为：
//如果 x >= 0 ，值为 x ，或者
//如果 x <= 0 ，值为 -x
//
//示例 1：
//输入：nums1 = [1,7,5], nums2 = [2,3,5]
//输出：3
//解释：有两种可能的最优方案：
//- 将第二个元素替换为第一个元素：[1,7,5] => [1,1,5] ，或者
//- 将第二个元素替换为第三个元素：[1,7,5] => [1,5,5]
//两种方案的绝对差值和都是 |1-2| + (|1-3| 或者 |5-3|) + |5-5| = 3
//提示：
//n == nums1.length
//n == nums2.length
//1 <= n <= 10^5
//1 <= nums1[i], nums2[i] <= 10^5
public class Problem_1818_MinAbsoluteSumDiff {

    public static int MOD = 1000000007;

    //答案的指标：两个数组对应位置 差值绝对值 累加和
    //只能替换 0 或 1 次
    //替换哪个位置最优？差值绝对值最大的那个位置，加入是 i
    //找谁替换？nums1 中离 nums2[i] 最近的那个
    //=>先对nums1排序，在nums1中找 >= nums2[i]最左的 x， <=nums2[i]最右的 y，x和y谁和nums2[i]绝对值差谁最小选谁
    public static int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int sum = 0;
        int[] arr = Arrays.copyOfRange(nums1, 0, n);
        Arrays.sort(arr);
        int max = 0; //最大浮动
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            sum = (sum % MOD + diff) % MOD;
            int left = left(arr, nums2[i]);
            int right = right(arr, nums2[i]);
            if (left < n) {
                max = Math.max(max, diff - Math.abs(arr[left] - nums2[i]));
            }
            if (right > -1) {
                max = Math.max(max, diff - Math.abs(arr[right] - nums2[i]));
            }
        }
        return (sum - max + MOD) % MOD;
    }

    public static int left(int[] nums, int target) {
        int n = nums.length;
        int l = 0;
        int r = n - 1;
        int ans = n;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] >= target) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    public static int right(int[] nums, int target) {
        int n = nums.length;
        int l = 0;
        int r = n - 1;
        int ans = -1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] <= target) {
                ans = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 28, 21};
        int[] nums2 = {9, 21, 20};
        System.out.println(minAbsoluteSumDiff(nums1, nums2));
    }

}
