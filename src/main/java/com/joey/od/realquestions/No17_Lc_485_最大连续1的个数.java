package com.joey.od.realquestions;

/**
 * @author pei.liu
 */
//给定一个二进制数组 nums ， 计算其中最大连续 1 的个数。
//
//示例 1：
//输入：nums = [1,1,0,1,1,1]
//输出：3
//解释：开头的两位和最后的三位都是连续 1 ，所以最大连续 1 的个数是 3.
//示例 2:
//输入：nums = [1,0,1,1,0,1]
//输出：2
public class No17_Lc_485_最大连续1的个数 {

    //dp: 求出以每个位置结尾的答案，抓max
    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int pre = nums[0] == 1 ? 1 : 0;
        int ans = pre;
        for (int i = 1; i < n; i++) {
            int cur = nums[i] == 0 ? 0 : (pre > 0 ? pre + 1 : 1);
            ans = Math.max(ans, cur);
            pre = cur;
        }
        return ans;
    }

    // slide window
    // 枚举每个可能的开头，以1开头的才考虑
    public int findMaxConsecutiveOnes2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        // [l,r) 窗口的范围，方便下标计算
        int r = 0;
        int ans = 0;
        for (int l = 0; l < n; ) {
            if (nums[l] == 0) {
                l++;
                r++;
                continue;
            }
            // nums[l]==1
            while (r < n && nums[r] == 1) {
                r++;
            }
            //r越界 或 nums[r]==0时跳出
            ans = Math.max(ans, r - l);
            //nums[r]==0，所以r来到下个位置尝试
            r++;
            l = r;
        }
        return ans;
    }
}
