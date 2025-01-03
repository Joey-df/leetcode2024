package com.joey.leetcode;

import java.util.*;

/**
 * @author pei.liu
 */
//815. 公交路线
//给你一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶。
//例如，路线 routes[0] = [1, 5, 7] 表示第 0 辆公交车会一直按序列 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... 这样的车站路线行驶。
//现在从 source 车站出发（初始时不在公交车上），要前往 target 车站。 期间仅可乘坐公交车。
//求出 最少乘坐的公交车数量 。如果不可能到达终点车站，返回 -1 。
//示例 1：
//输入：routes = [[1,2,7},[3,6,7]}, source = 1, target = 6
//输出：2
//解释：最优策略是先乘坐第一辆公交车到达车站 7 , 然后换乘第二辆公交车到车站 6 。
//示例 2：
//输入：routes = [[7,12},[4,5,15},[6},[15,19},[9,12,13]}, source = 15, target = 12
//输出：-1
//提示：
//1 <= routes.length <= 500.
//1 <= routes[i].length <= 10^5
//routes[i] 中的所有值 互不相同
//sum(routes[i].length) <= 10^5
//0 <= routes[i][j] < 10^6
//0 <= source, target < 10^6
public class Problem_0815_BusRoutes {

    static Map<Integer, Set<Integer>> point2car = new HashMap<>();
    static Map<Integer, Set<Integer>> car2point = new HashMap<>();
    //公交车之间可以建无向图
    static List<Set<Integer>> graph = new ArrayList<>();

    public static void build(int n) {
        for (int i = 0; i < n; i++) {
            graph.add(new HashSet<>());
        }
        for (int car : car2point.keySet()) {
            Set<Integer> points = car2point.get(car);
            for (int point : points) {
                for (int c : point2car.get(point)) {
                    if (c != car)
                        graph.get(car).add(c);
                }
            }
        }
    }

    //先建图
    //当前点：能到当前点的公交车编号集合
    //公交车编号：当前车能到的车站集合
    //然后通从source定位出公交车集合，枚举从这个集合的每一个点出发，到达target的最短路径
    public static int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        int n = routes.length;
        point2car.clear();
        car2point.clear();
        graph.clear();
        for (int i = 0; i < n; i++) { //i:公交车编号
            int[] points = routes[i];
            for (int point : points) {
                point2car.computeIfAbsent(point, k -> new HashSet<>()).add(i);
                car2point.computeIfAbsent(i, k -> new HashSet<>()).add(point);
            }
        }
        build(n);
        LinkedList<Integer> q = new LinkedList<>();
        if (!point2car.containsKey(source)) {
            return -1;
        }
        Set<Integer> starts = point2car.get(source); //可以从哪些公交车出发
        for (int start : starts) {
            q.offer(start); //可以选择从这些 公交车 出发
        }
        boolean[] visited = new boolean[n];//标记已经走过哪些车站了
        int ans = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            ans++;
            for (int i = 0; i < size; i++) {
                int car = q.poll(); //公交车
                if (car2point.get(car).contains(target)) {
                    return ans;
                }
                if (visited[car]) continue;
                visited[car] = true;
                //当前公交乘通过换乘能到哪些公交车
                for (int next : graph.get(car)) {
                    if (!visited[next]) {
                        q.offer(next);
                    }
                }
            }
        }
        return -1;
    }

}
