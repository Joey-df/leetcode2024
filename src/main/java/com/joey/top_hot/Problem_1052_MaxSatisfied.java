package com.joey.top_hot;

/**
 * @author pei.liu
 */
//1052. 爱生气的书店老板
//有一个书店老板，他的书店开了 n 分钟。每分钟都有一些顾客进入这家商店。
//给定一个长度为 n 的整数数组 customers ，其中 customers[i] 是在第 i 分钟开始时进入商店的顾客数量，所有这些顾客在第 i 分钟结束后离开。
//在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。
//当书店老板生气时，那一分钟的顾客就会不满意，若老板不生气则顾客是满意的。
//书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 minutes 分钟不生气，但却只能使用一次。
//请你返回 这一天营业下来，最多有多少客户能够感到满意 。
//
//示例 1：
//输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], minutes = 3
//输出：16
//解释：书店老板在最后 3 分钟保持冷静。
//感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
//
//示例 2：
//输入：customers = [1], grumpy = [0], minutes = 1
//输出：1
//
//提示：
//n == customers.length == grumpy.length
//1 <= minutes <= n <= 2 * 10^4
//0 <= customers[i] <= 1000
//grumpy[i] == 0 or 1
public class Problem_1052_MaxSatisfied {

    public static void main(String[] args) {
        int[] customers = new int[]{1,0,1,2,1,1,7,5};
        int []grumpy =new int[]    {0,1,0,1,0,1,0,1};
        int minutes = 3;
        System.out.println(maxSatisfied(customers, grumpy, minutes));
    }

    public static int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int n = customers.length;
        int sum=0;
        //先预处理：grumpy[i]==0时，将customers[i]的值全部加起来
        for (int i=0;i<n;i++) {
            sum += grumpy[i] == 0 ? customers[i] : 0;
        }
        int ans = sum;
        int l=0;
        int r=0; //窗口范围为[l,r)
        while (r<n && r-l <minutes) {
            if (grumpy[r]==1) {
                ans += customers[r];
            }
            r++;
        }
        //窗口涨到minutes大小跳出，或者r越界跳出
        //然后使用滑动窗口对grumpy进行处理，窗口大小固定为minutes
        while (r<n) {
            int cur = ans;
            //l和r同步涨
            if (grumpy[l] == 0) {
                cur -=customers[l];
            }
            if (grumpy[r]==1) {
                cur += customers[r];
            }
            ans = Math.max(ans, cur);
            l++;
            r++;
        }
        return ans;
    }

}
