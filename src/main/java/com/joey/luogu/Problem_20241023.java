package com.joey.luogu;

/**
 * @author pei.liu
 */
public class Problem_20241023 {
    //给定一个非负数组arr，一个正数m，求累加和大于m，并且长度在[minLen,maxLen]范围的子数组的个数，子数组必须是连续的一段。
    //1<=arr.length<=10^5, 1<=minLen<maxLen<=arr.length
    //给定一个非负数组arr，一个正数m，规定窗口长度在[minLen,maxLen]范围内，
    //规定：窗口内累加和>m 的窗口达标，返回达标的窗口有多少个
    //数据范围：
    //1 <= arr.length <= 10^5, 1 <= minLen < maxLen <= arr.length
    public static int countSubarrays(int[] arr, int m, int minLen, int maxLen) {
        int n = arr.length;
        int count = 0;
        int[] presum = presum(arr);
        for (int len = minLen; len <= maxLen; len++) {//枚举每一个长度的子数组
            //1, 9, 1, 0, 12
            for (int end = len - 1; end < n; end++) {
                int sum = sum(presum, end - len + 1, end);
                //System.out.printf("[%d,%d], %d\n", end - len + 1, end, sum);
                if (sum > m) count++;
            }
        }
        return count;
    }


    public static int countSubarrays2(int[] arr, int m, int minLen, int maxLen) {
        int n = arr.length;
        int ans = 0;
        int windowSum = 0;
        int r = 0;
        for (int i = 0; i < n; i++) { //枚举每个开头位置
            //只要windowSum没超过m就继续
            while (r < n && windowSum <= m) {
                windowSum += arr[r];
                r++;
            }
            //r来到初次windowSum > m的位置 或者 越界位置
            //r-i是当前窗口的大小
            if (r - i >= minLen && r - i <= maxLen && windowSum > m) {
                //System.out.printf("i=%d, r=%d\n", i, r);
                ans += Math.min(n, i + maxLen) - (r - 1); //直接计算出数量,r-1是当前window的右边界，则一直到maxLen的任意一个窗口都满足，因为窗口变大，累加和只会变大或者不变，有单调性
            }
            //i即将++，i位置的数出窗口
            windowSum -= arr[i];
        }
        return ans;
    }

    public static int[] presum(int[] arr) {
        int n = arr.length;
        int[] presum = new int[n];
        presum[0] = arr[0];
        for (int i = 1; i < n; i++) {
            presum[i] = presum[i - 1] + arr[i];
        }
        return presum;
    }

    public static int sum(int[] presum, int l, int r) {
        return l == 0 ? presum[r] : presum[r] - presum[l - 1];
    }

    public static void main(String[] args) {
        // 示例 1
        int[] arr1 = {1, 1, 1, 1, 1, 1, 3};
        int m1 = 3;
        int minLen1 = 3;
        int maxLen1 = 6;
        System.out.println(countSubarrays(arr1, m1, minLen1, maxLen1)); // 输出 10
        System.out.println(countSubarrays2(arr1, m1, minLen1, maxLen1)); // 输出 10

        // 示例 2
        int[] arr2 = {1, 9, 1, 0, 12};
        int m2 = 10;
        int minLen2 = 2;
        int maxLen2 = 3;
        System.out.println(countSubarrays(arr2, m2, minLen2, maxLen2)); // 输出 3
        System.out.println(countSubarrays2(arr2, m2, minLen2, maxLen2)); // 输出 3

        int[] arr3 = {7, 0, 1, 5, 8, 1, 5, 4, 6, 19};
        int m3 = 6;
        int minLen3 = 2;
        int maxLen3 = 6;
        System.out.println(countSubarrays(arr2, m2, minLen2, maxLen2)); // 输出 3
        System.out.println(countSubarrays2(arr2, m2, minLen2, maxLen2)); // 输出 3
    }


}
