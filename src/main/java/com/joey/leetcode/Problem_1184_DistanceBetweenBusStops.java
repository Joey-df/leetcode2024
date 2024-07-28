package com.joey.leetcode;

/**
 * @author pei.liu
 */
//1184. 公交站间的距离
public class Problem_1184_DistanceBetweenBusStops {

    public int distanceBetweenBusStops(int[] distance, int s, int e) {
        if (s > e) {
            int t = s;
            s = e;
            e = t;
        }
        int ans1 = 0;
        int ans2 = 0;
        for (int i = 0; i < distance.length; i++) {
            if (i >= s && i < e) {
                ans1 += distance[i];
            } else {
                ans2 += distance[i];
            }
        }
        return Math.min(ans1, ans2);
    }

}
