package com.joey.leetcode;

import java.util.List;

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

    public int countPairs(List<Integer> nums, int target) {
        //TODO
        return 0;
    }

}
