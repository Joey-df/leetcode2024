package com.joey.leetcode;

import java.util.HashMap;

/**
 * @author pei.liu
 */
//1695. 删除子数组的最大得分
//给你一个正整数数组 nums ，请你从中删除一个含有 若干不同元素 的子数组。删除子数组的 得分 就是子数组各元素之 和 。
//返回 只删除一个 子数组可获得的 最大得分 。
//如果数组 b 是数组 a 的一个连续子序列，即如果它等于 a[l],a[l+1],...,a[r] ，那么它就是 a 的一个子数组。
//
//示例 1：
//输入：nums = [4,2,4,5,6]
//输出：17
//解释：最优子数组是 [2,4,5,6]
//示例 2：
//输入：nums = [5,2,1,2,5,2,1,2,5]
//输入：nums = [2,5,1,2,5,2,1,2,5]
//输出：8
//解释：最优子数组是 [5,2,1] 或 [1,2,5]
//
//提示：
//1 <= nums.length <= 10^5
//1 <= nums[i] <= 10^4
public class Problem_1695_MaximumUniqueSubarray {

    //题意可以抽象为：找出一个子数组，要求不包含重复字符，并且使得这个子数组的累加和最大
    //思路：使用一个滑动窗口，窗口维持里面不含重复元素
    //[3,4,2,4,5,6]
    public int maximumUniqueSubarray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        // 标记每个位置的元素是否进入过窗口
        // value=0:没进入过窗口
        // value=1:进入过窗口
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, 0);
        }
        int l = 0;
        int r = 0;
        int sum = 0; //窗口内的累加和
        int ans = 0;
        while (r < n) {
            //r位置的数没进入过窗口，就进窗口，直到nums[r]之前进入过，停
            while (r < n && map.get(nums[r]) == 0) {
                sum += nums[r];
                map.put(nums[r], 1);
                r++;
            }
            //r位置的数之前已经进入过窗口
            //此时更新答案
            ans = Math.max(ans, sum);
            //l往右动,直到r位置的数（之前进入的）出窗口，停
            while (r < n && map.get(nums[r]) != 0) {
                sum -= nums[l];
                map.put(nums[l], 0);
                l++;
            }
        }
        return ans;
    }

}
