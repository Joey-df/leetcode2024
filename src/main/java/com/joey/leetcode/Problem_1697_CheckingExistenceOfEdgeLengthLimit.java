package com.joey.leetcode;

import java.util.Arrays;

/**
 * @author pei.liu
 */
//1697. 检查边长度限制的路径是否存在
//给你一个 n 个点组成的无向图边集 edgeList ，其中 edgeList[i] = [ui, vi, disi] 表示点 ui 和点 vi 之间有一条长度为 disi 的边。
//请注意，两个点之间可能有 超过一条边 。
//给你一个查询数组queries ，其中 queries[j] = [pj, qj, limitj] ，
//你的任务是对于每个查询 queries[j] ，判断是否存在从 pj 到 qj 的路径，且这条路径上的每一条边都 严格小于 limitj 。
//请你返回一个 布尔数组 answer ，
//其中 answer.length == queries.length
//当 queries[j] 的查询结果为 true 时， answer 第 j 个值为 true ，否则为 false 。
public class Problem_1697_CheckingExistenceOfEdgeLengthLimit {

    //思路：
    //边跑kruskal，边更新答案
    //在生成最小生成树的过程中填充答案
    //先给queries数组打上一个下标，然后将queries数组根据limitj排序
    //最小生成树生成的过程中，当处理完<=limitj的边时更新queries的某个答案

    int[] father;

    public void build(int n) {
        father = new int[n];
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
    }

    public int find(int i) {
        if (i != father[i]) {
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

    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        build(n);
        int N = queries.length;
        int M = edgeList.length;
        int[][] questions = new int[N][4];
        for (int i = 0; i < N; i++) {
            int pj = queries[i][0], qj = queries[i][1], limitj = queries[i][2];
            questions[i] = new int[]{pj, qj, limitj, i}; //第四维，答案的编号（下标）
        }
        Arrays.sort(questions, (o1, o2) -> o1[2] - o2[2]);
        Arrays.sort(edgeList, (o1, o2) -> o1[2] - o2[2]);
        boolean[] ans = new boolean[N];
        //处理一批边，更新一个答案
        int i = 0, j = 0;
        while (i < N) {
            for (; j < M && edgeList[j][2] < questions[i][2]; j++) {
                int[] edge = edgeList[j];
                int from = edge[0];
                int to = edge[1];
                int w = edge[2];
                if (!isSameSet(from, to)) {
                    union(from, to);
                }
            }
            ans[questions[i][3]] = isSameSet(questions[i][0], questions[i][1]);
            i++;
        }
        return ans;
    }

    //n = 3, edgeList = [[0,1,2],[1,2,4],[2,0,8],[1,0,16]], queries = [[0,1,2],[0,2,5]]
    public static void main(String[] args) {
        Problem_1697_CheckingExistenceOfEdgeLengthLimit obj = new Problem_1697_CheckingExistenceOfEdgeLengthLimit();
        int n = 3;
        int[][] edgeList = new int[][]{
                {0, 1, 2},
                {1, 2, 4},
                {2, 0, 8},
                {1, 0, 16}
        };
        int[][] queries = new int[][]{{0, 1, 2}, {0, 2, 5}};
        obj.distanceLimitedPathsExist(n, edgeList, queries);
    }

}
