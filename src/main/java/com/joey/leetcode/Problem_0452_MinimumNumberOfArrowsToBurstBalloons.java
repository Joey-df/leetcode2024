package com.joey.leetcode;

import java.util.Arrays;

/**
 * @author pei.liu
 */
public class Problem_0452_MinimumNumberOfArrowsToBurstBalloons {

    //先按s排序
    //遍历每个区间，求交集
    //如果当前区间与当前交集能产生交集，更新交集，不需要增加箭，否则新产生一个交集，增加箭继续
    public static int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (a, b) -> a[0] - b[0]);
        int n = points.length;
        int[] curr = points[0];
        int ans = 1;
        for (int i = 1; i < n; i++) {
            int[] point = points[i];
            int s = point[0];
            int e = point[1];
            if (s >= curr[0] && s <= curr[1]) {
                curr[0] = s;
                curr[1] = Math.min(curr[1], e);
            } else {
                curr = point;
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] points = new int[][]{
                {10, 16},
                {2, 8},
                {1, 6},
                {7, 12}
        };
        System.out.println(findMinArrowShots(points));
    }
}
