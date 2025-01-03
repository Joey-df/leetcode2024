package com.joey.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author pei.liu
 */
//3112. 访问消失节点的最少时间
//给你一个二维数组 edges 表示一个 n 个点的无向图，
//其中 edges[i] = [ui, vi, lengthi] 表示节点 ui 和节点 vi 之间有一条需要 lengthi 单位时间通过的无向边。
//同时给你一个数组 disappear ，其中 disappear[i] 表示节点 i 从图中消失的时间点，在那一刻及以后，你无法再访问这个节点。
//注意，图有可能一开始是不连通的，两个节点之间也可能有多条边。
//请你返回数组 answer ，answer[i] 表示从节点 0 到节点 i 需要的 最少 单位时间。
//如果从节点 0 出发 无法 到达节点 i ，那么 answer[i] 为 -1 。
//数据量：
//1 <= n <= 5 * 10^4
//0 <= edges.length <= 10^5
//edges[i] == [ui, vi, lengthi]
//0 <= ui, vi <= n - 1
//1 <= lengthi <= 10^5
//disappear.length == n
//1 <= disappear[i] <= 10^5
public class Problem_3112_MinimumTime {

    public static ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
    //[当前点, 源点到当前点的距离]
    public static PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]);
    public static boolean[] visited;
    public static int[] distance; //distance[i]：源点到当前点的距离

    public static void build(int n, int[][] edges) {
        graph.clear();
        q.clear();
        visited = new boolean[n];
        distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int w = edge[2];
            graph.get(from).add(new int[]{to, w});
            graph.get(to).add(new int[]{from, w});
        }
    }

    public static int[] minimumTime(int n, int[][] edges, int[] disappear) {
        build(n, edges);
        q.offer(new int[]{0, 0});//源点到自己的距离是0
        distance[0] = 0;
        //dij
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            if (visited[curr[0]]) {
                continue;
            }
            visited[curr[0]] = true;
            int dis = curr[1]; //一旦弹出，源点到当前点的最短距离就确定了
            //但是在此之前它已经消失了，不可达！
            //它的邻居也不会再处理，continue！
            //它已经弹出过了，它的邻居也再不会通过它进行跳转了
            if (dis >= disappear[curr[0]]) {
                distance[curr[0]] = Integer.MAX_VALUE;
                continue;
            }
            for (int[] next : graph.get(curr[0])) {
                int v = next[0];
                int w = next[1];
                if (!visited[v] && dis + w < distance[v]) {
                    distance[v] = dis + w;
                    q.offer(new int[]{v, dis + w});
                }
            }
        }
        return clt(n);
    }

    public static int[] clt(int n) {
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = distance[i] == Integer.MAX_VALUE ? -1 : distance[i];
        }
        return ans;
    }

}
