package com.joey.leetcode;

// 875.爱吃香蕉的珂珂
// 珂珂喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有 piles[i] 根香蕉
// 警卫已经离开了，将在 h 小时后回来。
// 珂珂可以决定她吃香蕉的速度 k （单位：根/小时)
// 每个小时，她将会选择一堆香蕉，从中吃掉 k 根
// 如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉
// 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
// 返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）
// 测试链接 : https://leetcode.cn/problems/koko-eating-bananas/
public class Problem_0875_MinEatingSpeed {

    // 时间复杂度O(n * log(max))，额外空间复杂度O(1)
    public static int minEatingSpeed(int[] arr, int h) {
        //吃香蕉的策略：
        //如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉
        //so，吃香蕉的速度最大就是arr中的最大值，最小速度就是1
        //so，所求的最小速度k有范围了[l,max(arr)]
        //so，二分答案法
        //中点的速度mid，如果mid速度达标，往左侧二分，寻找更小的达标的速度，否则往右侧二分，最终得到答案
        int l = 1;
        int r = arr[0];
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            r = Math.max(r, arr[i]);
        }
        int mid;
        int ans = r;
        while (l <= r) {
            mid = l + ((r - l) >> 1);
            if (check(mid, arr, h)) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    public static boolean check(int speed, int[] arr, int h) {
        long need = 0;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            //向上取整
            //a/b =》(a+b-1) / b
            //比如 arr[i]=11, speed=5 ,结果应该是3
            need += (arr[i] + speed - 1) / speed;
        }
        return need <= h;
    }

}
