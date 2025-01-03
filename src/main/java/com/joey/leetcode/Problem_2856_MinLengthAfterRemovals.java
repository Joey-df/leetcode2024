package com.joey.leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pei.liu
 */
//2856. 删除数对后的最小数组长度
//给你一个下标从 0 开始的 非递减 整数数组 nums 。
//你可以执行以下操作任意次：
//选择 两个 下标 i 和 j ，满足 nums[i] < nums[j] 。
//将 nums 中下标在 i 和 j 处的元素删除。剩余元素按照原来的顺序组成新的数组，下标也重新从 0 开始编号。
//请你返回一个整数，表示执行以上操作任意次后（可以执行 0 次），nums 数组的 最小 数组长度。
public class Problem_2856_MinLengthAfterRemovals {

    //统计数组中元素的词频
    //如果x的词频最大，次数为maxCnt
    //如果maxCnt > n/2，则剩下2*maxCnt-n个，如：1 2 3 4 4 4 4
    //否则maxCnt <= n/2,则剩下元素个数为 n % 2， 如 1 2 3 4 4 4，1 2 3 4 5 5 5
    public int minLengthAfterRemovals(List<Integer> nums) {
        int n = nums.size();
        HashMap<Integer, Integer> cnts = new HashMap<>();
        int maxCnt = 0;
        for (int c : nums) {
            cnts.put(c, cnts.getOrDefault(c, 0) + 1);
            maxCnt = Math.max(maxCnt, cnts.get(c));
        }
        if (maxCnt > n / 2) return maxCnt * 2 - n;
        return n % 2;
    }


    //因为数组已经有序
    //假设x出现次数超过数组长度的一半，那nums[n/2]位置一定是x
    //可以二分找边界
    //只找右边界做减法
    public int minLengthAfterRemovals2(List<Integer> nums) {
        int n = nums.size();
        int x = nums.get(n >> 1);
        int midNumCnt = right(nums, x) - right(nums, x - 1);
        if (midNumCnt > n / 2) return 2 * midNumCnt - n;
        return n % 2;
    }

    //只找左边界做减法
    public int minLengthAfterRemovals3(List<Integer> nums) {
        int n = nums.size();
        int x = nums.get(n >> 1);
        int midNumCnt = left(nums, x + 1) - left(nums, x);
        if (midNumCnt > n / 2) return 2 * midNumCnt - n;
        return n % 2;
    }

    //找左右边界做减法
    public int minLengthAfterRemovals4(List<Integer> nums) {
        int n = nums.size();
        int x = nums.get(n >> 1);
        int left = left(nums, x);
        int right = right(nums, x);
        int midNumCnt = left > right ? 0 : right - left + 1;
        if (midNumCnt > n / 2) return 2 * midNumCnt - n;
        return n % 2;
    }


    public int right(List<Integer> nums, int aim) {
        int l = 0;
        int r = nums.size() - 1;
        int ans = -1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums.get(m) <= aim) {
                ans = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }

    public int left(List<Integer> nums, int aim) {
        int l = 0;
        int r = nums.size() - 1;
        int ans = nums.size();
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums.get(m) >= aim) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }
}
