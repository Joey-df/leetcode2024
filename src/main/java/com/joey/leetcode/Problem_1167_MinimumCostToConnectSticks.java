package com.joey.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author pei.liu
 */
//1167. 连接木棍的最低费用
//你有一些长度为正整数的木棍。这些长度以数组 sticks 的形式给出， sticks[i] 是第 i 个木棍的长度。
//你可以通过支付 x + y 的成本将任意两个长度为 x 和 y 的木棍连接成一个木棍。你必须连接所有的木棍，直到剩下一个木棍。
//返回以这种方式将所有给定的木棍连接成一个木棍的 最小成本 。
public class Problem_1167_MinimumCostToConnectSticks {
    public static PriorityQueue<Integer> heap = new PriorityQueue<>();

    //这题使用小根堆，太明显了
    public int connectSticks(int[] sticks) {
        if (sticks == null || sticks.length == 0) return 0;
        int n = sticks.length;
        Arrays.sort(sticks, 0, n);
        heap.clear();
        for (int i = 0; i < n; i++) {
            heap.offer(sticks[i]);
        }
        int ans = 0;
        while (heap.size() >= 2) {
            int a = heap.poll();
            int b = heap.poll();
            ans += a + b;
            heap.offer(a + b);
        }
        //最后heap中必剩下一个数
        return ans;
    }

}
