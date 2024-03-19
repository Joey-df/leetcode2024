package com.joey.top_hot;

/**
 * 200. 岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * 示例 1：
 * 输入：grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * 输出：1
 *
 * 示例 2：
 * 输入：grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * 输出：3
 */
public class Problem_0200_NumberOfIslands {

    public static int numIslands(int[][] grid) {
        int M = grid.length, N = grid[0].length;
        int ans = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 1) {
                    ans++; //每次遇到1就计算一个岛，然后去递归的把其上下左右所有的1改为2
                    infect(i, j, grid);
                }
            }
        }
        return ans;
    }

    //感染函数
    private static void infect(int x, int y, int[][] grid) {
        int M = grid.length, N = grid[0].length;
        if (x < 0 || x >= M || y < 0 || y >= N || grid[x][y] != 1) {
            return;
        }
        grid[x][y] = 2;
        //去感染其上下左右
        infect(x - 1, y, grid);
        infect(x + 1, y, grid);
        infect(x, y - 1, grid);
        infect(x, y + 1, grid);
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                new int[]{1, 1, 0, 0, 0},
                new int[]{1, 1, 0, 0, 0},
                new int[]{0, 1, 1, 0, 0},
                new int[]{0, 0, 0, 1, 1}};
        System.out.println(numIslands(grid));
    }

}
