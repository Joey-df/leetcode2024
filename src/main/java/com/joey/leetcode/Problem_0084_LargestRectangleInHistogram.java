package com.joey.leetcode;

import java.util.Stack;

//84. 柱状图中最大的矩形
//https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
public class Problem_0084_LargestRectangleInHistogram {

    //思路
    //遍历求的数组中每个height[i]左边离自己最近比自己小的位置left，右边离自己最近比自己小的位置right
    //height[i]的答案为height[i]*(right-left-1)
    public static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                int curri = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                int width = i - left - 1;
                ans = Math.max(ans, heights[curri] * width);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int curri = stack.pop();
            int left = stack.isEmpty() ? -1 : stack.peek();
            int width = n - left - 1;
            ans = Math.max(ans, heights[curri] * width);
        }
        return ans;
    }

}