package com.joey.leetcode;

import java.util.Stack;

/**
 * @author pei.liu
 */
//907. 子数组的最小值之和
//给定一个整数数组 arr，找到 min(b) 的总和，其中 b 的范围为 arr 的每个（连续）子数组。
//由于答案可能很大，因此 返回答案模 10^9 + 7 。
//示例 1：
//输入：arr = [3,1,2,4]
//输出：17
//解释：
//子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。
//最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。
//提示：
//1 <= arr.length <= 3 * 10^4
//1 <= arr[i] <= 3 * 10^4
public class Problem_0907_SumOfSubarrayMinimums {

    public static int mod = 1000000007;

    //思路
    //求出每一个位置左边、右边离自己最近比自己小的位置
    //对于每一个位置，求解答案（如果同时存在左边和右边自己大的位置，即不能为-1）
    public static int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int[][] help = new int[n][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]) {
                int curri = stack.pop();
                help[curri][0] = stack.isEmpty() ? -1 : stack.peek();
                help[curri][1] = i;
            }
            stack.push(i);
        }
        //结算
        while (!stack.isEmpty()) {
            int curri = stack.pop();
            help[curri][0] = stack.isEmpty() ? -1 : stack.peek();
            help[curri][1] = n;
        }
        //修正(该题这里不能修正，修正的话如果遇到重复元素会多算)
        /*for (int i = n-2; i >=0; i--) {
            if (help[i][1] != -1 && arr[i] == arr[help[i][1]]) {
                help[i][1] = help[help[i][1]][1];
            }
        }*/
        //收集答案
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int left = help[i][0];
            int right = help[i][1];
            long curr = ((long) (i - left) * (right - i) * arr[i]);
            ans = (ans + curr) % mod;
        }
        return (int) ans;
    }

    public static void main(String[] args) {
        int[] arr = {71, 55, 82, 55};
        System.out.println(sumSubarrayMins(arr));
        System.out.println((int)Math.pow(2,3));
    }
}
