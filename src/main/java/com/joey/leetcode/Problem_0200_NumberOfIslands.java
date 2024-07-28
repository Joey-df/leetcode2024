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

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                //每次遇到1就计算一个岛，然后去递归的把其上下左右所有的1改为2
                if (grid[i][j] == '1') {
                    ans++;
                    inflect(grid, i, j);
                }
            }
        }
        return ans;
    }

    //感染函数
    private void inflect(char[][] grid, int i, int j) {
        //越界 或者 值 != 1，直接返回
        if (i < 0 || i == grid.length || j < 0 || j == grid[0].length || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '2';
        //去感染其上下左右
        inflect(grid, i - 1, j);
        inflect(grid, i + 1, j);
        inflect(grid, i, j - 1);
        inflect(grid, i, j + 1);
    }
}
