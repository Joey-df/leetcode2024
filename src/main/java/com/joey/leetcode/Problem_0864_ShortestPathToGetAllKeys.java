package com.joey.leetcode;

import com.joey.od.s200.爱吃蟠桃的孙悟空;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author pei.liu
 */
//864. 获取所有钥匙的最短路径
//给定一个二维网格 grid ，其中：
//'.' 代表一个空房间
//'#' 代表一堵墙
//'@' 是起点
//小写字母代表钥匙
//大写字母代表锁
//我们从起点开始出发，一次移动是指向四个基本方向之一行走一个单位空间。我们不能在网格外面行走，也无法穿过一堵墙。
//如果途经一个钥匙，我们就把它捡起来。除非我们手里有对应的钥匙，否则无法通过锁。
//假设 k 为 钥匙/锁 的个数，且满足 1 <= k <= 6，字母表中的前 k 个字母在网格中都有自己对应的一个小写和一个大写字母。
//换言之，每个锁有唯一对应的钥匙，每个钥匙也有唯一对应的锁。另外，代表钥匙和锁的字母互为大小写并按字母顺序排列。
//返回获取所有钥匙所需要的移动的最少次数。如果无法获取所有钥匙，返回 -1 。
public class Problem_0864_ShortestPathToGetAllKeys {

    //分层图最短路问题
    //宽度优先遍历
    //找到所有钥匙
    //钥匙和锁一一对应，钥匙的数量在6以内，使用钥匙的数量进行扩点
    public static int[] move = {-1, 0, 1, 0, -1};

    public int shortestPathAllKeys(String[] grid) {
        char[][] matrix = build(grid);
        int n = matrix.length;
        int m = matrix[0].length;
        //统计钥匙的数量
        int keys = 0;
        int starti = -1, startj = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] >= 'a' && matrix[i][j] <= 'f') {
                    keys++;
                }
                if (matrix[i][j] == '@') {
                    starti = i;
                    startj = j;
                }
            }
        }
        //0：所在的行
        //1：所在的列
        //2：收集钥匙的状态
        Queue<int[]> q = new LinkedList<>();
        boolean[][][] visited = new boolean[n][m][1 << keys];
        q.offer(new int[]{starti, startj, 0});
        visited[starti][startj][0] = true;
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                int x = cur[0];
                int y = cur[1];
                int s = cur[2];
                if (Integer.bitCount(s) == keys) {
                    return level;
                }
                for (int j = 0; j < 4; j++) {
                    int nx = x + move[j];
                    int ny = y + move[j + 1];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny][s] || matrix[nx][ny] == '#') {
                        //越界了，或者访问过了，或者是墙
                        continue;
                    }
                    if (matrix[nx][ny] >= 'A' && matrix[nx][ny] <= 'F' && ((s & (1 << (matrix[nx][ny] - 'A'))) == 0)) {
                        //下一个格子是一把锁，但是没有对应的钥匙
                        continue;
                    }
                    int ns = s; //下一个格子钥匙的状态
                    if (matrix[nx][ny] >= 'a' && matrix[nx][ny] <= 'f') {
                        ns |= (1 << (matrix[nx][ny] - 'a')); //捡钥匙
                    }
                    q.offer(new int[]{nx, ny, ns});
                    visited[nx][ny][ns] = true;
                }
            }
            level++;
        }
        return -1;
    }

    public static char[][] build(String[] grid) {
        int n = grid.length;
        int m = grid[0].length();
        char[][] matrix = new char[n][m];
        for (int i = 0; i < n; i++) {
            matrix[i] = grid[i].toCharArray();
        }
        return matrix;
    }

}
