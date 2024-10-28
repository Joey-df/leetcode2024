package com.joey.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author pei.liu
 */
//2101. 引爆最多的炸弹
//给你一个炸弹列表。一个炸弹的 爆炸范围 定义为以炸弹为圆心的一个圆。
//炸弹用一个下标从 0 开始的二维整数数组 bombs 表示，其中 bombs[i] = [xi, yi, ri] 。
//xi 和 yi 表示第 i 个炸弹的 X 和 Y 坐标，ri 表示爆炸范围的 半径 。
//你需要选择引爆 一个 炸弹。当这个炸弹被引爆时，所有 在它爆炸范围内的炸弹都会被引爆，这些炸弹会进一步将它们爆炸范围内的其他炸弹引爆。
//给你数组 bombs ，请你返回在引爆 一个 炸弹的前提下，最多 能引爆的炸弹数目。
//1 <= bombs.length <= 100
//bombs[i].length == 3
//1 <= xi, yi, ri <= 10^5
public class Problem_2101_DetonateTheMaximumBombs {

    //单源BDF
    //时间复杂度O(N^2)
    //建图：O(N^2)
    //调度O(N) * BFS:O(N)
    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    public static double distance(int[] a, int[] b) {
        return Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2));
    }

    public static void build(int[][] bombs) {
        graph.clear();
        int n = bombs.length;
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (distance(bombs[i], bombs[j]) <= bombs[i][2]) {
                    graph.get(i).add(j);
                }
            }
        }
    }

    public static int bfs(int start, int n) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n];
        q.offer(start);
        visited[start] = true;
        int ans = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int curr = q.poll();
                ans++;
                for (int next : graph.get(curr)) {
                    if (!visited[next]) {
                        visited[next] = true;
                        q.offer(next);
                    }
                }
            }
        }
        return ans;
    }

    public static int maximumDetonation(int[][] bombs) {
        build(bombs);
        int n = bombs.length;
        int ans = 0;
        //枚举从任意一个顶点出发
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, bfs(i, n));
        }
        return ans;
    }

}
