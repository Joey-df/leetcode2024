package com.joey.binarysearch;

/**
 * @author pei.liu
 */
public class FindLeft {

    public static int findLeft(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        int ans = -1;
        int m;
        while (l <= r) {
            m = l + (r - l) / 2;
            if (nums[m] >= target) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }
}
