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

    public static int[] move = {-1, 0, 1, 0, -1};
    public static int key = 0;
    //0：行
    //1：列
    //2：收集钥匙的状态
    public static LinkedList<int[]> q = new LinkedList<>();
    public static boolean[][][] visited;

    public static int shortestPathAllKeys(String[] grid) {
        char[][] matrix = build(grid);
        //格子中的每个点不能作为图中的点，加上钥匙的状态才能作为图中的点
        //key: 钥匙的个数
        int n = matrix.length;
        int m = matrix[0].length;
        //1个钥匙：2种状态  0 1
        //2个钥匙：4种状态  00 01 10 11
        //3个钥匙：8种状态  000 001 010 011 100 101 110 111
        //所以是2^key

        int level = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();
                int x = curr[0];
                int y = curr[1];
                int s = curr[2];

                for (int k = 0; k < 4; k++) {
                    int nx = x + move[k];
                    int ny = y + move[k + 1];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m || matrix[nx][ny] == '#') {
                        continue; //越界或者是障碍，继续
                    }
                    //没越界并且不是障碍
                    //如果是大写，但是没对应钥匙
                    if (matrix[nx][ny] >= 'A' && matrix[nx][ny] <= 'F' && ((1 << (matrix[nx][ny] - 'A')) & s) == 0) {
                        continue;
                    }
                    //可以扩
                    int ns = s;
                    if (matrix[nx][ny] >= 'a' && matrix[nx][ny] <= 'f') {
                        ns |= (1 << (matrix[nx][ny] - 'a'));
                    }
                    if (Integer.bitCount(ns) == key) {
                        return level;
                    }
                    if (!visited[nx][ny][ns]) {
                        visited[nx][ny][ns] = true;
                        q.offer(new int[]{nx, ny, ns});
                    }
                }
            }
            level++;
        }
        return -1;
    }

    public static char[][] build(String[] grid) {
        q.clear();
        key = 0;
        int n = grid.length;
        int m = grid[0].length();
        int si = -1;
        int sj = -1;
        char[][] matrix = new char[n][m];
        for (int i = 0; i < n; i++) {
            char[] line = grid[i].toCharArray();
            for (int j = 0; j < m; j++) {
                matrix[i][j] = line[j];
                if (line[j] >= 'a' && line[j] <= 'f') {
                    key++;
                }
                if (line[j] == '@') {
                    si = i;
                    sj = j;
                }
            }
        }
        visited = new boolean[n][m][1 << key];
        visited[si][sj][0] = true;
        q.offer(new int[]{si, sj, 0});
        return matrix;
    }

    public static void main(String[] args) {
        String[] grid = {"@.a..", "###.#", "b.A.B"};
        System.out.println(shortestPathAllKeys(grid));
    }
}
