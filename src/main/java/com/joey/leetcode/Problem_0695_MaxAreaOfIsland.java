package com.joey.leetcode;

//695. 岛屿的最大面积
//给你一个大小为 m x n 的二进制矩阵 grid 。
//岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。
//你可以假设 grid 的四个边缘都被 0（代表水）包围着。
//岛屿的面积是岛上值为 1 的单元格的数目。
//计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
//抽象化问题：一个二维数组，里面仅包含0或1，求最多的1连在一起的个数
public class Problem_0695_MaxAreaOfIsland {

    public static int[] move = {-1, 0, 1, 0, -1};

    //连城一片的1的最大数量
    public static int maxAreaOfIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans = Math.max(ans, f(grid, n, m, i, j));
            }
        }
        return ans;
    }

    //递归含义：从(x,y)出发连城一片的1是几个
    public static int f(int[][] grid, int n, int m, int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= m || grid[x][y] != 1) {
            return 0;
        }
        grid[x][y] = 2; //防止走回头路
        int ans = 1;
        for (int i = 0; i < 4; i++) {
            int nx = x + move[i];
            int ny = y + move[i + 1];
            ans += f(grid, n, m, nx, ny);
        }
        return ans;
    }
}
