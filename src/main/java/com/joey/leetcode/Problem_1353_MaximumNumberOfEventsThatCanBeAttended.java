package com.joey.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author pei.liu
 */

//1353. 最多可以参加的会议数目
//给你一个数组 events，其中 events[i] = [startDayi, endDayi] ，表示会议 i 开始于 startDayi ，结束于 endDayi 。
//你可以在满足 startDayi <= d <= endDayi 中的任意一天 d 参加会议 i 。在任意一天 d 中只能参加一场会议。
//请你返回你可以参加的 最大 会议数目。
//提示：
//
//1 <= events.length <= 10^5
//events[i].length == 2
//1 <= startDayi <= endDayi <= 10^5
public class Problem_1353_MaximumNumberOfEventsThatCanBeAttended {

    //思路
    //每天只能参加一场会议
    //按天统计，来到每一天
    //1.解锁能开的会议，入小根堆（放会议的结束时间）
    //2.选择最紧迫的会议（结束时间最早的）
    //如果小根堆中会议过期，抛弃掉
    public int maxEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int n = events.length;
        int min = events[0][0]; //最小天
        int max = Integer.MIN_VALUE; //最大天
        for (int[] event : events) {
            max = Math.max(max, event[1]);
        }
        int ans = 0;
        int i = 0;
        for (int d = min; d <= max; d++) {
            //如果堆顶的会议已过期，抛弃掉
            while (!heap.isEmpty() && heap.peek() < d) {
                heap.poll();
            }
            //解锁d天开始的会议
            while (i < n && d == events[i][0]) {
                heap.offer(events[i][1]);
                i++;
            }
            //选择最紧迫的会去开
            if (!heap.isEmpty()) {
                heap.poll();
                ans++;
            }
        }
        return ans;
    }

}
