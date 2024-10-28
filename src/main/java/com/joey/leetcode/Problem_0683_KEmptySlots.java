package com.joey.leetcode;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author pei.liu
 */
//683. K 个关闭的灯泡
//n 个灯泡排成一行，编号从 1 到 n 。最初，所有灯泡都关闭。每天 只打开一个 灯泡，直到 n 天后所有灯泡都打开。
//给你一个长度为 n 的灯泡数组 blubs ，其中 bulbs[i] = x 意味着在第 (i+1) 天，我们会把在位置 x 的灯泡打开，其中 i 从 0 开始，x 从 1 开始。
//给你一个整数 k ，请返回恰好有两个打开的灯泡，且它们中间 正好 有 k 个 全部关闭的 灯泡的 最小的天数 。如果不存在这种情况，返回 -1 。
//
//示例 1：
//输入：
//bulbs = [1,3,2]，k = 1
//输出：2
//解释：
//第一天 bulbs[0] = 1，打开第一个灯泡 [1,0,0]
//第二天 bulbs[1] = 3，打开第三个灯泡 [1,0,1]
//第三天 bulbs[2] = 2，打开第二个灯泡 [1,1,1]
//返回2，因为在第二天，两个打开的灯泡之间恰好有一个关闭的灯泡。
//示例 2：
//输入：bulbs = [1,2,3]，k = 1
//输出：-1
public class Problem_0683_KEmptySlots {

    //维护一个K长度的窗口
    //窗口内的最小值，同时大于左侧、右侧的值，满足条件
    //因为此种情况下，代表左侧及右侧的灯已亮，而窗口的所有灯都没亮
    //枚举每个窗口的开始位置，窗口的开始位置范围：[1,n-k-1],因为最后一个窗口的右边界只能是n-2
    public static int kEmptySlots(int[] bulbs, int k) {
        int n = bulbs.length;
        int[] position = new int[n];
        for (int i = 0; i < n; i++) {
            position[bulbs[i] - 1] = i;
        }
        if (k == 0) {
            int ans = Integer.MAX_VALUE;
            for (int i = 1; i < n; i++) {
                ans = Math.min(ans, Math.max(position[i], position[i - 1]));
            }
            return ans == Integer.MAX_VALUE ? -1 : ans + 1; //天的编号，所以要+1
        }
        int ans = Integer.MAX_VALUE;
        LinkedList<Integer> q = new LinkedList<>(); //放下标 小 -> 大
        int l = 0;
        int r = 0;
        //先形成k-1长度的窗口
        for (; r < k - 1; r++) {
            while (!q.isEmpty() && position[q.peekLast()] > position[r]) {
                q.pollLast();
            }
            q.offerLast(r);
        }
        for (; r < n - 1; l++, r++) { //因为最后一个窗口的右边界只能是n-2
            while (!q.isEmpty() && position[q.peekLast()] > position[r]) {
                q.pollLast();
            }
            q.offerLast(r);
            //收集答案
            int head = q.peekFirst();
            if (l > 0 && position[head] > position[l - 1] && position[head] > position[r + 1]) {
                ans = Math.min(ans, Math.max(position[l - 1], position[r + 1]));
            }
            if (head == l) {
                q.pollFirst();
            }

        }
        return ans == Integer.MAX_VALUE ? -1 : ans + 1; //天的编号，所以要+1
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //i+1天开arr[i]的花
        //转化为第i天开arr[i]-1号花
        int[] arr = new int[]{1, 3, 2};
        int[] pos = new int[arr.length]; //第pos[i]号花在i号天开
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("第i+1=%d天开arr[i]=%d的花；arr[i]-1=%d位置的花在第i=%d号天开\n", i + 1, arr[i], arr[i] - 1, i);
            pos[arr[i] - 1] = i;
        }
        print(arr);
        print(pos);

        int[] a = {48, 27, 71, 68, 30, 1, 45, 22, 23, 95, 35, 41, 65, 100, 54, 15, 75, 67, 31, 79, 36, 26, 5, 40, 88, 25, 7, 77, 16, 59, 74, 52, 14, 60, 98, 34, 82, 10, 96, 70, 73, 92, 55, 66, 28, 69, 19, 94, 53, 42, 64, 50, 89, 46, 80, 72, 76, 63, 81, 33, 3, 85, 99, 13, 86, 29, 8, 4, 51, 11, 62, 78, 39, 32, 44, 17, 38, 49, 20, 84, 24, 57, 93, 18, 37, 83, 6, 61, 12, 91, 90, 47, 97, 2, 56, 43, 58, 21, 9, 87};
        System.out.println(kEmptySlots(a, 89));
    }

}
