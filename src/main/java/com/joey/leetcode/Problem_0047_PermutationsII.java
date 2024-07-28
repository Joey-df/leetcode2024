package com.joey.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 47. 数组的全排列（有重复值）
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 * <p>
 * 示例 1：
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 * [1,2,1],
 * [2,1,1]]
 * 示例 2：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */
//https://leetcode.com/problems/permutations-ii/
//求给定数组 的全排列
//包含重复字符
public class Problem_0047_PermutationsII {

    public List<List<Integer>> permuteUnique(int[] arr) {
        List<List<Integer>> ans = new ArrayList<>();
        if (arr == null || arr.length == 0) return ans;
        fun(arr, 0, ans);
        return ans;
    }

    public void fun(int[] arr, int index, List<List<Integer>> ans) {
        if (index == arr.length) {
            //collect ans
            ArrayList<Integer> cur = new ArrayList<>();
            for (int n : arr) cur.add(n);
            ans.add(cur);
            return;
        }
        //主逻辑
        //visited只对当前index位置私有，表示哪些字符有机会来到index位置
        Set<Integer> visited = new HashSet<>();
        for (int i = index; i < arr.length; i++) {
            if (!visited.contains(arr[i])) {
                visited.add(arr[i]);
                swap(arr, i, index);
                fun(arr, index + 1, ans);
                swap(arr, i, index); //clear
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
