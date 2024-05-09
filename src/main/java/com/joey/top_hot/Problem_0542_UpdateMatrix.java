package com.joey.top_hot;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author pei.liu
 */
//542. 01 矩阵
//给定一个由 0 和 1 组成的矩阵 mat ，请输出一个大小相同的矩阵，其中每一个格子是 mat 中对应位置元素到最近的 0 的距离。
//两个相邻元素间的距离为 1 。
//提示：
//m == mat.length
//n == mat[i].length
//1 <= m, n <= 10^4
//1 <= m * n <= 10^4
//mat[i][j] is either 0 or 1.
//mat 中至少有一个 0
public class Problem_0542_UpdateMatrix {


    //时间复杂度：O(n*m)
    //空间复杂度：O(n*m)，因为申请了visited二维数组
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        //控制上下左右四个方向
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int[][] dist = new int[m][n];
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<int[]>();
        // 将所有的 0 添加进初始队列中
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        // 宽度优先遍历
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int i = cur[0], j = cur[1];
            for (int k = 0; k < 4; k++) {
                int nexti = i + dirs[k][0];
                int nextj = j + dirs[k][1];
                if (nexti >= 0 && nexti < m && nextj >= 0 && nextj < n && !visited[nexti][nextj]) {
                    dist[nexti][nextj] = dist[i][j] + 1;
                    queue.offer(new int[]{nexti, nextj});
                    visited[nexti][nextj] = true;
                }
            }
        }

        return dist;
    }

}
