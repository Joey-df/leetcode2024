package com.joey.leetcode;

//287. 寻找重复数
//给定一个包含 n + 1 个整数的数组 nums ，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。
//假设 nums 只有 一个重复的整数 ，找出 这个重复的数 。
//你设计的解决方案必须不修改数组 nums 且只用常量级 O(1) 的额外空间。
//示例 1：
//输入：nums = [1,3,4,2,2]
//输出：2
//示例 2：
//输入：nums = [3,1,3,4,2]
//输出：3
//示例 3：
//输入：nums = [1,1]
//输出：1
//示例 4：
//输入：nums = [1,1,2]
//输出：1
public class Problem_0287_FindTheDuplicateNumber {
    //算法原型：单链表的第一个入环节点
    public static int findDuplicate(int[] nums) {
        if (nums == null || nums.length < 2) {
            return -1;
        }
        //可以想象0是链表的头节点
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0;
        while (slow != fast) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }
}
