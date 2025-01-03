package com.joey.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author pei.liu
 */
public class Problem_1851_MinInterval {

    //流程设计：扫描线
    //需要对区间进行排序，start升序
    //查询也进行排序，同时记录原有下标，方便填答案时知道填到哪
    //查询的答案：包含当前值的最小区间，即贯穿的最小区间
    //求最小，所以使用小根堆，小根堆依据区间的长度排序
    //需要注意的是每个区间的右边界，需要带上，用于判断当前val是否可以贯穿
    public int[] minInterval(int[][] intervals, int[] queries) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int n = queries.length;
        int m = intervals.length;
        //0：查询的key
        //1：原来的位置i
        int[][] requests = new int[n][2];
        for (int i = 0; i < n; i++) {
            requests[i] = new int[]{queries[i], i};
        }
        Arrays.sort(requests, Comparator.comparingInt(a -> a[0]));
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        //0：区间的长度
        //1：区间的右边界
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int j = 0; //标记区间
        for (int[] request : requests) {
            int curr = request[0];
            int idx = request[1];
            //先把左边界 start <= curr的区间加小根堆
            while (j < m && intervals[j][0] <= curr) {
                int start = intervals[j][0];
                int end = intervals[j][1];
                int len = end - start + 1;
                q.offer(new int[]{len, end});
                j++;
            }
            //检查堆顶end过期（end<curr）的区间，弹出
            while (!q.isEmpty() && q.peek()[1] < curr) {
                q.poll();
            }
            //当前堆如果不空，则堆顶就是当前curr的答案
            if (!q.isEmpty()) {
                ans[idx] = q.peek()[0];
            }
        }
        return ans;
    }

}
