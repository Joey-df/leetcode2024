package com.joey.top_hot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//18. 四数之和
//给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。
//请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] ：
//0 <= a, b, c, d < n
//a、b、c 和 d 互不相同
//nums[a] + nums[b] + nums[c] + nums[d] == target
//你可以按 任意顺序 返回答案 。
//示例 1：
//输入：nums = [1,0,-1,0,-2,2], target = 0
//输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
//示例 2：
//输入：nums = [2,2,2,2,2], target = 8
//输出：[[2,2,2,2]]
//提示：
//1 <= nums.length <= 200
//-10^9 <= nums[i] <= 10^9
//-10^9 <= target <= 10^9
public class Problem_0018_4Sum {

    public static List<List<Integer>> fourSum(int[] arr, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (arr==null || arr.length==0) return ans;
        Arrays.sort(arr);
        return fourSum(arr,0, target);
    }

    //arr已排好序
    //返回[strat,n-1]范围上，累加和为target的不重复的二元组
    public static List<List<Integer>> twoSum(int[] arr, int start, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (arr==null || arr.length==0) return ans;
        Arrays.sort(arr);
        int l=start;
        int r=arr.length-1;
        while (l<r) {
            if (arr[l]+arr[r] < target) {
                l++;
            }
            else if (arr[l]+arr[r] > target) {
                r--;
            }
            else { //==
                if (l==start || arr[l]!=arr[l-1]) {
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

    //arr已排好序
    //返回[strat,n-1]范围上，累加和为target的不重复的三元组
    public static List<List<Integer>> threeSum(int[] arr, int start, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (arr==null || arr.length==0) return ans;
        Arrays.sort(arr);
        int n=arr.length;
        for (int i = start; i < n-2; i++) {
            if (i==start || arr[i]!=arr[i-1]) {
                List<List<Integer>> twoSum = twoSum(arr, i + 1, target - arr[i]);
                for (List<Integer> cur: twoSum) {
                    cur.add(0, arr[i]);
                    ans.add(cur);
                }
            }
        }
        return ans;
    }

    //arr已排好序
    //返回[strat,n-1]范围上，累加和为target的不重复的四元组
    public static List<List<Integer>> fourSum(int[] arr, int start, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (arr==null || arr.length==0) return ans;
        Arrays.sort(arr);
        int n=arr.length;
        for (int i = start; i < n-3; i++) {
            if (i==start || arr[i]!=arr[i-1]) {
                List<List<Integer>> threeSum = threeSum(arr, i + 1, target - arr[i]);
                for (List<Integer> cur: threeSum) {
                    cur.add(0, arr[i]);
                    ans.add(cur);
                }
            }
        }
        return ans;
    }

}
