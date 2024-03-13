package com.joey.binarysearch;

/**
 * @author pei.liu
 */
public class FindNumber {

    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        int m;
        while (l <= r) {
            m = l + ((r - l) >> 1);
            if (nums[m] > target) {
                r = m - 1;
            } else if (nums[m] < target) {
                l = m + 1;
            } else {
                return m;
            }
        }
        return -1;
    }
}
