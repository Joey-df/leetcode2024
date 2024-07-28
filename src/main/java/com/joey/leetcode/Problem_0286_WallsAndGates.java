package com.joey.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author pei.liu
 */
public class Problem_0286_WallsAndGates {

    private static final int EMPTY = Integer.MAX_VALUE;
    private static final int GATE = 0;
    private static final List<int[]> DIRECTIONS = Arrays.asList(
            new int[] { 1,  0}, // 下
            new int[] {-1,  0}, // 上
            new int[] { 0,  1}, // 右
            new int[] { 0, -1}  // 左
    );

    public void wallsAndGates(int[][] rooms) {
        int m = rooms.length;   // 多少行
        if (m == 0) return;
        int n = rooms[0].length;    // 多少列
        Queue<int[]> q = new LinkedList<>();
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                // 把所有的 GATE 入队
                if (rooms[row][col] == GATE) {
                    q.add(new int[] { row, col });
                }
            }
        }
        // 开始宽度优先搜索遍历
        while (!q.isEmpty()) {
            int[] point = q.poll(); // 取出一个点
            int row = point[0]; // 这个点在哪一行
            int col = point[1]; // 这个点在哪一列
            // 下、上、右、左四个方向挨着的坐标进行处理
            for (int[] direction : DIRECTIONS) {
                int r = row + direction[0];
                int c = col + direction[1];
                // 如果超出边界或者它不是房间（EMPTY），则无视它
                if (r < 0 || c < 0 || r >= m || c >= n || rooms[r][c] != EMPTY) {
                    continue;
                }
                rooms[r][c] = rooms[row][col] + 1;  // 如果它是房间，则把它设置为跟门的距离
                q.add(new int[] { r, c });  // 把它塞入队列，等候处理
            }
        }
    }
}
