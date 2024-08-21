package com.joey.leetcode;

import java.util.Arrays;

// 312.戳气球
// 有 n 个气球，编号为0到n-1，每个气球上都标有一个数字，这些数字存在数组nums中
// 现在要求你戳破所有的气球。戳破第 i 个气球
// 你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币
// 这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号
// 如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球
// 求所能获得硬币的最大数量
// 测试链接 : https://leetcode.cn/problems/burst-balloons/
public class Problem_0312_BurstBalloons {

    // 需要添加外部信息的dp
    // 记忆化搜索
    public static int maxCoins(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 2];
        arr[0] = 1;
        arr[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            arr[i + 1] = nums[i];
        }
        int[][] dp = new int[n + 2][n + 2];
        for (int i = 0; i < n + 2; i++) {
            Arrays.fill(dp[i], -1);
        }
        return fun(arr, 1, n, dp);
    }

    //返回arr[l,r]范围上戳气球的最大得分
    //枚举每个位置的气球最后爆
    //一定有arr[l-1]没爆
    //一定有arr[r+1]没爆
    public static int fun(int[] arr, int l, int r, int[][] dp) {
        if (l > r) return 0;
        if (dp[l][r] != -1) {
            return dp[l][r];
        }
        int ans = Integer.MIN_VALUE;
        if (l == r) {
            ans = arr[l] * arr[l - 1] * arr[r + 1];
        } else {
            //l < r
            for (int k = l; k <= r; k++) { //枚举每个最后爆的位置
                int cur = arr[k] * arr[l - 1] * arr[r + 1] + fun(arr, l, k - 1, dp) + fun(arr, k + 1, r, dp);
                ans = Math.max(ans, cur);
            }
        }
        dp[l][r] = ans;
        return ans;
    }

    public static void main(String[] args) {
//        String s = "enn";
        String s = "arn";
        int ans=0;

        for (int i = 0; i < s.length(); i++) {
            ans += s.charAt(i);
        }
        System.out.println(ans);
    }

}
