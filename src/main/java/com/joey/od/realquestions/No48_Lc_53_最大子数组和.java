package com.joey.od.realquestions;

/**
 * @author pei.liu
 */
//https://renjie.blog.csdn.net/article/details/135033706
public class No48_Lc_53_最大子数组和 {

    public int maxSubArray(int[] arr) {
        if (arr==null || arr.length==0) return 0;
        int n = arr.length;
        int[] dp =new int[n]; // dp[i]含义：以i位置结尾的子数组的最大累加和是多少，客观上子数组必以某个位置结尾
        dp[0] = arr[0];
        int ans = dp[0];
        for (int i=1;i<n;i++) {
            int p1 = arr[i];
            int p2 = dp[i-1]+arr[i];
            dp[i] = Math.max(p1,p2);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
