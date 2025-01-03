package com.joey.leetcode;

/**
 * @author pei.liu
 */
public class LCS03_LargestArea {

    //如果dfs到边缘或者遇到0，则说明该主题空间与走廊相连，就让该主题空间的面积设置为最小值，从而排除该主题空间。
    public int INF = Integer.MIN_VALUE;
    public int n, m;
    public int[] move = {-1, 0, 1, 0, -1};

    public int largestArea(String[] grid) {
        n = grid.length;
        m = grid[0].length();
        boolean[][] vis = new boolean[n][m];
        int ans = 0;
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                if (grid[i].charAt(j) != '0') {
                    long area = dfs(grid, i, j, vis);
                    if (area > 0) {
                        ans = Math.max(ans, (int) area);
                    }
                }
            }
        }
        return ans;
    }

    public long dfs(String[] grid, int x, int y, boolean[][] vis) {
        vis[x][y] = true;
        if (x == 0 || x == n - 1 || y == 0 || y == m - 1) {
            return INF;
        }
        long area = 1;
        for (int k = 0; k < 4; k++) {
            int nx = x + move[k];
            int ny = y + move[k + 1];
            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                if (grid[nx].charAt(ny) == '0') {
                    area = INF;
                } else if (grid[nx].charAt(ny) == grid[x].charAt(y) && !vis[nx][ny]) {
                    area += dfs(grid, nx, ny, vis);
                }
            }
        }
        return area;
    }

}
