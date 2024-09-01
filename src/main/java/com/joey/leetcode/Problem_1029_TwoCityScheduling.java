package com.joey.leetcode;

import java.util.Arrays;

/**
 * @author pei.liu
 */
//1029. 两地调度
//公司计划面试 2n 人。给你一个数组 costs ，其中 costs[i] = [aCosti, bCosti] 。
//第 i 人飞往 a 市的费用为 aCosti ，飞往 b 市的费用为 bCosti 。
//返回将每个人都飞到 a 、b 中某座城市的最低费用，要求每个城市都有 n 人抵达。
public class Problem_1029_TwoCityScheduling {

    //题意：给定长度为2N的数组，每个数组里是一个二维的小数组，两个元素arr[0],arr[1]
    //每次选择只能从小数组中选择一个元素，选N个arr[0]，选N个arr[1],
    //让累加和最小，求最小的累加和
    public int twoCitySchedCost(int[][] costs) {
        //先让所有人都去A城市，得到一个费用sum
        //用一个-a+b的值收集成一个数组，表示减掉A城去B城市，排序取前n/2个
        int n = costs.length;
        int sum = 0;
        int[] arr = new int[n];
        int i = 0;
        for (int[] cost : costs) {
            sum += cost[0];
            arr[i++] = cost[1] - cost[0];
        }
        Arrays.sort(arr);
        for (int j = 0; j < n >> 1; j++) {
            sum += arr[j];
        }
        return sum;
    }

}
