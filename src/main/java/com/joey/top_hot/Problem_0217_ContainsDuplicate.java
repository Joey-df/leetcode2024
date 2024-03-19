package com.joey.top_hot;

import java.util.HashSet;

/**
 * 217. 存在重复元素
 * 给定一个整数数组，判断是否存在重复元素。
 * 如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
 * 示例 1:
 * 输入: [1,2,3,1]
 * 输出: true
 * 示例 2:
 * 输入: [1,2,3,4]
 * 输出: false
 * 示例 3:
 * 输入: [1,1,1,3,3,4,3,2,4,2]
 * 输出: true
 */
public class Problem_0217_ContainsDuplicate {

    //平凡解：哈希表的解法
    //如果要额外空间复杂度O(1)的话，请使用堆排序，然后从左往右遍历找到重复出现的数
    public boolean containsDuplicate(int[] nums) {
        if (nums==null || nums.length<2) {
            return false;
        }
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            } else {
                set.add(nums[i]);
            }
        }
        return false;
    }
}
