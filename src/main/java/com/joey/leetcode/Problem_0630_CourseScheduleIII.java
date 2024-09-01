package com.joey.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author pei.liu
 */
//630. 课程表 III
//这里有 n 门不同的在线课程，按从 1 到 n 编号。给你一个数组 courses ，
//其中 courses[i] = [durationi, lastDayi] 表示第 i 门课将会 持续 上 durationi 天课，并且必须在不晚于 lastDayi 的时候完成。
//你的学期从第 1 天开始。且不能同时修读两门及两门以上的课程。
//返回你最多可以修读的课程数目。
public class Problem_0630_CourseScheduleIII {

    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        int ans = 0;
        int time = 0; //当前来到的时间点
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
        for (int[] course : courses) {
            int a = course[0]; //a:当前课所需天数
            int b = course[1]; //b:当前课截止时间
            if (time + a <= b) {
                heap.offer(a);
                time += a;
                ans++;
            } else {
                //收益一样(所选课数量)的情况下，优先选所需天数少的，能让time变得更小，所选的课数量会最大化
                if (!heap.isEmpty() && heap.peek() > a) {
                    time -= heap.poll();
                    time += a;
                    heap.offer(a);
                }
            }
        }
        return ans;
    }

}
