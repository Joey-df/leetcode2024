package com.joey.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author pei.liu
 */
public class Problem_0851_LoudAndRich {

    //图的起点是最有钱的人
    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static Queue<Integer> queue = new LinkedList<>();
    public static int[] indegree;

    //n个人，编号0~n-1
    public static int[] loudAndRich(int[][] richer, int[] quiet) {
        graph.clear();
        queue.clear();
        int n = quiet.length; //n个人
        if (richer == null || richer.length == 0) {
            int[] ans = new int[n];
            for (int i = 0; i < n; i++) {
                ans[i]=i; //如果没有有钱的关系，那么每个人的答案就是自己
            }
            return ans;
        }
        indegree = new int[n];
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        int m = richer.length;
        for (int i = 0; i < m; i++) {
            int[] curr = richer[i];
            int from = curr[0];
            int to = curr[1];
            graph.get(from).add(to);
            indegree[to]++;
        }
        int[] ans = new int[n];
        //入度为0的进入队列
        for (int i = 0; i < n; i++) {
            ans[i] = i; //初始化，钱数不小于自己的最安静的人就是自己，拓扑排序的过程中更新答案
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int next : graph.get(curr)) {
                //ans里存的是比当前人更有钱，安静值最小人的编号
                int u = ans[curr];
                int v = ans[next];
                if (quiet[u] < quiet[v]) {
                    ans[next] = u;
                }
                if (--indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        //richer = [[1,0],[2,1],[3,1],[3,7],[4,3],[5,3],[6,3]], quiet = [3,2,5,4,6,1,7,0]
        int[][] richer = new int[][]{
                {1, 0},
                {2, 1},
                {3, 1},
                {3, 7},
                {4, 3},
                {5, 3},
                {6, 3}
        };
        int[] quiet = new int[]{3, 2, 5, 4, 6, 1, 7, 0};
        int[] ans = loudAndRich(richer, quiet);
        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i] + " ");
        }
        System.out.println();
    }

}
