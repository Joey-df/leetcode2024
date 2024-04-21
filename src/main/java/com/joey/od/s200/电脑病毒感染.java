package com.joey.od.s200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 题目描述
 * 一个局域网只内有很多台电脑，分别标注为 1 ~ N 的数字。相连接的电脑距离不一样，所以感染时间不一样，感染时间用t 表示。
 * 其中网络内一台电脑被病毒感染，求其感染网络内所有的电脑最少需要多长时间。如果最后有电脑不会感染，则返回-1。
 * 给定一个数组 times 表示一台电脑把相邻电脑感染所用的时间: path[i] = {i, j, t} 表示: 电脑i 上的病毒感染 j，需要时间 t 。
 *
 * 输入描述
 * 第一行输入一个整数N，表示局域网内电脑个数 N，1<= N<= 200 ；
 * 第二行输入一个整数M, 表示有 M 条网络连接；
 * 接下来M行, 每行输入为 i,j,t 。表示电脑 i 感染电脑 j 需要时间t。(1 <= i, j <= N)
 * 最后一行为病毒所在的电脑编号。
 *
 * 输出描述
 * 输出最少需要多少时间才能感染全部电脑，如果不存在输出 -1
 *
 * 示例1
 * 输入：
 * 4
 * 3
 * 2 1 1
 * 2 3 1
 * 3 4 1
 * 2
 *
 * 输出：
 * 2
 *
 * 示例2
 * 输入：
 * 4
 * 3
 * 2 1 1
 * 2 3 1
 * 3 4 1
 * 3
 *
 * 输出：
 * -1
 *
 * 作者：code5bug
 * 链接：https://www.nowcoder.com/discuss/592807891728347136
 * 来源：牛客网
 */

// 题解
// Dijkstra模版题
// lc743. 网络延迟时间

import java.util.*;
public class 电脑病毒感染 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt(); // m条边
        int[][] times = new int[m][3];
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt(); // from
            int v = sc.nextInt(); // to
            int w = sc.nextInt(); // 出发点到达目标点的耗时
            times[i][0] = u;
            times[i][1] = v;
            times[i][2] = w;
        }
        int start = sc.nextInt(); // 出发点
        System.out.println(networkDelayTime(times, n, start));
    }

    public static int networkDelayTime(int[][] times, int n, int start) {
        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : times) {
            graph.get(edge[0]).add(new int[] { edge[1], edge[2] });
        }
        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;
        boolean[] visited = new boolean[n + 1];
        // 0 : 当前节点
        // 1 : 源点到当前点距离
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        heap.add(new int[] { start, 0 });
        while (!heap.isEmpty()) {
            int u = heap.poll()[0];
            if (visited[u]) {
                continue;
            }
            visited[u] = true;
            for (int[] edge : graph.get(u)) {
                int v = edge[0];
                int w = edge[1];
                if (!visited[v] && distance[u] + w < distance[v]) {
                    distance[v] = distance[u] + w;
                    heap.add(new int[] { v, distance[u] + w });
                }
            }
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                return -1;
            }
            ans = Math.max(ans, distance[i]);
        }
        return ans;
    }
}

