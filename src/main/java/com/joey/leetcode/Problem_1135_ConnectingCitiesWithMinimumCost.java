package com.joey.leetcode;

import java.util.Arrays;

/**
 * @author pei.liu
 */
//1135. 最低成本连通所有城市
//想象一下你是个城市基建规划者，地图上有 n 座城市，它们按以 1 到 n 的次序编号。
//给你整数 n 和一个数组 conections，其中 connections[i] = [xi, yi, costi] 表示将城市 xi 和城市 yi 连接所要的costi（连接是双向的）。
//返回连接所有城市的最低成本，每对城市之间至少有一条路径。如果无法连接所有 n 个城市，返回 -1
//该 最小成本 应该是所用全部连接成本的总和。
public class Problem_1135_ConnectingCitiesWithMinimumCost {

    static class UnionFind {
        int[] father;

        public UnionFind(int n) {
            father = new int[n + 1];
            for (int i = 0; i <= n; i++) { //0 弃而不用
                father[i] = i;
            }
        }

        public int find(int i) {
            if (father[i] != i) {
                father[i] = find(father[i]);
            }
            return father[i];
        }

        public void union(int x, int y) {
            int fx = find(x);
            int fy = find(y);
            if (fx != fy) {
                father[fx] = fy;
            }
        }

        public boolean isSameSet(int x, int y) {
            return find(x) == find(y);
        }
    }

    public int minimumCost(int n, int[][] connections) {
        UnionFind uf = new UnionFind(n);
        int m = connections.length;
        Arrays.sort(connections, 0, m, (o1, o2) -> o1[2] - o2[2]);
        int ans = 0;
        int cnt = 0; //使用边的数量
        for (int i = 0; i < m; i++) {
            int[] curr = connections[i];
            int from = curr[0];
            int to = curr[1];
            int cost = curr[2];
            if (uf.isSameSet(from, to)) {
                continue;
            }
            uf.union(from, to);
            ans += cost;
            cnt++;
        }
        return cnt == n - 1 ? ans : -1;
    }
}
