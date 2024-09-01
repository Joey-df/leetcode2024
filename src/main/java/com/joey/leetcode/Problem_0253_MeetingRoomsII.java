package com.joey.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

//253. 会议室 II
//给你一个会议时间安排的数组 intervals ，
//每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi] ，
//返回 所需会议室的最小数量 。
//示例 1：
//输入：intervals = [[0,30],[5,10],[15,20]]
//输出：2
//示例 2：
//输入：intervals = [[7,10],[2,4]]
//输出：1
//提示：
//1 <= intervals.length <= 10^4
//0 <= starti < endi <= 10^6
public class Problem_0253_MeetingRoomsII {

    //最大线段重合问题，想象每个区间是一个线段
    //求给定线段的最大重合数量，就是所需的最少的会议室
    //因为只要重合，就得新开会议室
    //没重合的可以冲用会议室
    public int minMeetingRooms(int[][] intervals) {
        //思路
        //先按开始时间排序
        //用一个小跟堆，小根堆里放区间的结束时间
        //对于当前考察的每一个[s,e],小根堆里小于等于s的全部弹出，然后将e加入小根堆，抓此时小根堆的元素数量，求全局max
        //相当于是求出每一个s开头的最大重合数量
        Arrays.sort(intervals, (a,b)->a[0]-b[0]);
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int ans = 0;
        int n = intervals.length;
        for (int i = 0; i < n; i++) {
            int[] curr = intervals[i];
            int s = curr[0];
            int e = curr[1];
            while (!heap.isEmpty() && heap.peek() <= s) {
                heap.poll();
            }
            heap.offer(e);
            ans = Math.max(ans, heap.size());
        }
        return ans;
    }

}
