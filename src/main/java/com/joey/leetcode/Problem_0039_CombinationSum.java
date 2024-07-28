package com.joey.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 39. 组合总和
 * 给定一个无重复元素的正整数数组 candidates 和一个正整数 target，
 * 找出 candidates 中所有可以使数字和为目标数 target 的唯一组合。
 * candidates 中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是唯一的。
 * 对于给定的输入，保证和为 target 的唯一组合数少于 150 个。
 * 
 * 示例 1：
 * 输入: candidates = [2,3,6,7], target = 7
 * 输出: [[7],[2,2,3]]
 * 示例 2：
 * 输入: candidates = [2,3,5], target = 8
 * 输出: [[2,2,2,2],[2,3,3],[3,5]]
 * 示例 3：
 * 输入: candidates = [2], target = 1
 * 输出: []
 * 示例 4：
 * 输入: candidates = [1], target = 1
 * 输出: [[1]]
 * 示例 5：
 * 输入: candidates = [1], target = 2
 * 输出: [[1,1]]
 */
//兑换零钱 类似
//https://leetcode.com/problems/combination-sum/discuss/16502/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)
public class Problem_0039_CombinationSum {

    public static List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        fun(ans, new ArrayList<>(), nums, target, 0);
        return ans;
    }

    //nums 固定输入数组 正数数组
    //[0,index-1]已经做好决定了，不用再操心了
    //[0,index-1]所做的决定，形成的路径存在path中
    //当前来到index位置做决定，[index...]自由选择
    //还有 rest 这么多数需要拼凑
    private static void fun(List<List<Integer>> ans, List<Integer> path, int[] nums, int rest, int index) {
        if (rest < 0) { // 因为nums是正数数组 凑不出负数
            return;
        } else if (rest == 0) { //还有0需要拼凑，说明之前所做的决定 path中是一个合法的决定 收集答案
            ans.add(new ArrayList<>(path));
        } else { //rest > 0 还有数需要拼凑
            // [index...]每个位置都试
            for (int i = index; i < nums.length; i++) {
                path.add(nums[i]);
                fun(ans, path, nums, rest - nums[i], i); // not i + 1 because we can reuse same elements
                path.remove(path.size() - 1);
            }
        }
    }

}
