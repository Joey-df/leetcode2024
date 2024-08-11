package com.joey.leetcode;

import java.util.*;

/**
 * @author pei.liu
 */
//2050. 并行课程 III
//给你一个整数 n ，表示有 n 节课，课程编号从 1 到 n 。
//同时给你一个二维整数数组 relations ，其中 relations[j] = [prevCoursej, nextCoursej] ，
//表示课程 prevCoursej 必须在课程 nextCoursej 之前 完成（先修课的关系）。
//同时给你一个下标从 0 开始的整数数组 time ，其中 time[i] 表示完成第 (i+1) 门课程需要花费的 月份 数。
//请你根据以下规则算出完成所有课程所需要的 最少 月份数：
//如果一门课的所有先修课都已经完成，你可以在 任意 时间开始这门课程。
//你可以 同时 上 任意门课程 。
//请你返回完成所有课程所需要的 最少 月份数。
//注意：测试数据保证一定可以完成所有课程（也就是先修课的关系构成一个有向无环图）。
public class Problem_2050_ParallelCoursesIII {

    //注意：n 节课，课程编号从 1 到 n 。以及time的下标
    //分析
    //一个结点完成的时间：上游的最大值+自己需要的月份

    public static List<ArrayList<Integer>> graph = new ArrayList<>();
    public static Queue<Integer> queue = new LinkedList<>();
    public static int[] indegree;

    public static int minimumTime(int n, int[][] relations, int[] time) {
        //n 节课，课程编号从 1 到 n 。
        graph.clear();
        queue.clear();
        indegree = new int[n + 1]; //初始全为0
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>()); //0弃而不用
        }
        for (int[] edge : relations) {
            int from = edge[0];
            int to = edge[1];
            graph.get(from).add(to);
            indegree[to]++;
        }
        //入度为0的先入队列
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        int[] starts = new int[n];//标记每门课程的开始时间，初始化都为0
        int ans = 0;
        //开始遍历
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            int currNeed = starts[curr - 1] + time[curr - 1]; //当前课程完成需要的月数：开始时间+本身需要的月数
            ans = Math.max(ans, currNeed);
            for (int next : graph.get(curr)) {
                //一个结点的开始时间为，上游所有结点need的最大值
                starts[next - 1] = Math.max(starts[next - 1], currNeed);
                if (--indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        //n = 5, relations = [[1,5],[2,5],[3,5],[3,4],[4,5]], time = [1,2,3,4,5]
        //输出：12
        int n = 5;
        int[][] relations = new int[][]{
                {1, 5},
                {2, 5},
                {3, 5},
                {3, 4},
                {4, 5}
        };
        int[] time = new int[]{1, 2, 3, 4, 5};
        System.out.println(minimumTime(n, relations, time));
    }

}
