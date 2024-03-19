package com.joey.top_hot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 46. 全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 示例 2：
 * <p>
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * 示例 3：
 * <p>
 * 输入：nums = [1]
 * 输出：[[1]]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 */
public class Problem_0046_Permutations {

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        fun(nums, 0, ans);
        return ans;
    }

    //递归含义
    //arr固定
    //当前来到了index位置，做决定
    //[0...index-1]已经搞定了，不用操心了！
    //ans收集答案
    private static void fun(int[] arr, int index, List<List<Integer>> ans) {
        if (index == arr.length) {
            //表示[0...arr.length-1]已经做好决定了
            //此时arr的样子就是一种答案
            ArrayList<Integer> sub = new ArrayList<>();
            for (int i : arr) {
                sub.add(i);
            }
            ans.add(sub);
        } else {
            //index还有字符
            //index后面的字符均有机会来到index位置
            Set<Integer> visited = new HashSet<>();
            for (int i = index; i < arr.length; i++) {
                if (!visited.contains(arr[i])) {
                    visited.add(arr[i]);
                    swap(arr, i, index);
                    fun(arr, index + 1, ans);
                    swap(arr, i, index); // clear
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }


    public static void main(String[] args) {
        System.out.println(permute(new int[]{1, 2, 2}));
    }
}
