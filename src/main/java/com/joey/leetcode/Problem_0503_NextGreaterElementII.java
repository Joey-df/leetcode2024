package com.joey.leetcode;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author pei.liu
 */
//环形数组求下一个更大元素
public class Problem_0503_NextGreaterElementII {

    public static int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Stack<Integer> stack = new Stack<>(); //存下标
        for (int i = 0; i < n * 2; i++) {
            int ci = i %n;
            while (!stack.isEmpty() && nums[ci] > nums[stack.peek()]) {
                int curri = stack.pop();
                ans[curri] = nums[ci];
            }
            stack.push(ci);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,1};
        nextGreaterElements(arr);
    }

}
