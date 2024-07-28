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
        //自底向上单调递增的栈（存下标）
        //如果来了一个数num违反了这个规则，则从栈顶弹出元素top并结算之，top右边比自己小离自己最近的就是num
        //top左边离自己近比自己小的就是栈顶下标压着的数
        Stack<Integer> stack = new Stack<>(); //存下标
        int n = heights.length;
        // arr[i][0]: height[i]左边离自己最近比自己小的位置left，
        // arr[i][1]: 右边离自己最近比自己小的位置right
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int curi = stack.pop();
                arr[curi][1] = i;
                arr[curi][0] = !stack.isEmpty() ? stack.peek() : -1;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int curi = stack.pop();
            arr[curi][1] = n;
            arr[curi][0] = !stack.isEmpty() ? stack.peek() : -1;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int l = arr[i][0];
            int r = arr[i][1];
            ans = Math.max(ans, (r - l - 1) * heights[i]);
        }
        return ans;
    }

}