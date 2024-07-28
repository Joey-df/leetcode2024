package com.joey.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author pei.liu
 */
//1162. 地图分析
public class Problem_1162_AsFarFromLandAsPossible {

    public static int N;
    public static int M;
    public static boolean[][] visited;
    public static Queue<int[]> queue = new LinkedList<>();
    public static int[][] distance;
    public static int sea;
    public static int[] move = {-1, 0, 1, 0, -1};
    public static int ans;

    public int maxDistance(int[][] grid) {
        //init
        init(grid);
        if (sea == 0 || sea == N * M) return -1;
        //start
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int x = curr[0];
                int y = curr[1];
                for (int j = 0; j < 4; j++) {
                    int nx = x + move[j];
                    int ny = y + move[j + 1];
                    if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny]) {
                        distance[nx][ny] = distance[x][y] + 1;
                        queue.offer(new int[]{nx, ny});
                        visited[nx][ny] = true;
                        ans = Math.max(ans, distance[nx][ny]);
                    }
                }
            }
        }
        return ans;
    }

    private static void init(int[][] grid) {
        N = grid.length;
        M = grid[0].length;
        sea = 0;
        ans = 0;
        queue.clear();
        visited = new boolean[N][M];
        distance = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                distance[i][j] = Integer.MAX_VALUE;
                if (grid[i][j] == 0) {
                    sea++;
                } else {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                    distance[i][j] = 0;
                }
            }
        }
    }
}
