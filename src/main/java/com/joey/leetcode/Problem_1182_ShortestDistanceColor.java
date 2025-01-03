package com.joey.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author pei.liu
 */
//1182. 与目标颜色间的最短距离
//给你一个数组 colors，里面有  1、2、 3 三种颜色。
//我们需要在 colors 上进行一些查询操作 queries，其中每个待查项都由两个整数 i 和 c 组成。
//现在请你帮忙设计一个算法，查找从索引 i 到具有目标颜色 c 的元素之间的最短距离。
//如果不存在解决方案，请返回 -1。
//
//示例 1：
//输入：colors = [1,1,2,1,3,2,2,3,3], queries = [[1,3],[2,2],[6,1]]
//输出：[3,0,3]
//解释：
//距离索引 1 最近的颜色 3 位于索引 4（距离为 3）。
//距离索引 2 最近的颜色 2 就是它自己（距离为 0）。
//距离索引 6 最近的颜色 1 位于索引 3（距离为 3）。
//示例 2：
//输入：colors = [1,2], queries = [[0,3]]
//输出：[-1]
//解释：colors 中没有颜色 3。
//
//提示：
//1 <= colors.length <= 5*10^4
//1 <= colors[i] <= 3
//1 <= queries.length <= 5*10^4
//queries[i].length == 2
//0 <= queries[i][0] < colors.length
//1 <= queries[i][1] <= 3
public class Problem_1182_ShortestDistanceColor {

    public static HashMap<Integer, List<Integer>> store = new HashMap<>();

    public static List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        store.clear();
        int n = colors.length;
        for (int i = 0; i < n; i++) {
            store.computeIfAbsent(colors[i], k -> new ArrayList<>())
                    .add(i);
        }
        int m = queries.length;
        List<Integer> ans = new ArrayList<>();
        for (int[] query : queries) {
            int aim = query[0];
            int c = query[1];
            if (!store.containsKey(c)) {
                ans.add(-1);
                continue;
            }
            List<Integer> arr = store.get(c); // 4 7 8
            int left = left(arr, aim);        // 1
            int right = right(arr, aim);
            int curr = Integer.MAX_VALUE;
            if (left < arr.size()) curr = Math.min(curr, Math.abs(arr.get(left) - aim));
            if (right > -1) curr = Math.min(curr, Math.abs(arr.get(right) - aim));
            ans.add(curr);
        }
        return ans;
    }

    public static int left(List<Integer> arr, int x) {
        int l = 0;
        int r = arr.size() - 1;
        int hit = arr.size();
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (arr.get(m) >= x) {
                hit = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return hit;
    }

    public static int right(List<Integer> arr, int x) {
        int l = 0;
        int r = arr.size() - 1;
        int hit = -1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (arr.get(m) <= x) {
                hit = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return hit;
    }


    public static void main(String[] args) {
        int[] colors = {1, 1, 2, 1, 3, 2, 2, 3, 3};
        int[][] queries = {
                {1, 3},
                {2, 2},
                {6, 1}
        };
        System.out.println(shortestDistanceColor(colors, queries));
    }
}
