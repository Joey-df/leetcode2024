package com.joey.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author pei.liu
 */
//2799. 统计完全子数组的数目
//给你一个由 正 整数组成的数组 nums 。
//如果数组中的某个子数组满足下述条件，则称之为 完全子数组 ：
//子数组中 不同 元素的数目等于整个数组不同元素的数目。
//返回数组中 完全子数组 的数目。
//子数组 是数组中的一个连续非空序列。
//
//示例 1：
//输入：nums = [1,3,1,2,2]
//输出：4
//解释：完全子数组有：[1,3,1,2]、[1,3,1,2,2]、[3,1,2] 和 [3,1,2,2] 。
//示例 2：
//输入：nums = [5,5,5,5]
//输出：10
//解释：数组仅由整数 5 组成，所以任意子数组都满足完全子数组的条件。子数组的总数为 10 。
//
//提示：
//1 <= nums.length <= 1000
//1 <= nums[i] <= 2000
public class Problem_2799_CountCompleteSubarraysInAnArray {

    //不同元素的数目，就是元素的种类数
    //滑动窗口+set
    //可以使用992题的思路解这道题
    public static int countCompleteSubarrays(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int e : nums) {
            set.add(e);
        }
        int kinds = set.size();
        int n = nums.length;
        int ans = 0;
        //map.size: 当前窗口内元素的类型
        HashMap<Integer, Integer> map = new HashMap<>();
        int r = 0;
        for (int i = 0; i < n; i++) { //枚举每个i开头位置的答案
            while (r < n && map.size() < kinds) {
                map.put(nums[r], map.getOrDefault(nums[r], 0) + 1);
                r++;
            }
            //r来到初次满足条件的位置，[i,r)是数组的范围
            if (map.size() == kinds) {
                //i为当前子数组的开始位置，r 为初次满足的位置，则i开头，把r及其以后的元素都加上的任意一个子数组都满足
                ans += n - r + 1;
                // 1 3 4 2 4 2
                //       3 4 5
                //       r   n
            }
            //i位置的答案已求完，i即将++，nums[i]出窗口
            map.put(nums[i], map.get(nums[i]) - 1);
            if (map.get(nums[i]) == 0) map.remove(nums[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 1, 2, 2};
        System.out.println(countCompleteSubarrays(arr));
    }
}
