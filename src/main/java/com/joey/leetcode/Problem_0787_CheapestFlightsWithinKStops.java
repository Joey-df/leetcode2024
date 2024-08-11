package com.joey.leetcode;

import com.sun.org.apache.bcel.internal.generic.ARETURN;

import java.util.*;

/**
 * @author pei.liu
 */
public class Problem_0787_CheapestFlightsWithinKStops {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        //邻接表建图
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] flight : flights) {
            graph.get(flight[0]).add(new int[]{flight[1], flight[2]});
        }
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{src, 0}); //[当前点，源点到当前点的距离]
        int level = 0; //level=0时，处理第1层结点，level=1时，处理第2层结点，刚好对应中转的概念
        while (!q.isEmpty()) {
            if (level > k) break;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();
                int u = curr[0];
                int d = curr[1];
                for (int[] next : graph.get(u)) {
                    int v = next[0];
                    int w = next[1];
                    if (d + w < distance[v]) {//距离变得更小，才更新。类似于Dijkstra算法的处理
                        distance[v] = d + w;
                        q.offer(new int[]{v, d + w});
                    }
                }
            }
            level++;
        }
        return distance[dst] == Integer.MAX_VALUE ? -1 : distance[dst];
    }

}
