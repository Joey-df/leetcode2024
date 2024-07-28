package com.joey.leetcode;

import java.util.Arrays;

/**
 * @author pei.liu
 */
//1168. 水资源分配优化
//村里面一共有 n 栋房子。我们希望通过建造水井和铺设管道来为所有房子供水。
//对于每个房子 i，我们有两种可选的供水方案：一种是直接在房子内建造水井，成本为 wells[i - 1] （注意 -1 ，因为 索引从0开始 ）；
//另一种是从另一口井铺设管道引水，数组 pipes 给出了在房子间铺设管道的成本，
//其中每个 pipes[j] = [house1j, house2j, costj] 代表用管道将 house1j 和 house2j连接在一起的成本。连接是双向的。
//请返回 为所有房子都供水的最低总成本 。
public class Problem_1168_OptimizeWaterDistributionInAVillage {

    //分析，房子编号1~n,每栋房子单独打井有一个费用，存在well[]里
    //然后有一个房子之间铺设管道的成本，放在pipes[]里，相当于点之间的边，点的编号取值在[1,n]之间
    //现在核心问题变为如何把每栋房子单独打井的成本弄成边，就可以使用kruskal算法求解最小生成树
    //进而求解出最低成本
    //因为房子编号为1～n，可以把0最为虚拟水源，0带i的边代表i单独打井的成本
    //做一下这个转化
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        //wells[]长度一定是n
        //建立虚拟水源到每栋房子之间的边
        int m = pipes.length;
        int[][] edges = new int[m + n][3];
        for (int i = 0; i < n; i++) {
            edges[i] = new int[]{0, i + 1, wells[i]};//0号水源到房子i+1编号的房子之间的边，打井的成本是wells[i]
        }
        for (int i = 0; i < m; i++) {
            edges[n + i] = pipes[i];
        }
        UnionFind uf = new UnionFind(n);
        int ans = 0;
        int cnt = 0;
        //至此得到了所有的边
        Arrays.sort(edges, 0, n + m, (o1, o2) -> o1[2] - o2[2]);
        for (int i = 0; i < n + m; i++) {
            int[] curr = edges[i];
            int from = curr[0];
            int to = curr[1];
            int cost = curr[2];
            if (uf.isSeamSet(from, to)) {
                continue;
            }
            uf.union(from, to);
            ans += cost;
            cnt++;
        }
        return cnt == n ? ans : -1; //n个房子+0虚拟水源，一共n+1个点，一定存在n条边
    }

    static class UnionFind {
        int[] father;

        public UnionFind(int n) {
            father = new int[n + 1];
            for (int i = 0; i < n + 1; i++) { //0代表虚拟水源
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

        public boolean isSeamSet(int x, int y) {
            return find(x) == find(y);
        }
    }

}
