package com.joey.leetcode;

//134. 加油站
//在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
//你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。
//你从其中的一个加油站出发，开始时油箱为空。
//给定两个整数数组 gas 和 cost ，如果你可以按顺序绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。
//如果存在解，则 保证 它是 唯一 的。
public class Problem_0134_GasStation {

    public int canCompleteCircuit1(int[] gas, int[] cost) {
        if (gas == null || cost == null || gas.length != cost.length) return -1;
        int gasSum = 0;
        int costSum = 0;
        int tank = 0; //纯能值
        int start = 0;
        for (int i = 0; i < gas.length; i++) {
            gasSum += gas[i];
            costSum += cost[i];
            tank += gas[i] - cost[i];
            if (tank < 0) {
                start = i + 1; //纯能值如果小于0说明前面的位置不能跑完一周，尝试下一个出发点
                tank = 0;
            }
        }
        return gasSum < costSum ? -1 : start;
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        //扩一倍：环形问题常见处理方法
        int r = 0;
        int windowSum = 0;
        for (int i = 0; i < n; i++) { //枚举每一个出发点
            //每个i位置作为出发点，转一圈的中点位于[n,2*n-1]，所以是 r<n*2
            //r右扩的条件：
            //1.r不越界
            //2.累加和没有到0以下
            //3.还没走完一圈 r-i<n
            //同时满足
            while (r < n * 2 && windowSum >= 0 && r - i < n) {
                int curr = gas[r % n] - cost[r % n]; //当前r位置的剩余
                windowSum += curr;
                r++;
            }
            //while出来时三种情况
            //1.r越界了，即已经枚举到最后一个出发点了
            //2.windowSum < 0了
            //3.r-i == n了
            //当前i位置作为出发点时，剩余的油量
            int startRest = gas[i % n] - cost[i % n];
            //同时满足以下条件，说明i是第一个良好出发点
            if (startRest >= 0 && windowSum >= 0 && r - i == n) {
                return i;
            }
            //i即将++
            windowSum -= startRest;
        }
        return -1; //没有良好出发点
    }

}
