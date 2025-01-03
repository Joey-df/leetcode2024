package com.joey.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pei.liu
 */
//797. 所有可能的路径
//给你一个有 n 个节点的 有向无环图（DAG），请你找出所有从节点 0 到节点 n-1 的路径并输出（不要求按特定顺序）
//graph[i] 是一个从节点 i 可以访问的所有节点的列表（即从节点 i 到节点 graph[i][j]存在一条有向边）。
//提示：
//
//n == graph.length
//2 <= n <= 15
//0 <= graph[i][j] < n
//graph[i][j] != i（即不存在自环）
//graph[i] 中的所有元素 互不相同
//保证输入为 有向无环图（DAG）
public class Problem_0797_AllPathsFromSourceToTarget {

    //graph就已经表示图
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        int n = graph.length; //点的个数
        List<List<Integer>> ans = new ArrayList<>();
        fun(0, new ArrayList<>(), ans, graph, n - 1);
        return ans;
    }

    //当前来到i，之前沿途收集到的路径存在path中
    //ans收集答案，遇到终点收集答案
    public void fun(int i, List<Integer> path, List<List<Integer>> ans, int[][] graph, int end) {
        path.add(i);
        //收集答案的时机: 遇到叶子结点
        if (i == end) {
            ans.add(new ArrayList<>(path));
        } else {
            for (int next : graph[i]) {
                fun(next, path, ans, graph, end);
            }
        }
        path.remove(path.size() - 1); //clear
    }

}
