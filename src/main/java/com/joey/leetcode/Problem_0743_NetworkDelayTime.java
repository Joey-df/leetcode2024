package com.joey.leetcode;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * @author pei.liu
 */
//743. 网络延迟时间
//有 n 个网络节点，标记为 1 到 n。
//给你一个列表 times，表示信号经过 有向 边的传递时间。
//times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， wi 是一个信号从源节点传递到目标节点的时间。
//现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
public class Problem_0743_NetworkDelayTime {

    public int networkDelayTime(int[][] times, int n, int k) {
        //先建图
        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
        int[] distance = new int[n + 1]; //distance[i]:源点到i的最短距离
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            distance[i] = Integer.MAX_VALUE;
        }
        for (int[] time : times) {
            int from = time[0];
            int to = time[1];
            int w = time[2];
            graph.get(from).add(new int[]{to, w});
        }
        //[当前点，源点到当前点的距离]
        PriorityQueue<int[]> heap = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        boolean[] visited = new boolean[n + 1];
        heap.offer(new int[]{k, 0});
        distance[k] = 0;
        while (!heap.isEmpty()) {
            int[] node = heap.poll();
            int curr = node[0];
            int dis = node[1]; //一旦弹出，源点到当前点的最短距离就确定了
            if (visited[curr]) {
                continue;
            }
            visited[curr] = true;
            for (int[] next : graph.get(curr)) {
                int to = next[0];
                int w = next[1];
                if (!visited[to] && dis + w < distance[to]) {
                    distance[to] = dis + w;
                    heap.offer(new int[]{to, dis + w});
                }
            }
        }
        int ans = 0;
        for (int i = 1; i < n + 1; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                return -1;
            }
            ans = Math.max(ans, distance[i]);
        }
        return ans;
    }
}
