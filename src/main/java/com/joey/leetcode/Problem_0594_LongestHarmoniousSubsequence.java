package com.joey.leetcode;

import java.util.HashMap;

//594. 最长和谐子序列
//和谐数组是指一个数组里元素的最大值和最小值之间的差别 正好是 1 。
//现在，给你一个整数数组 nums ，请你在所有可能的子序列中找到最长的和谐子序列的长度。
//数组的子序列是一个由数组派生出来的序列，它可以通过删除一些元素或不删除元素、且不改变其余元素的顺序而得到。
//
//示例 1：
//输入：nums = [1,3,2,2,5,2,3,7]
//输出：5
//解释：最长的和谐子序列是 [3,2,2,2,3]
//示例 2：
//输入：nums = [1,2,3,4]
//输出：2
//示例 3：
//输入：nums = [1,1,1,1]
//输出：0
//
//提示：
//1 <= nums.length <= 2 * 10^4
//-10^9 <= nums[i] <= 10^9
public class Problem_0594_LongestHarmoniousSubsequence {

    //思路：
    //统计出每个元素出现的词频 map
    //如果存在相邻的k，k+1，则收集答案
    //遍历map抓最大值
    public int findLHS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        //<nums[i], freq>
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int ans = 0;
        for (int key : map.keySet()) {
            //注意保证key+1不能越界
            if (key < Integer.MAX_VALUE && map.containsKey(key + 1)) {
                ans = Math.max(ans, map.get(key) + map.get(key + 1));
            }
        }
        return ans;
    }

}
