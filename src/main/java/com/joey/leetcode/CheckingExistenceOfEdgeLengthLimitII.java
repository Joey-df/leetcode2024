package com.joey.leetcode;

import java.util.Arrays;

/**
 * @author pei.liu
 */
public class CheckingExistenceOfEdgeLengthLimitII {

    //超时
    static class DistanceLimitedPathsExist {

        int[] father;
        int[][] edges;

        public void build(int n, int[][] edgeList) {
            int m = edgeList.length;
            edges = new int[m][3];
            Arrays.sort(edgeList, (o1, o2) -> o1[2] - o2[2]);
            System.arraycopy(edgeList, 0, edges, 0, m);
            father = new int[n];
            for (int i = 0; i < n; i++) {
                father[i] = i;
            }
        }

        public void reset() {
            for (int i = 0; i < father.length; i++) {
                father[i] = i;
            }
        }

        public int find(int i) {
            if (father[i] != i) {
                father[i] = find(father[i]);
            }
            return father[i];
        }

        public boolean isSameSet(int x, int y) {
            return find(x) == find(y);
        }

        public void union(int x, int y) {
            int fx = find(x);
            int fy = find(y);
            if (fx != fy) {
                father[fx] = fy;
            }
        }

        public DistanceLimitedPathsExist(int n, int[][] edgeList) {
            build(n, edgeList);
        }

        public boolean query(int p, int q, int limit) {
            reset();
            for (int[] edge : edges) {
                int from = edge[0];
                int to = edge[1];
                int w = edge[2];
                if (w >= limit) {
                    return isSameSet(p, q);
                }
                if (!isSameSet(from, to)) {
                    union(from, to);
                }
            }
            return isSameSet(p, q);
        }
    }
}
