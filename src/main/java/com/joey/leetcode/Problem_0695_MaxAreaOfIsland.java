package com.joey.leetcode;

//695. 岛屿的最大面积
//给你一个大小为 m x n 的二进制矩阵 grid 。
//岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。
//你可以假设 grid 的四个边缘都被 0（代表水）包围着。
//岛屿的面积是岛上值为 1 的单元格的数目。
//计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
//抽象化问题：一个二维数组，里面仅包含0或1，求最多的1连在一起的个数
public class Problem_0695_MaxAreaOfIsland {
    public static int maxAreaOfIsland(int[][] grid) {
        int M = grid.length, N = grid[0].length;
        int ans = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 1) {
                    ans = Math.max(ans, fun(i, j, grid, M, N));
                }
            }
        }
        return ans;
    }

    //计算一个岛的面积
    //出发点为grid[x][y]==1
    private static int fun(int x, int y, int[][] grid, int M, int N) {
        if (x == -1 || x == M || y == -1 || y == N || grid[x][y] != 1) { //越界 或者 不等于1，直接返回0
            return 0;
        }
        //不越界 并且grid[x][y]==1
        grid[x][y] = 2; //防止走回头路
        return 1 + fun(x - 1, y, grid, M, N) +
                fun(x + 1, y, grid, M, N) +
                fun(x, y - 1, grid, M, N) +
                fun(x, y + 1, grid, M, N);
    }

}
