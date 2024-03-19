package com.joey.top_hot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 448. 找到所有数组中消失的数字
 * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。
 * 请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
 *
 * 示例 1：
 * 输入：nums = [4,3,2,7,8,2,3,1]
 * 输出：[5,6]
 *
 * 示例 2：
 * 输入：nums = [1,1]
 * 输出：[2]
 */
public class Problem_0448_FindAllNumbersDisappearedInAnArray {

    //平凡解
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        if (nums==null || nums.length==0) {
            return ans;
        }
        int N = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int num: nums) {
            set.add(num);
        }
        for (int i=1;i<=N;i++) {
            if (!set.contains(i)) {
                ans.add(i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4,3,2,7,8,2,3,1};
        System.out.println(findDisappearedNumbers(nums));
    }

    //最优解
    //下标循环怼
    //额外空间复杂度O(1)
    public static List<Integer> findDisappearedNumbers2(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        int N = nums.length;
        for (int i = 0; i < N; i++) {
            walk(nums, i);
        }
        for (int i = 0; i < N; i++) {
            if (nums[i] != i + 1) {
                ans.add(i + 1);
            }
        }
        return ans;
    }

    public static void walk(int[] nums, int i) {
        while (nums[i] != i + 1) { // 不断从i发货
            int nexti = nums[i] - 1;
            if (nums[nexti] == nexti + 1) {
                break;
            }
            swap(nums, i, nexti);
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
