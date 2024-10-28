package com.joey.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author pei.liu
 */
//2249. 统计圆内格点数目
//给你一个二维整数数组 circles ，其中 circles[i] = [xi, yi, ri] 表示网格上圆心为 (xi, yi) 且半径为 ri 的第 i 个圆，
//返回出现在 至少一个 圆内的 格点数目 。
//注意：
//格点 是指整数坐标对应的点。
//圆周上的点 也被视为出现在圆内的点。
//提示：
//
//1 <= circles.length <= 200
//circles[i].length == 3
//1 <= xi, yi <= 100
//1 <= ri <= min(xi, yi)
public class Problem_2249_CountLatticePointsInsideACircle {

    public static Map<Integer, Set<Integer>> visited = new HashMap<>();

    public static int f(int[] circle) {
        int ans = 0;
        int x = circle[0];
        int y = circle[1];
        int r = circle[2];
        int left = x - r;
        int right = x + r;
        int down = y - r;
        int up = y + r;
        for (int i = left; i <= right; i++) {
            for (int j = down; j <= up; j++) {
                double dis = Math.sqrt(Math.pow(x - i, 2) + Math.pow(y - j, 2));
                if (dis <= r && (!visited.containsKey(i) || !visited.get(i).contains(j))) {
                    visited.computeIfAbsent(i, k -> new HashSet<>()).add(j);
                    ans += 1;
                }
            }
        }
        return ans;
    }

    //对于每个圆，可以求出左右上下边界，即一个正方形区域，在这个区域中枚举所有格点
    //使用一个去重机制，收集数量最后返回
    public static int countLatticePoints(int[][] circles) {
        visited.clear();
        int ans = 0;
        for (int[] circle : circles) {
            ans += f(circle);
        }
        return ans;
    }

}
