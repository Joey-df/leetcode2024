package com.joey.leetcode;

/**
 * @author pei.liu
 */
//1109. 航班预订统计
//这里有 n 个航班，它们分别从 1 到 n 进行编号。
//有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi]
//意味着在从 firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。
//请你返回一个长度为 n 的数组 answer，里面的元素是每个航班预定的座位总数。
//一维差分
public class Problem_1109_CorporateFlightBookings {

    //n个航班，1～n
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] diff = new int[n + 2]; //为啥是+2：n个航班1-n，0位置弃而不用，差分数组需要i+1位置（新增最后一个位置少了边界判断）
        //构建差分数组
        for (int[] book : bookings) {
            diff[book[0]] += book[2];
            diff[book[1] + 1] -= book[2];
        }
        //构建累加和
        for (int i = 1; i < n + 1; i++) {
            diff[i] += diff[i - 1];
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = diff[i + 1];
        }
        return ans;
    }

}
