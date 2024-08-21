package com.joey.leetcode;

import com.joey.od.realquestions.No13_判断一个数能否分解为几个连续自然数之和;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author pei.liu
 */

//LCP 35. 电动车游城市
//小明的电动车电量充满时可行驶距离为 cnt，每行驶 1 单位距离消耗 1 单位电量，且花费 1 单位时间。
//小明想选择电动车作为代步工具。地图上共有 N 个景点，景点编号为 0 ~ N-1。
//他将地图信息以 [城市 A 编号,城市 B 编号,两城市间距离] 格式整理在在二维数组 paths，表示城市 A、B 间存在双向通路。
//初始状态，电动车电量为 0。每个城市都设有充电桩，charge[i] 表示第 i 个城市每充 1 单位电量需要花费的单位时间。
//请返回小明最少需要花费多少单位时间从起点城市 start 抵达终点城市 end。
public class Problem_LCP35_VisitCityMinCost {

    public static ArrayList<ArrayList<int[]>> graph;
    public static int[][] distance;

    public static void build(int[][] paths, int n, int cnt) {
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] path : paths) {
            int f = path[0];
            int t = path[1];
            int c = path[2];
            graph.get(f).add(new int[]{t, c});
            graph.get(t).add(new int[]{f, c});
        }

        distance = new int[n][cnt + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }
    }

    public static int electricCarPlan(int[][] paths, int cnt, int start, int end, int[] charge) {
        int n = charge.length;
        build(paths, n, cnt);
        //i号城市，剩余电量，作为图上的点
        boolean[][] visited = new boolean[n][cnt + 1];
        //0：当前城市
        //1：到当前城市剩余电量
        //2：源点到当前城市的花费
        PriorityQueue<int[]> heap = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        heap.offer(new int[]{start, 0, 0});
        distance[start][0] = 0;
        while (!heap.isEmpty()) {
            int[] record = heap.poll();
            int cur = record[0];
            int power = record[1];
            int cost = record[2];
            if (visited[cur][power]) {
                continue;
            }
            visited[cur][power] = true;
            if (cur == end) {
                return cost;
            }

            if (power < cnt) {
                // 充一格电
                // cur, power+1
                if (!visited[cur][power + 1] && cost + charge[cur] < distance[cur][power + 1]) {
                    distance[cur][power + 1] = cost + charge[cur];
                    heap.add(new int[]{cur, power + 1, cost + charge[cur]});
                }
            }
            for (int[] edge : graph.get(cur)) {
                // 不充电去别的城市
                int nextCity = edge[0];
                int restPower = power - edge[1];
                int nextCost = cost + edge[1];
                if (restPower >= 0 && !visited[nextCity][restPower] && nextCost < distance[nextCity][restPower]) {
                    distance[nextCity][restPower] = nextCost;
                    heap.add(new int[]{nextCity, restPower, nextCost});
                }
            }
        }
        return -1;
    }
}
