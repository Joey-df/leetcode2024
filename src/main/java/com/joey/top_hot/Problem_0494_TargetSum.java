package com.joey.top_hot;

import java.util.HashMap;
import java.util.Map;

//494. 目标和
//给你一个整数数组 nums 和一个整数 target 。
//向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
//例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
//返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
//示例 1：
//输入：nums = [1,1,1,1,1], target = 3
//输出：5
//解释：一共有 5 种方法让最终目标和为 3 。
//-1 + 1 + 1 + 1 + 1 = 3
//+1 - 1 + 1 + 1 + 1 = 3
//+1 + 1 - 1 + 1 + 1 = 3
//+1 + 1 + 1 - 1 + 1 = 3
//+1 + 1 + 1 + 1 - 1 = 3
//示例 2：
//输入：nums = [1], target = 1
//输出：1
public class Problem_0494_TargetSum {

    //暴力尝试
    public int findTargetSumWays(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return target == 0 ? 1 : 0;
        }
        return fun(nums, 0, target);
    }

    //nums中的元素均非负
    //rest：可正 可负 可0
    //递归含义：
    //[index...]自由选择，搞定 rest 的方法数是多少？
    public static int fun(int[] nums, int index, int rest) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (index == nums.length) { //没有数可以选择了
            //已经没有数可以选择了，如果target==0有1种方案，否则没有方案。
            return rest == 0 ? 1 : 0;
        }
        //index < nums.length
        //index前加'-' 的方法数
        int p1 = fun(nums, index + 1, rest + nums[index]);
        //index前加'+' 的方法数
        int p2 = fun(nums, index + 1, rest - nums[index]);
        return p1 + p2;
    }


    //记忆化搜索版本
    public int findTargetSumWays2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return target == 0 ? 1 : 0;
        }
        Map<String, Integer> cache = new HashMap<>();
        return dpCache(nums, 0, target, cache);
    }

    //含义：[index...]自由选择，搞定 rest 的方法数是多少？
    //记忆化搜索（可变参数就是index和rest）
    public int dpCache(int[] nums, int index, int rest, Map<String, Integer> cache) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //如果缓存命中直接返回
        if (cache.containsKey(rest + "_" + index)) {
            return cache.get(rest + "_" + index);
        }
        if (index == nums.length) { //没有数可以选择了
            //已经没有数可以选择了，如果 rest==0 有1种方案，否则没有方案。
            int ans = rest == 0 ? 1 : 0;
            cache.put(rest + "_" + index, ans); //返回之前加缓存
            return ans;
        }
        //index < nums.length
        //index前加'-' 的方法数
        int p1 = dpCache(nums, index + 1, rest + nums[index], cache);
        //index前加'+' 的方法数
        int p2 = dpCache(nums, index + 1, rest - nums[index], cache);
        //返回之前加缓存
        cache.put(rest + "_" + index, p1 + p2);
        return p1 + p2;
    }

}
