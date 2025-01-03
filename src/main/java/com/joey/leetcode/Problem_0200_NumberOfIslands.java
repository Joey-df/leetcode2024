package com.joey.leetcode;

//200. 岛屿数量
//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
//岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
//此外，你可以假设该网格的四条边均被水包围。
//示例 1：
//输入：grid = [
//["1","1","1","1","0"],
//["1","1","0","1","0"],
//["1","1","0","0","0"],
//["0","0","0","0","0"]
//]
//输出：1
//示例 2：
//输入：grid = [
//["1","1","0","0","0"],
//["1","1","0","0","0"],
//["0","0","1","0","0"],
//["0","0","0","1","1"]
//]
//输出：3
public class Problem_0200_NumberOfIslands {

    public static int[] move = {-1, 0, 1, 0, -1};

    public static int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    ans++;
                    infect(grid, i, j);
                }
            }
        }
        return ans;
    }

    public static void infect(char[][] grid, int i, int j) {
        grid[i][j] = '2';
        for (int k = 0; k < 4; k++) {
            int ni = i + move[k];
            int nj = j + move[k + 1];
            if (ni >= 0 && ni < grid.length && nj >= 0 && nj < grid[0].length && grid[ni][nj] == '1') {
                infect(grid, ni, nj);
            }
        }
    }
}
