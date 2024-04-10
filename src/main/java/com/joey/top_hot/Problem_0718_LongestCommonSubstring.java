package com.joey.top_hot;

/**
 * 718. 最长重复子数组（最长公共子串）
 * 给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
 */
public class Problem_0718_LongestCommonSubstring {

    public int findLength(int[] nums1, int[] nums2) {
        if ((nums1 == null || nums1.length == 0) || (nums2 == null || nums2.length == 0)) return 0;
        int n = nums1.length;
        int m = nums2.length;
        //dp[i][j]含义：公共子串必须一nums1[i]和nums2[j]结尾的最大长度
        int[][] dp = new int[n][m];
        int ans = 0;
        for (int i = 0; i < m; i++) { // 第一行
            dp[0][i] = nums1[0] == nums2[i] ? 1 : 0;
            ans = Math.max(ans, dp[0][i]);
        }

        for (int i = 0; i < n; i++) { // 第一列
            dp[i][0] = nums1[i] == nums2[0] ? 1 : 0;
            ans = Math.max(ans, dp[i][0]);
        }
        //普遍位置
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = nums1[i] == nums2[j] ? dp[i - 1][j - 1] + 1 : 0;
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }
}
