package com.joey.leetcode;

/**
 * @author pei.liu
 */
//547. 省份数量
//有 n 个城市，其中一些彼此相连，另一些没有相连。
//如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
//省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
//给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，
//而 isConnected[i][j] = 0 表示二者不直接相连。
//返回矩阵中 省份 的数量。
public class Problem_0547_NumberOfProvinces {

    static int[] father;
    static int cnt;

    public static void build(int n) {
        father = new int[n];
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
        cnt = n;
    }

    public static int find(int i) {
        if (i != father[i]) {
            father[i] = find(father[i]);
        }
        return father[i];
    }

    public static void union(int x, int y) {
        int fx = find(x);
        int fy = find(y);
        if (fx != fy) {
            father[fx] = fy;
            cnt--;
        }
    }


    public static int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int m = isConnected[0].length;
        build(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (isConnected[i][j] == 1) {
                    union(i, j);
                }
            }
        }
        return cnt;
    }

}
