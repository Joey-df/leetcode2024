package com.joey.top_hot;

/**
 * @author pei.liu
 */
//1802. 有界数组中指定下标处的最大值
//给你三个正整数 n、index 和 maxSum 。你需要构造一个同时满足下述所有条件的数组 nums（下标 从 0 开始 计数）：
//nums.length == n
//nums[i] 是 正整数 ，其中 0 <= i < n
//abs(nums[i] - nums[i+1]) <= 1 ，其中 0 <= i < n-1
//nums 中所有元素之和不超过 maxSum
//nums[index] 的值被 最大化
//返回你所构造的数组中的 nums[index] 。
//
//注意：abs(x) 等于 x 的前提是 x >= 0 ；否则，abs(x) 等于 -x 。
//
//示例 1：
//输入：n = 4, index = 2,  maxSum = 6
//输出：2
//解释：数组 [1,1,2,1] 和 [1,2,2,1] 满足所有条件。不存在其他在指定下标处具有更大值的有效数组。
//示例 2：
//输入：n = 6, index = 1,  maxSum = 10
//输出：3
//
//提示：
//1 <= n <= maxSum <= 10^9
//0 <= index < n
public class Problem_1802_MaxValue {

    //二分答案法 + 等差数列求和
    //时间复杂度：O(log(maxSum))
    //空间复杂度：O(1)
    public int maxValue(int n, int index, int maxSum) {
        int l = 1;
        int r = maxSum;
        int ans = -1;
        int m = 0;
        while (l <= r) {
            m = l + ((r - l) >> 1);
            //计算[0, index] 和 [index, n - 1]区间所有元素的和
            //左右两个区间的元素个数，分别为，index + 1、 n - index
            if (sum(m, index + 1) + sum(m, n - index) - m <= maxSum) {
                // 题目要求：找满足条件的最大值，
                // 所以m使得 <=maxSum 达标，此时记录答案，向右侧二分，看还有没有更大的m满足条件
                ans = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }

    //cnt个元素，从1开始递增到x，允许前置多个1，如11123456
    //返回累加和
    //1. cnt >= x, 那么会有多余的数全部放置为1: cnt - x, 剩下的数为1,2,...x, 和为(x+1)*x/2。
    //总和 = cnt-x + (x+1)*x/2
    //2. cnt < x, 那么放置的数为 x-cnt+1, x-cnt, ...., x-1, x 即[x-cnt+1, x],
    //总和 = (2x-cnt+1) * cnt / 2
    public long sum(long x, int cnt) {
        if (cnt >= x) {
            return (x + 1) * x / 2 + (cnt - x); // 比如cnt=9,x=6,数据状况就是111123456
        }
        // cnt < x
        return (x * 2 - cnt + 1) * cnt / 2; // 比如cnt=3,x=6,数据状况就是456
    }

}
