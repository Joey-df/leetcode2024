package com.joey.leetcode;

import java.util.Arrays;

//16. 最接近的三数之和
//给定一个包括 n 个整数的数组 nums 和 一个目标值 target。
//找出 nums 中的三个整数，使得它们的和与 target 最接近。
//返回这三个数的和。假定每组输入只存在唯一答案。
//示例：
//输入：nums = [-1,2,1,-4], target = 1
//输出：2
//解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
//提示：
//3 <= nums.length <= 10^3
//-10^3 <= nums[i] <= 10^3
//-10^4 <= target <= 10^4
public class Problem_0016_3SumClosest {

    //排序+双指针
    //时间复杂度：O(n^2)
    public int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        //初始为最大的三数之和
        int res = nums[n - 3] + nums[n - 2] + nums[n - 1];
        for (int i = 0; i < n - 3; i++) { //枚举第一个数
            int l = i + 1; //第二个数
            int r = n - 1; //第三个数
            while (l < r) {
                int cur = nums[i] + nums[l] + nums[r];
                //距离变小，更新答案
                if (Math.abs(target - cur) < Math.abs(target - res)) {
                    res = cur;
                }
                if (cur < target) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return res;
    }

}
