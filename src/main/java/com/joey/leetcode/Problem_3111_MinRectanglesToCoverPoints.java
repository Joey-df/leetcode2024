package com.joey.leetcode;

import java.util.Arrays;

/**
 * @author pei.liu
 */
//3111. 覆盖所有点的最少矩形数目
//给你一个二维整数数组 point ，其中 points[i] = [xi, yi] 表示二维平面内的一个点。同时给你一个整数 w 。
//你需要用矩形 覆盖所有 点。
//每个矩形的左下角在某个点 (x1, 0) 处，且右上角在某个点 (x2, y2) 处，
//其中 x1 <= x2 且 y2 >= 0 ，同时对于每个矩形都 必须 满足 x2 - x1 <= w 。
//如果一个点在矩形内或者在边上，我们说这个点被矩形覆盖了。
//请你在确保每个点都 至少 被一个矩形覆盖的前提下，最少 需要多少个矩形。
//注意：一个点可以被多个矩形覆盖。
//数据量
//1 <= points.length <= 10^5
//points[i].length == 2
//0 <= xi == points[i][0] <= 10^9
//0 <= yi == points[i][1] <= 10^9
//0 <= w <= 10^9
//所有点坐标 (xi, yi) 互不相同。
public class Problem_3111_MinRectanglesToCoverPoints {

    //滑动窗口
    //找出点的x坐标，使用滑动窗口解决
    public int minRectanglesToCoverPoints(int[][] points, int w) {
        int n = points.length;
        Arrays.sort(points, (a, b) -> a[0] - b[0]);
        int ans = 0;
        int r = 0;
        for (int l = 0; l < n; ) {
            while (r < n && points[r][0] - points[l][0] <= w) {
                r++;
            }
            //while出来两种情况
            //1 r越界了
            //2 nums[r]-nums[l]>w
            ans++; //l位置开头生成了一个矩形,范围[l,r-1]
            l = r; //l来到r，开始下一个矩形
        }
        return ans;
    }

}
