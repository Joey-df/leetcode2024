package com.joey.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

//64. 最小路径和
//给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
//说明：每次只能向下或者向右移动一步。
public class Problem_0064_MinimumPathSum {

    public static int[][] distance;
    public static boolean[][] visited;
    public static PriorityQueue<int[]> heap = new PriorityQueue<>((o1, o2) -> {
        return o1[2] - o2[2];
    });

    public static int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        heap.clear();
        distance = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
            Arrays.fill(visited[i], false);
        }
        distance[0][0] = grid[0][0];
        heap.offer(new int[]{0, 0, grid[0][0]});
        while (!heap.isEmpty()) {
            int[] curr = heap.poll();
            int x = curr[0];
            int y = curr[1];
            int d = curr[2];
            if (visited[x][y]) {
                continue;
            }
            visited[x][y] = true;
            if (x == n - 1 && y == m - 1) {
                return d;
            }
            if (y + 1 < m) {
                if (distance[x][y] + grid[x][y + 1] < distance[x][y + 1]) {
                    distance[x][y + 1] = distance[x][y] + grid[x][y + 1];
                    heap.offer(new int[]{x, y + 1, distance[x][y] + grid[x][y + 1]});
                }
            }
            if (x + 1 < n) {
                if (distance[x][y] + grid[x + 1][y] < distance[x + 1][y]) {
                    distance[x + 1][y] = distance[x][y] + grid[x + 1][y];
                    heap.offer(new int[]{x + 1, y, distance[x][y] + grid[x + 1][y]});
                }
            }
        }
        return -1;
    }

    public static int minPathSum2(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        //dp[i][j]:从左上角出发走到(i,j)位置，最短路径是多少
        int[][] dp = new int[n][m];
        dp[0][0] = grid[0][0];
        //第一列
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        //第一行
        for (int i = 1; i < m; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[n - 1][m - 1];
    }


    public static void main(String[] args) {
        //grid = [[1,3,1],[1,5,1],[4,2,1]]
        int[][] grid = new int[][]{{1, 2, 3}, {4, 5, 6}};
        System.out.println(minPathSum(grid));
    }
}
