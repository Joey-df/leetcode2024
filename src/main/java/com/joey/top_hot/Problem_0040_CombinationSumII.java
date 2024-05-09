package com.joey.top_hot;

import java.util.*;

/**
 * 40. 组合总和 II
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用一次。
 * 注意：解集不能包含重复的组合。
 * <p>
 * 示例 1:
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 输出:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 * 示例 2:
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 输出:
 * [
 * [1,2,2],
 * [5]
 * ]
 */
public class Problem_0040_CombinationSumII {

    //Each number in candidates may only be used once in the combination.
    public static List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums); //必须
        List<Integer> path = new ArrayList<>();
        process(nums, 0, target, path, ans);
        return ans;
    }

    //nums 有序正数数组 固定参数
    //[0,index-1]已经做好决定了，所做决定形成的路径，存在path中
    //[index...]自由选择，凑出rest
    //ans 收集答案使用
    private static void process(int[] nums, int index, int rest, List<Integer> path, List<List<Integer>> ans) {
        if (rest < 0) return; //如果题目要求nums为正数数组，必须加上这句
        if (rest == 0) {
            ans.add(new ArrayList<>(path));
        } else {
            //每个index位置一个set，用于在当前位置去重（表示有哪些不同的元素有机会被安排在index位置）
            Set<Integer> set = new HashSet<>();
            for (int i = index; i < nums.length; i++) {
                if (!set.contains(nums[i])) {
                    set.add(nums[i]);
                    path.add(nums[i]);
                    process(nums, i + 1, rest - nums[i], path, ans);
                    path.remove(path.size() - 1);
                }
            }
        }
    }

}
