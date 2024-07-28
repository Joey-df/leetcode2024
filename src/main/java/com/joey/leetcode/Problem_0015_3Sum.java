package com.joey.leetcode;

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

    public List<List<Integer>> threeSum(int[] arr) {
        List<List<Integer>> ans = new ArrayList<>();
        if (arr == null || arr.length < 3) return ans;
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 2; i++) {
            if (i > 0 && arr[i] == arr[i - 1]) continue;
            List<List<Integer>> twoSum = twoSum(arr, i + 1, arr.length - 1, -arr[i]);
            for (List<Integer> cur : twoSum) {
                cur.add(0, arr[i]);
                ans.add(cur);
            }
        }
        return ans;
    }

    //arr已有序
    //返回arr[l,r]范围上累加和等于target的所有不重复的二元组
    private List<List<Integer>> twoSum(int[] arr, int l, int r, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (arr == null || arr.length < 2 || l >= r) return ans;
        int start = l;
        while (l < r) {
            if (arr[l] + arr[r] < target) l++;
            else if (arr[l] + arr[r] > target) r--;
            else {
                if (l == start || arr[l] != arr[l - 1]) {
                    List<Integer> cur = new ArrayList<>();
                    cur.add(arr[l]);
                    cur.add(arr[r]);
                    ans.add(cur);
                }
                l++;
                r--;
            }
        }
        return ans;
    }


}
