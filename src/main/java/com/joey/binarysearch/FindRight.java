package com.joey.binarysearch;

/**
 * @author pei.liu
 */
public class FindRight {

    public static int findRight(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        int ans = -1, m;
        while (l <= r) {
            m = l + (r - l) / 2;
            if (nums[m] <= target) {
                ans = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }
}
