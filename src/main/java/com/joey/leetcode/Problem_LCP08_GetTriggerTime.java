package com.joey.leetcode;

import java.util.Arrays;

/**
 * @author pei.liu
 */
public class Problem_LCP08_GetTriggerTime {

    public static int[] getTriggerTime(int[][] increase, int[][] requirements) {
        int n = increase.length;
        int[][] matrix = new int[n + 1][3];
        matrix[0] = new int[3];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = matrix[i - 1][j] + increase[i - 1][j];
            }
        }
        int[] first = new int[n + 1];
        int[] second = new int[n + 1];
        int[] third = new int[n + 1];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) first[j] = matrix[j][i];
                if (i == 1) second[j] = matrix[j][i];
                if (i == 2) third[j] = matrix[j][i];
            }
        }
        int m = requirements.length;
        int[] ans = new int[m];
        Arrays.fill(ans, -1);
        for (int i = 0; i < m; i++) {
            int[] requirement = requirements[i];
            int left0 = left(first, requirement[0]);
            int left1 = left(second, requirement[1]);
            int left2 = left(third, requirement[2]);
            if (left0 <= n && left1 <= n && left2 <= n) {
                ans[i] = Math.max(left0, Math.max(left1, left2));
            }
        }
        return ans;
    }

    public static int left(int[] nums, int target) {
        int n = nums.length;
        int l = 0;
        int r = n - 1;
        int hit = n;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] >= target) {
                hit = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return hit;
    }

    public static void main(String[] args) {
        int[][] increase = {
                {2, 8, 4},
                {2, 5, 0},
                {10, 9, 8}
        };
        int[][] req = {
                {2, 11, 3},
                {15, 10, 7},
                {9, 17, 12},
                {8, 1, 14}
        };
        int[] triggerTime = getTriggerTime(increase, req);
        for (int i = 0; i < triggerTime.length; i++) {
            System.out.println(triggerTime[i]);
        }
    }
}
