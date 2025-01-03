package com.joey.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author pei.liu
 */
//3296. 移山所需的最少秒数
//给你一个整数 mountainHeight 表示山的高度。
//同时给你一个整数数组 workerTimes，表示工人们的工作时间（单位：秒）。
//工人们需要 同时 进行工作以 降低 山的高度。对于工人 i :
//山的高度降低 x，需要花费 workerTimes[i] + workerTimes[i] * 2 + ... + workerTimes[i] * x 秒。例如：
//山的高度降低 1，需要 workerTimes[i] 秒。
//山的高度降低 2，需要 workerTimes[i] + workerTimes[i] * 2 秒，依此类推。
//返回一个整数，表示工人们使山的高度降低到 0 所需的 最少 秒数。
//
//示例 1：
//输入： mountainHeight = 4, workerTimes = [2,1,1]
//输出： 3
//解释：
//将山的高度降低到 0 的一种方式是：
//工人 0 将高度降低 1，花费 workerTimes[0] = 2 秒。
//工人 1 将高度降低 2，花费 workerTimes[1] + workerTimes[1] * 2 = 3 秒。
//工人 2 将高度降低 1，花费 workerTimes[2] = 1 秒。
//因为工人同时工作，所需的最少时间为 max(2, 3, 1) = 3 秒。
//
//示例 2：
//输入： mountainHeight = 10, workerTimes = [3,2,2,4]
//输出： 12
//解释：
//工人 0 将高度降低 2，花费 workerTimes[0] + workerTimes[0] * 2 = 9 秒。
//工人 1 将高度降低 3，花费 workerTimes[1] + workerTimes[1] * 2 + workerTimes[1] * 3 = 12 秒。
//工人 2 将高度降低 3，花费 workerTimes[2] + workerTimes[2] * 2 + workerTimes[2] * 3 = 12 秒。
//工人 3 将高度降低 2，花费 workerTimes[3] + workerTimes[3] * 2 = 12 秒。
//所需的最少时间为 max(9, 12, 12, 12) = 12 秒。
//
//示例 3：
//输入： mountainHeight = 5, workerTimes = [1]
//输出： 15
//
//解释：
//这个示例中只有一个工人，所以答案是 workerTimes[0] + workerTimes[0] * 2 + workerTimes[0] * 3 + workerTimes[0] * 4 + workerTimes[0] * 5 = 15 秒。
//提示：
//1 <= mountainHeight <= 10^5
//1 <= workerTimes.length <= 10^4
//1 <= workerTimes[i] <= 10^6
public class Problem_3296_MinNumberOfSeconds {

    //答案有没有范围
    //最少：1
    //最多：max*(1+2+...+height)
    public static long minNumberOfSeconds1(int mountainHeight, int[] workerTimes) {
        int h = mountainHeight;
        int max = Integer.MIN_VALUE;
        for (int time : workerTimes) {
            max = Math.max(max, time);
        }
        long l = 1L;
        long r = (long) h * (h + 1) / 2 * max;
        long ans = r;
        while (l <= r) {
            long m = l + (r - l) / 2; //所需的时间
            //f(): 如果规定所需时间为m，返回能否使得山变为0
            if (f(workerTimes, m, mountainHeight)) {
                ans = m;
                r = m - 1; //时间大于m，肯定更加可以
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    //如果规定所需时间为t，返回能否使得山变为0
    public static boolean f(int[] times, long t, int height) {
        long can = 0;//可以使山降低多少
        for (int time : times) {
            //time：当前工人工作的时间
            int l = 0;
            int r = height;
            long curr = l; //二分搜索当前工人在不超过t的时间内能移除的最大高度
            while (l <= r) {
                int m = l + (r - l) / 2; //降低高度为m时
                if ((long) m * (m + 1) / 2 * time <= t) {
                    curr = m;
                    l = m + 1; //还可以找到更大的高度
                } else {
                    r = m - 1;
                }
            }
            can += curr;
            if (can >= height) {
                return true;
            }
        }
        return false;
    }


    //小根堆解法
    //https://leetcode.cn/problems/minimum-number-of-seconds-to-make-mountain-height-zero/solutions/2929490/zui-xiao-dui-mo-ni-jie-jian-ling-shen-de-36hc/
    public static long minNumberOfSeconds2(int mountainHeight, int[] workerTimes) {
        PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1[2], o2[2]));
        //int[]:已经使用的次数，单位代价，下一次使用应付出的代价
        for (int cost : workerTimes) {
            pq.offer(new long[]{0, cost, cost});
        }
        long res = 0;
        while (mountainHeight-- > 0) {
            long[] cur = pq.poll();
            long times = cur[0], cost = cur[1], totalCost = cur[2];
            res = Math.max(res, totalCost);
            pq.offer(new long[]{times + 1, cost, (times + 2) * cost + totalCost});
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 2, 4};
        int h = 10;
        //System.out.println(g(3));
        System.out.println(minNumberOfSeconds2(h, nums));
    }
}
