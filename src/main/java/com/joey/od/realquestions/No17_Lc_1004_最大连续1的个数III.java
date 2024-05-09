package com.joey.od.realquestions;

/**
 * @author pei.liu
 */
//给定一个二进制数组 nums 和一个整数 k，如果可以翻转最多 k 个 0 ，则返回 数组中连续 1 的最大个数 。
//
//示例 1：
//输入：nums = [1,1,1,0,0,0,1,1,1,1,0], K = 2
//输出：6
//解释：[1,1,1,0,0,1,1,1,1,1,1]
//粗体数字从 0 翻转到 1，最长的子数组长度为 6。
//示例 2：
//输入：nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
//输出：10
//解释：[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
//粗体数字从 0 翻转到 1，最长的子数组长度为 10。
//
//提示：
//1 <= nums.length <= 10^5
//nums[i] 不是 0 就是 1
//0 <= k <= nums.length
public class No17_Lc_1004_最大连续1的个数III {

    //slide window
    //枚举每个开头位置，窗口内包含的0够k个，收集答案，抓全局max
    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;
        int l = 0;
        int r = 0;
        //窗口范围[l,r)
        int zeros = 0;
        while (r < n) {
            //r一直往右动，遇到0就计数
            if (nums[r++] == 0) {
                zeros++;
            }
            //如果zeros个数超过k，l被迫往右动
            while (zeros > k) {
                if (nums[l++] == 0) zeros--;
            }
            //zeros==k时，l停，始终维持窗口内zeros==k
            ans = Math.max(ans, r - l);
        }
        return ans;
    }

}
