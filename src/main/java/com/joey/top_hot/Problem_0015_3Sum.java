package com.joey.top_hot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
 * 使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * <p>
 * https://leetcode-cn.com/problems/3sum/
 */
public class Problem_0015_3Sum {

    //给定数组nums
    //返回 从start开始到N-1范围上 所有和为target的二元组
    public static List<List<Integer>> twoSum(int[] nums, int start, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        // [-1,0,1,2,-1,-4]，target=1
        // [-4,-1,-1,0,1,2]
        // 返回[-1,2],[0,1]
        Arrays.sort(nums);

        int l = start;
        int r = nums.length - 1;
        while (l < r) {
            if (nums[l] + nums[r] > target) {
                r--;
            } else if (nums[l] + nums[r] < target) {
                l++;
            } else { // ==
                if (l == start || nums[l] != nums[l - 1]) {
                    ArrayList<Integer> sub = new ArrayList<>();
                    sub.add(nums[l]);
                    sub.add(nums[r]);
                    ans.add(sub);
                }
                l++;
            }
        }
        return ans;
    }

    //返回nums[start,N-1]范围上累加和为target的三元组
    public static List<List<Integer>> threeSum(int[] nums, int start, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        Arrays.sort(nums);
        // [-4,-1,-1,0,1,2]
        for (int i = start; i <= nums.length - 3; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            List<List<Integer>> twoSum = twoSum(nums, i + 1, target - nums[i]);
            for (List<Integer> list : twoSum) {
                list.add(nums[i]);
                ans.add(list);
            }
        }
        return ans;
    }

    //返回nums[start,N-1]范围上累加和为target的4元组
    public static List<List<Integer>> fourSum(int[] nums, int start, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        Arrays.sort(nums);
        //[-4,-1,-1,0,1,2]
        for (int i = start; i <= nums.length - 4; i++) {
            if (i != start && nums[i] == nums[i - 1]) {
                continue;
            }
            List<List<Integer>> threeSum = threeSum(nums, i + 1, target - nums[i]);
            for (List<Integer> list : threeSum) {
                list.add(nums[i]);
                ans.add(list);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-4, -1, -1, 0, 1, 2};
        int target = 0;
        List<List<Integer>> lists = threeSum(nums, 0, target);
        System.out.println(lists);
    }

}
