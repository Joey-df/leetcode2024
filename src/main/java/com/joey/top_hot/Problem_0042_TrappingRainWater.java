package com.joey.top_hot;

/**
 * 42. 接雨水
 * 给定一个数组arr，已知其中所有的值都是非负的，将这个数组看作一个容器， 请返回容器能装多少水
 * 比如，arr = {3，1，2，5，2，4}，根据值画出的直方图就是容器形状，该容 器可以装下5格水
 * 再比如，arr = {4，5，1，3，2}，该容器可以装下2格水
 */
public class Problem_0042_TrappingRainWater {

    // 预处理数组：先依次生成左边的最大值，右边的最大值数组，两个数组
    // 撸一遍结算答案
    public int trap(int[] arr) {
        if (arr == null || arr.length < 3) return 0;
        int n = arr.length;
        int[] maxLeft = new int[n];
        int[] maxRight = new int[n];
        for (int i = 0; i < n; i++) {
            maxLeft[i] = i == 0 ? arr[i] : (Math.max(arr[i], maxLeft[i - 1]));
        }
        for (int i = n - 1; i >= 0; i--) {
            maxRight[i] = i == n - 1 ? arr[n - 1] : (Math.max(arr[i], maxRight[i + 1]));
        }
        //collect ans
        int ans = 0;
        for (int i = 1; i < n - 1; i++) {
            ans += Math.max(0, Math.min(maxLeft[i], maxRight[i]) - arr[i]);
        }
        return ans;
    }
}
