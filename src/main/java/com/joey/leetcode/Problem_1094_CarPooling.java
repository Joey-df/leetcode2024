package com.joey.leetcode;

/**
 * @author pei.liu
 */
//1094. 拼车
//车上最初有 capacity 个空座位。车 只能 向一个方向行驶（也就是说，不允许掉头或改变方向）
//给定整数 capacity 和一个数组 trips ,
//trip[i] = [numPassengersi, fromi, toi] 表示第 i 次旅行有 numPassengersi 乘客，接他们和放他们的位置分别是 fromi 和 toi 。
//这些位置是从汽车的初始位置向东的公里数。
//当且仅当你可以在所有给定的行程中接送所有乘客时，返回 true，否则请返回 false。
//
//示例 1：
//
//输入：trips = [[2,1,5],[3,3,7]], capacity = 4
//输出：false
//示例 2：
//输入：trips = [[2,1,5],[3,3,7]], capacity = 5
//输出：true
//
//提示：
//1 <= trips.length <= 1000
//trips[i].length == 3
//1 <= numPassengersi <= 100
//0 <= fromi < toi <= 1000
//1 <= capacity <= 10^5
public class Problem_1094_CarPooling {

    public static boolean carPooling(int[][] trips, int capacity) {
        int minL = Integer.MAX_VALUE;
        int maxR = Integer.MIN_VALUE;
        for (int[] trip : trips) {
            minL = Math.min(minL, trip[1]);
            maxR = Math.max(maxR, trip[2]);
        }
        int[] diff = new int[maxR + 1];
        for (int[] trip : trips) {
            diff[trip[1]] += trip[0];
            diff[trip[2]] -= trip[0];
        }
        int sum = 0;
        for (int i = 0; i < diff.length; i++) {
            sum += i > 0 ? diff[i - 1] : diff[i];
            if (sum > capacity) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] trips={
                {2,1,5},{3,5,7}
        };
        int capacity=3;
        System.out.println(carPooling(trips, capacity));
    }

}
