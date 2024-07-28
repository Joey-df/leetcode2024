package com.joey.luogu;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author pei.liu
 */
// Kruskal算法模版（洛谷）
// 静态空间实现
// 测试链接 : https://www.luogu.com.cn/problem/P3366
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下所有代码，把主类名改成Main，可以直接通过
public class Problem_P3366_Kruskal {

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
            if (fx == fy) return; //不需要合并了，已经是一个了
            father[fx] = fy;
        }
    }

    public static UnionFind uf;
    public static int[][] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int N = (int) in.nval;
            uf = new UnionFind(N);
            in.nextToken();
            int M = (int) in.nval;
            edges = new int[M][3];
            for (int i = 0; i < M; i++) {
                in.nextToken();
                int a = (int) in.nval;
                in.nextToken();
                int b = (int) in.nval;
                in.nextToken();
                int w = (int) in.nval;
                edges[i] = new int[]{a, b, w};
            }
            //先对变进行排序
            Arrays.sort(edges, 0, M, (o1, o2) -> o1[2] - o2[2]);
            int ans = 0;
            int cnt = 0;
            for (int i = 0; i < M; i++) {
                if (cnt == N - 1) {
                    break;
                }
                int[] curr = edges[i];
                int from = curr[0];
                int to = curr[1];
                int cost = curr[2];
                if (!uf.isSameSet(from, to)) {
                    ans += cost;
                    uf.union(from, to);
                    cnt++;
                }
            }
            out.println(cnt == N - 1 ? ans : "orz");
        }
        out.flush();
        br.close();
        out.close();
    }
}
