package com.joey.od.s200;

/**
 * @author pei.liu
 */

import java.util.*;

public class 跳格子 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];

        // 读取输入数组
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int k = sc.nextInt();
        System.out.println(getAns(arr, n, k));
    }
    // 获取最终结果
    public static int getAns(int[] arr, int n, int k) {
        Queue<Integer[]> q = new LinkedList<>();
        int[] dp = new int[n];
        dp[0] = arr[0];
        q.add(new Integer[]{0, dp[0]});

        for (int i = 1; i < n; i++) {
            // 移除超出窗口范围的元素
            while (!q.isEmpty() && q.peek()[0] < i - k) {
                q.poll();
            }
            // 计算dp[i]，取队列中的最大值+当前元素的值
            dp[i] = arr[i] + q.peek()[1];

            // 移除队列中小于等于dp[i]的元素
            while (!q.isEmpty() && q.peek()[1] <= dp[i]) {
                q.poll();
            }

            // 将当前dp[i]加入队列中
            q.add(new Integer[]{i, dp[i]});
        }

        return dp[n - 1];
    }
}
