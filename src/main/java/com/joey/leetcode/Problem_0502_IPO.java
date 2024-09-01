package com.joey.leetcode;

import java.util.PriorityQueue;

/**
 * @author pei.liu
 */
//502. IPO
//假设 力扣（LeetCode）即将开始 IPO 。为了以更高的价格将股票卖给风险投资公司，力扣 希望在 IPO 之前开展一些项目以增加其资本。
//由于资源有限，它只能在 IPO 之前完成最多 k 个不同的项目。帮助 力扣 设计完成最多 k 个不同项目后得到最大总资本的方式。
//给你 n 个项目。对于每个项目 i ，它都有一个纯利润 profits[i] ，和启动该项目需要的最小资本 capital[i] 。
//最初，你的资本为 w 。当你完成一个项目时，你将获得纯利润，且利润将被添加到你的总资本中。
//总而言之，从给定项目中选择 最多 k 个不同项目的列表，以 最大化最终资本 ，并输出最终可获得的最多资本。
//答案保证在 32 位有符号整数范围内。
public class Problem_0502_IPO {

    //profits：纯利润
    //capital：投入
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[]{profits[i], capital[i]};
        }
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]); //按照投入组织的小根堆
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]); //按照纯利润组织的大根堆
        for (int i = 0; i < n; i++) {
            minHeap.offer(arr[i]);
        }
        while (k > 0) {
            //先从小根堆里解锁能做的项目，入大根堆
            while (!minHeap.isEmpty() && w >= minHeap.peek()[1]) {
                int[] curr = minHeap.poll();
                maxHeap.offer(curr);
            }
            //从大根堆中取出收益最大的项目，做一次
            if (maxHeap.isEmpty()) {
                break;
            }
            int profit = maxHeap.poll()[0];
            w += profit;
            k--;
        }
        return w;
    }

}
