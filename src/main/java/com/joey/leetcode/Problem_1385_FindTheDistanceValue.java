package com.joey.leetcode;

import java.util.Arrays;

/**
 * @author pei.liu
 */
//1385. 两个数组间的距离值
//给你两个整数数组 arr1 ， arr2 和一个整数 d ，请你返回两个数组之间的 距离值 。
//「距离值」 定义为符合此距离要求的元素数目：对于元素 arr1[i] ，不存在任何元素 arr2[j] 满足 |arr1[i]-arr2[j]| <= d 。
public class Problem_1385_FindTheDistanceValue {

    //|x-y| <= d
    //x-y <= d 并且 y-x <= d
    //y >= x-d 并且 y <= x+d
    //x-d <=y<= x+d
    //即如果arr2中不存在[x-d, x+d]的数，ans加1
    //arr1 = [4,5,8], arr2 = [10,9,1,8], d = 2
    //对于这个示例，对于4来说，arr2中如果不存在[2,6]的数，计数+1
    // 1 8 9 10
    public static int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int ans = 0;
        for (int num : arr1) {
            int left = left(arr2, num - d);
            int right = right(arr2, d + num);
            if (right < left) {
                ans++;
            }
        }
        return ans;
    }

    public static int left(int[] arr, int t) {
        int n = arr.length;
        int l = 0;
        int r = n - 1;
        int ans = n;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (arr[m] >= t) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    public static int right(int[] arr, int t) {
        int n = arr.length;
        int l = 0;
        int r = n - 1;
        int ans = -1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (arr[m] <= t) {
                ans = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr1 = {4, 5, 8}, arr2 = {10, 9, 1, 8};
        int d = 2;
        System.out.println(findTheDistanceValue(arr1, arr2, d));
    }
}
