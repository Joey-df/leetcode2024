package com.joey.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author pei.liu
 */
//994. 腐烂的橘子
//在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
//
//值 0 代表空单元格；
//值 1 代表新鲜橘子；
//值 2 代表腐烂的橘子。
//每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
//返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1
public class Problem_994_OrangesRotting {

    public static Queue<int[]> q = new LinkedList<>();
    public static int n;
    public static int m;
    public static int bad = 0;
    public static int good = 0;
    public static boolean[][] visited;
    public static int[] move = {-1, 0, 1, 0, -1};

    //思路
    //先把腐烂的橘子入队列
    //使用一个visited标记不走回头路
    //BFS扩出的最大层数返回
    public static int orangesRotting(int[][] grid) {
        q.clear();
        good=0;
        bad=0;
        n = grid.length;
        if (n == 0) return 0;
        m = grid[0].length;
        if (m == 0) return 0;
        visited = new boolean[n][m];
        init(grid);
        if (bad == 0 && good == 0) return 0; //啥橘子也没有
        if (bad == 0) return -1; //没有坏橘子，但有好橘子，所以不可能变腐烂
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();
                //帮当前的curr的上下左右拉进队列
                next(grid, curr);
            }
            level++;
        }
        int rest = rest(grid);
        if (rest > 0) return -1; //如果还剩下好橘子，带遍不可能全变腐烂
        return level - 1;
    }

    private static void init(int[][] grid) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    q.offer(new int[]{i, j});
                    visited[i][j] = true;
                    bad++;
                }
                if (grid[i][j] == 1) {
                    good++;
                }
            }
        }
    }

    private static int rest(int[][] grid) {
        int rest = 0; //剩余好橘子的数量
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) rest++;
            }
        }
        return rest;
    }

    private static void next(int[][] grid, int[] curr) {
        for (int j = 0; j < 4; j++) {
            int nx = curr[0] + move[j];
            int ny = curr[1] + move[j + 1];
            //好橘子并且没处理过
            if (nx >= 0 && nx < n && ny >= 0 && ny < m && grid[nx][ny] == 1 && !visited[nx][ny]) {
                q.offer(new int[]{nx, ny});
                //grid[nx][ny] = 2; //好橘子变腐烂
                visited[nx][ny] = true;
            }
        }
    }

    public static void main(String[] args) {
        //[[2,1,1],[1,1,0],[0,1,1]]
        int[][] g = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };
        System.out.println(orangesRotting(g));
    }
}
