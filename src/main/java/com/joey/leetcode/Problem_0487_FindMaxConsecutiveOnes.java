package com.joey.leetcode;

/**
 * @author pei.liu
 */
//487. 最大连续1的个数 II
//给定一个二进制数组 nums ，如果最多可以翻转一个 0 ，则返回数组中连续 1 的最大个数。
//示例 1：
//输入：nums = [1,0,1,1,0]
//输出：4
//解释：翻转第一个 0 可以得到最长的连续 1。
//     当翻转以后，最大连续 1 的个数为 4。
//示例 2:
//输入：nums = [1,0,1,1,0,1]
//输出：4
//提示:
//1 <= nums.length <= 10^5
//nums[i] 不是 0 就是 1.
public class Problem_0487_FindMaxConsecutiveOnes {

    //尝试每个开头
    //r每次往右动
    //当发现的0的个数==1，并且又发现0了，此时收集答案，并且l往右动，直至发现0的个数又归0为止
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;
        int zeros = 0;
        int ans = Integer.MIN_VALUE;
        int l = 0;
        int r = 0;
        while (r < n) {
            //r往右动
            while (r < n && (zeros == 0 || nums[r] != 0)) { //没发现0或者发现了0但是当前位置是1，r就往右扩
                if (nums[r] == 0) zeros++;
                r++;
            }
            ans = Math.max(ans, r - l);
            //l往右动,来到把0吐出去之后的第一个1位置
            while (zeros > 0) {
                if (nums[l] == 0) zeros--;
                l++;
            }
        }
        return ans;
    }

    //[1,0,1,1,0,1]
    public int findMaxConsecutiveOnes2(int[] nums) {
        int l = 0;
        //窗口为[l,r]
        int maxLen = 0;
        int count = 0;
        for (int r = 0; r < nums.length; r++) { //每一步都r++
            if (nums[r] == 0) {
                count++;
            }
            //始终维持窗口内最多只有一个0
            while (count > 1) {
                if (nums[l++] == 0) {
                    count--;
                }
            }
            maxLen = Math.max(maxLen, r - l + 1);
        }
        return maxLen;
    }
}
