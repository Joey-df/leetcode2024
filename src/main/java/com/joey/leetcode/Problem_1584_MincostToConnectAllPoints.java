package com.joey.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pei.liu
 */
//1584. 连接所有点的最小费用
//给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。
//连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 val 的绝对值。
//请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。
//提示：
//1 <= points.length <= 1000
//-10^6 <= xi, yi <= 10^6
//所有点 (xi, yi) 两两不同。
public class Problem_1584_MincostToConnectAllPoints {
    //题目给的数据量，点的数据量10^3以内
    //所以先遍历求出任意两点间的曼哈顿距离O(n^2)
    //然后使用kruskal求出最小生成树，也就求出了连接所有点的最小费用

    public static int n; //点的数量
    public static int[] father;

    public static void build() {
        father = new int[n];
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
    }

    public static int find(int i) {
        if (father[i] != i) {
            father[i] = find(father[i]);
        }
        return father[i];
    }

    public static boolean isSameSet(int x, int y) {
        return find(x) == find(y);
    }

    public void union(int x, int y) {
        if (isSameSet(x, y)) {
            return;
        }
        int fx = find(x);
        int fy = find(y);
        father[fx] = fy; //x挂到y下
    }

    public int minCostConnectPoints(int[][] points) {
        int len = points.length;
        n = len;
        build();
        List<int[]> dis = new ArrayList<>();
        for (int i = 0; i < len - 1; i++) {
            int x1 = points[i][0];
            int y1 = points[i][1];
            for (int j = i + 1; j < len; j++) {
                int x2 = points[j][0];
                int y2 = points[j][1];
                dis.add(new int[]{i, j, Math.abs(x1 - x2) + Math.abs(y1 - y2)});
            }
        }
        dis.sort((a, b) -> a[2] - b[2]); //根据费用排序
        int ans = 0;
        int cnt = 0;
        for (int i = 0; i < dis.size(); i++) {
            if (cnt == n - 1) { //n个点两两联通必有n-1条边
                break;
            }
            int[] curr = dis.get(i);
            int x = curr[0];
            int y = curr[1];
            int cost = curr[2];
            if (!isSameSet(x, y)) {
                ans += cost;
                union(x, y);
                cnt++;
            }
        }
        return ans;
    }

}
