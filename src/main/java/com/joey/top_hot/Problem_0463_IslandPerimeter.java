package com.joey.top_hot;

//463. 岛屿的周长
//给定一个 row x col 的二维网格地图 grid ，其中：grid[i][j] = 1 表示陆地， grid[i][j] = 0 表示水域。
//网格中的格子 水平和垂直 方向相连（对角线方向不相连）。
//整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
//岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。
//网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
public class Problem_0463_IslandPerimeter {
    //观察图可以发现：
    //陆地的周边为水域，或者为边界时，周长贡献1
    //所以可以用感染方法
    public static int islandPerimeter(int[][] grid) {
        int M = grid.length, N = grid[0].length;
        int ans = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 1) {
                    //有几个岛就调几次
                    ans += infect(i, j, grid, M, N);
                }
            }
        }
        return ans;
    }

    //grid[x][y]为1时连成一片的岛的周长是多少？
    //调用infect函数的前提：grid[x][y]==1
    private static int infect(int x, int y, int[][] grid, int M, int N) {
        //从一个岛屿方格走向一个非岛屿方格，就将周长加 1
        if (x == -1 || x == M || y == -1 || y == N || grid[x][y] == 0) {//陆地的周边为水域，或者为边界时，周长贡献1
            return 1;
        }
        //走到这里说明[x,y]不越界并且grid[x][y]!=0
        if (grid[x][y] == 2) {
            return 0;
        }
        grid[x][y] = 2;//为了不走回头路
        return infect(x - 1, y, grid, M, N) +
                infect(x + 1, y, grid, M, N) +
                infect(x, y - 1, grid, M, N) +
                infect(x, y + 1, grid, M, N);
    }

}
