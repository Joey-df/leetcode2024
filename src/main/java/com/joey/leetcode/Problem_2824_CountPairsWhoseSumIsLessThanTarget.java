package com.joey.leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author pei.liu
 */
//2824. 统计和小于目标的下标对数目
//给你一个下标从 0 开始长度为 n 的整数数组 nums 和一个整数 target ，
//请你返回满足 0 <= i < j < n 且 nums[i] + nums[j] < target 的下标对 (i, j) 的数目。
//示例 1：
//输入：nums = [-1,1,2,3,1], target = 2
//输出：3
//解释：总共有 3 个下标对满足题目描述：
//- (0, 1) ，0 < 1 且 nums[0] + nums[1] = 0 < target
//- (0, 2) ，0 < 2 且 nums[0] + nums[2] = 1 < target
//- (0, 4) ，0 < 4 且 nums[0] + nums[4] = 0 < target
//注意 (0, 3) 不计入答案因为 nums[0] + nums[3] 不是严格小于 target
public class Problem_2824_CountPairsWhoseSumIsLessThanTarget {

    //求满足0 <= i < j < n 且 nums[i] + nums[j] < target 的下标对 (i, j) 的数目。
    //转化为：在任意一个位置i求前面多少个位置的数？满足 nums[j] < target - nums[i]，求数对数量所以和位置无关，可以排序
    //转化为：二分查找 小于m最右的位置，假设该位置为p，则位置i的答案为：p+1，即[0，p]这些位置和i位置结合都是满足条件的
    public static int countPairs(List<Integer> nums, int target) {
        int[] arr = nums.stream().mapToInt(Integer::intValue).toArray();
        Arrays.sort(arr);
        // -7 -6 -2 -1 2 3 5
        int n = arr.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int num = target - arr[i];
            //在[0,i-1]范围上找 < num最右的位置
            int p = findRight(arr, 0, i - 1, num);
            ans += p+1;
        }
        return ans;
    }

    public static int findRight(int[] arr, int l, int r, int target) {
        int ans = -1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (arr[m] < target) { //求小于这里就是arr[m] < target，如果求<=左右的位置就改为 arr[m] <= target
                ans = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }


}
