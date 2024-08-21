package com.joey.leetcode;

import java.util.Arrays;

/**
 * @author pei.liu
 */
//2560. 打家劫舍 IV
//沿街有一排连续的房屋。每间房屋内都藏有一定的现金。现在有一位小偷计划从这些房屋中窃取现金。
//由于相邻的房屋装有相互连通的防盗系统，所以小偷 不会窃取相邻的房屋 。
//小偷的 窃取能力 定义为他在窃取过程中能从单间房屋中窃取的 最大金额 。
//给你一个整数数组 nums 表示每间房屋存放的现金金额。形式上，从左起第 i 间房屋中放有 nums[i] 美元。
//另给你一个整数 k ，表示窃贼将会窃取的 最少 房屋数。小偷总能窃取至少 k 间房屋。
//返回小偷的 最小 窃取能力。
//1 <= nums.length <= 10^5
//1 <= nums[i] <= 10^9
//1 <= k <= (nums.length + 1)/2
public class Problem_2560_HouseRobberIV {

    //二分答案法
    //求至少头K间房的最小能力
    //能力有范围[min,max]之间，因为小于min时能偷的房屋为0，为max时所有的房屋都能偷
    //所以可以在这个范围上进行二分
    //中点m代表能力，在m能力时，如果能偷的房屋>=k，说明大于m的能力都能偷够>=k间房，，记录答案，左侧继续二分；否认能力小了，右侧二分
    public static int minCapability(int[] nums, int k) {
        int min = Arrays.stream(nums).min().getAsInt();
        int max = Arrays.stream(nums).max().getAsInt();
        int ans = max;
        for (int l = min, r = max, m; l <= r; ) {
            m = l + ((r - l) >> 1);
            if (can(nums, m) >= k) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    //返回：能力为limit时，能偷够几间房
    public static int can(int[] nums, int limit) {
        int n = nums.length;
        if (n == 1) return limit >= nums[0] ? 1 : 0;
        if (n == 2) return nums[0] >= limit || nums[1] >= limit ? 1 : 0;
        //n>2
        int prepre = limit >= nums[0] ? 1 : 0;
        int pre = limit >= nums[0] || limit >= nums[1] ? 1 : 0;
        for (int i = 2; i < n; i++) {
            int t = limit >= nums[i] ? 1 : 0;
            int cur = Math.max(pre, Math.max(t, t + prepre));
            prepre = pre;
            pre = cur;
        }
        return pre;
    }

    //贪心
    public static int can1(int[] nums, int limit) {
        int ans = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (limit >= nums[i]) {
                ans += 1;
                i++;
            }
        }
        return ans;
    }

}
