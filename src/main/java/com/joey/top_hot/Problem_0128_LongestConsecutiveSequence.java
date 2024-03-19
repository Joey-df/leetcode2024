package com.joey.top_hot;

import java.util.HashSet;

/**
 * 128. 最长连续序列
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 进阶：你可以设计并实现时间复杂度为 O(n) 的解决方案吗？
 *
 * 示例 1：
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *
 * 示例 2：
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 */
public class Problem_0128_LongestConsecutiveSequence {

    public static int longestConsecutive(int[] nums) {
        if (nums==null || nums.length==0) {
            return 0;
        }
        HashSet<Integer> set = new HashSet<>();
        for (int i: nums) set.add(i);
        int best = 1;
        for (int x: set) {
            // 枚举每一个可能的开头
            if (!set.contains(x-1)) {
                int y = x;
                while(set.contains(x)) {
                    x++;
                }
                best = Math.max(best, x-y);
            }
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println(longestConsecutive(new int[]{0,3,7,2,5,8,4,6,0,1}));
    }
}
