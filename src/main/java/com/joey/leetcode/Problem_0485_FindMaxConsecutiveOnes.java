package com.joey.leetcode;

/**
 * @author pei.liu
 */
public class Problem_0485_FindMaxConsecutiveOnes {

    //发现1就++，发现0就归0
    public int findMaxConsecutiveOnes(int[] nums) {
        int ans = 0;
        int cur = 0;
        for (int num : nums) {
            if (num == 1) {
                cur++;
            } else {
                cur = 0;
            }
            ans = Math.max(ans, cur);
        }
        return ans;
    }

    //思路，枚举每一个首1开头的位置
    public static int findMaxConsecutiveOnes1(int[] nums) {
        int ans = 0;
        int zeros = 0;
        int n = nums.length;
        for (int l = 0, r = 0; r < n; r++) {
            if (nums[r] == 0) zeros++;
            while (zeros > 0) {
                if (nums[l++] == 0) zeros--;
            }
            System.out.println("l:"+l);
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(findMaxConsecutiveOnes1(new int[]{1,0,1,1,0,1}));
    }
}
