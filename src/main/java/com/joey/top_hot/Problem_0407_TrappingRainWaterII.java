package com.joey.top_hot;

import java.util.PriorityQueue;

// 407. 二维接雨水
// 给你一个 m * n 的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度
// 请计算图中形状最多能接多少体积的雨水。
// 测试链接 : https://leetcode.cn/problems/trapping-rain-water-ii/
public class Problem_0407_TrappingRainWaterII {

	//时间复杂度：O(n*m*log(n*m))
	public static int trapRainWater(int[][] height) {
		int[] move = new int[] { -1, 0, 1, 0, -1 };
		int n = height.length;
		int m = height[0].length;
		// int[]中存放
		// 0 : 行
		// 1 : 列
		// 2 : 水线
		PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[2] - b[2]); //根据水线高度组织的小根堆
		boolean[][] visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
					// 一圈的边界，先进小跟堆，visited标记为true
					heap.add(new int[] { i, j, height[i][j] });
					visited[i][j] = true;
				} else {
					visited[i][j] = false;
				}
			}
		}
		int ans = 0;
		while (!heap.isEmpty()) {
			//每次都弹出水线最薄弱的点。弹出的时候就结算答案
			int[] record = heap.poll();
			int r = record[0];
			int c = record[1];
			int w = record[2];
			ans += w - height[r][c]; //不可能减出负数
			//上下左右四个方向
			for (int i = 0, nr, nc; i < 4; i++) {
				nr = r + move[i];
				nc = c + move[i + 1];
				//不越界并且没有进过小根堆
				if (nr >= 0 && nr < n && nc >= 0 && nc < m && !visited[nr][nc]) {
					//水线：触发我进堆的那个水线，和我自己的高度取最大值，就是我的水线
					heap.add(new int[] { nr, nc, Math.max(height[nr][nc], w) });
					visited[nr][nc] = true;
				}
			}
		}
		return ans;
	}

}
