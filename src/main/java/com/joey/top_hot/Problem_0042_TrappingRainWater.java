package com.joey.top_hot;

/**
 * 42. 接雨水
 * 给定一个数组arr，已知其中所有的值都是非负的，将这个数组看作一个容器， 请返回容器能装多少水
 * 比如，arr = {3，1，2，5，2，4}，根据值画出的直方图就是容器形状，该容 器可以装下5格水
 * 再比如，arr = {4，5，1，3，2}，该容器可以装下2格水
 */
public class Problem_0042_TrappingRainWater {

    public static int trap(int[] arr) {

        if (arr == null || arr.length < 3) {
            return 0;
        }
        int N = arr.length;
        int[] leftMax = new int[N];
        int lm = arr[0];
        for (int i = 0; i < N; i++) {
            lm = Math.max(lm, arr[i]);
            leftMax[i] = lm;
        }
        int[] rightMax = new int[N];
        int rm = arr[N - 1];
        for (int i = N - 1; i >= 0; i--) {
            rm = Math.max(rm, arr[i]);
            rightMax[i] = rm;
        }
        //print(leftMax);
        //print(rightMax);
        int ans = 0;
        for (int i = 1; i < N - 1; i++) {
            ans += Math.max((Math.min(leftMax[i], rightMax[i]) - arr[i]), 0);
        }
        return ans;
    }


    private static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 1, 2, 5, 2, 4};
        System.out.println(trap(arr));
    }
}
