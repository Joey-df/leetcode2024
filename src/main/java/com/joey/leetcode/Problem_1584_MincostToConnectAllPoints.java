package com.joey.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pei.liu
 */
public class Problem_1584_MincostToConnectAllPoints {

    static class UnionFind {
        int[] father;

        public UnionFind(int n) {
            father = new int[n];
            for (int i = 0; i < n; i++) {
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


    //kruskal
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        UnionFind uf = new UnionFind(n);
        List<int[]> edges = new ArrayList<>();
        //先算出任意两个点之间的距离，保证点之前两两连接
        for (int i = 0; i < n - 1; i++) {
            int[] from = points[i];
            for (int j = i + 1; j < n; j++) {
                int[] to = points[j];
                //i j都是点的编号
                edges.add(new int[]{i, j, Math.abs(from[0] - to[0]) + Math.abs(from[1] - to[1])});
            }
        }
        edges.sort((o1, o2) -> o1[2] - o2[2]);
        int ans = 0;
        for (int i = 0; i < edges.size(); i++) {
            int[] curr = edges.get(i);
            int from = curr[0]; //from to都是点的编号
            int to = curr[1];
            int cost = curr[2];
            if (uf.isSameSet(from, to)) {
                continue;
            }
            uf.union(from, to);
            ans += cost;
        }
        return ans;
    }

}
