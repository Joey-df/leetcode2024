package com.joey.od.s200;

//题目描述
//现需要在某城市进行5G网络建设，已经选取N个地点设置5G基站，编号固定为1到N，
//接下来需要各个基站之间使用光纤进行连接以确保基站能互联互通，不同基站之间假设光纤的成本各不相同，且有些节点之间已经存在光纤相连。
//请你设计算法，计算出能联通这些基站的最小成本是多少。
//注意：基站的联通具有传递性，比如基站A与基站B架设了光纤，基站B与基站C也架设了光纤，则基站A与基站C视为可以互相联通。
//输入描述
//第一行输入表示基站的个数N，其中：
//0 < N ≤ 20
//第二行输入表示具备光纤直连条件的基站对的数目M，其中：
//0 < M < N * (N - 1) / 2
//从第三行开始连续输入M行数据，格式为
//X Y Z P
//其中：
//X，Y 表示基站的编号
//0 < X ≤ N
//0 < Y ≤ N
//X ≠ Y
//Z 表示在 X、Y之间架设光纤的成本
//0 < Z < 100
//P 表示是否已存在光纤连接，0 表示未连接，1表示已连接
//输出描述
//如果给定条件，可以建设成功互联互通的5G网络，则输出最小的建设成本
//如果给定条件，无法建设成功互联互通的5G网络，则输出 -1
//用例1
//输入
//3
//3
//1 2 3 0
//1 3 1 0
//2 3 5 0
//输出
//4
//说明
//只需要在1，2以及1，3基站之间铺设光纤，其成本为3+1=4
//用例2
//输入
//3
//1
//1 2 5 0
//输出
//-1
//说明
//3基站无法与其他基站连接，输出-1
//用例3
//输入
//3
//3
//1 2 3 0
//1 3 1 0
//2 3 5 1
//输出
//1
//说明
//2，3基站已有光纤相连，只要在1，3基站之间铺设光纤，其成本为1
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class 五G网络建设 {

    static class UnionFind {
        int[] father;

        public UnionFind(int n) {
            father = new int[n + 1];
            for (int i = 0; i <= n; i++) {
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
    }

    public static UnionFind uf;
    public static int[][] edges;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        uf = new UnionFind(N);
        int M = sc.nextInt(); //M条变
        edges = new int[M][3];
        int ans = 0;
        int cnt = 0;
        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int w = sc.nextInt();
            int p = sc.nextInt();
            if (p == 1) {
                cnt++;
                uf.union(a, b);
            }
            edges[i] = new int[]{a, b, w};
        }

        Arrays.sort(edges, 0, M, (o1, o2) -> o1[2] - o2[2]);

        for (int i = 0; i < M; i++) {
            int[] curr = edges[i];
            int from = curr[0];
            int to = curr[1];
            int cost = curr[2];
            if (!uf.isSameSet(from, to)) {
                ans += cost;
                cnt++;
                uf.union(from, to);
            }
        }
        System.out.println(cnt == N - 1 ? ans : -1);
        sc.close();
    }
}

