package com.joey.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pei.liu
 */
//1512. 好数对的数目
//给你一个整数数组 nums 。
//如果一组数字 (i,j) 满足 nums[i] == nums[j] 且 i < j ，就可以认为这是一组 好数对 。
//返回好数对的数目。
public class Problem_1512_NumberOfGoodPairs {

    //哈希表做法
    public int numIdenticalPairs1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.sort(nums);
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int curr = map.getOrDefault(nums[i], 0);
            ans += curr;
            map.put(nums[i], curr + 1);
        }
        return ans;
    }

    //二分
    public int numIdenticalPairs(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int l = left(nums, 0, i - 1, nums[i]); //向左找>=nums[i]最左的位置
            ans += l != -1 ? i - l : 0;
        }
        return ans;
    }

    public int left(int[] nums, int l, int r, int target) {
        int ans = -1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] >= target) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

}
