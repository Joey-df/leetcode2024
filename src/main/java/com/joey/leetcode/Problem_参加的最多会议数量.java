package com.joey.leetcode;

import java.util.Arrays;

/**
 * @author pei.liu
 */
// 会议必须独占时间段的最大会议数量
// 给定若干会议的开始、结束时间
// 你参加某个会议的期间，不能参加其他会议
// 返回你能参加的最大会议数量
// 来自真实大厂笔试，没有在线测试，对数器验证
public class Problem_参加的最多会议数量 {

    //不相交的最大区间数量
    public static int maxValue1(int[][] meetings) {
        return fun(meetings, 0);
    }

    public static int fun(int[][] meetings, int i) {
        if (i == meetings.length) {
            //收集答案
            int cur = -1; //当前到达的时间点
            int ans = 0;
            for (int j = 0; j < meetings.length; j++) {
                int[] meeting = meetings[j];
                if (cur <= meeting[0]) {
                    ans++;
                    cur = meeting[1];
                }
            }
            return ans;
        } else {
            int ans = Integer.MIN_VALUE;
            for (int j = i; j < meetings.length; j++) {
                swap(meetings, i, j);
                ans = Math.max(ans, fun(meetings, i + 1));
                swap(meetings, i, j);
            }
            return ans;
        }
    }

    public static void swap(int[][] arr, int i, int j) {
        int[] curr = arr[i];
        arr[i] = arr[j];
        arr[j] = curr;
    }

    public static int maxValue2(int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> a[1] - b[1]);
        int curr = -1;
        int ans = 0;
        for (int i = 0; i < meetings.length; i++) {
            int[] meeting = meetings[i];
            if (curr <= meeting[0]) {
                ans++;
                curr = meeting[1];
            }
        }
        return ans;
    }

    // 为了验证
    // 生成随机会议
    public static int[][] randomMeeting(int n, int m) {
        int[][] ans = new int[n][2];
        for (int i = 0, a, b; i < n; i++) {
            a = (int) (Math.random() * m);
            b = (int) (Math.random() * m);
            if (a == b) {
                ans[i][0] = a;
                ans[i][1] = a + 1;
            } else {
                ans[i][0] = Math.min(a, b);
                ans[i][1] = Math.max(a, b);
            }
        }
        return ans;
    }

    // 对数器
    // 为了验证
    public static void main(String[] args) {
        int N = 10;
        int M = 12;
        int testTimes = 2000;
        System.out.println("测试开始");
        for (int i = 1; i <= testTimes; i++) {
            int n = (int) (Math.random() * N) + 1;
            int[][] meeting = randomMeeting(n, M);
            int ans1 = maxValue1(meeting);
            int ans2 = maxValue2(meeting);
            if (ans1 != ans2) {
                // 如果出错了
                // 可以增加打印行为找到一组出错的例子
                // 然后去debug
                System.out.println("出错了！");
            }
            if (i % 100 == 0) {
                System.out.println("测试到第" + i + "组");
            }
        }
        System.out.println("测试结束");
    }

}
