package com.joey.top_hot;

//605. 种花问题
//假设有一个很长的花坛，一部分地块种植了花，另一部分却没有。
//可是，花不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
//给你一个整数数组  flowerbed 表示花坛，由若干 0 和 1 组成，其中 0 表示没种植花，1 表示种植了花。
//另有一个数 n ，能否在不打破种植规则的情况下种入 n 朵花？能则返回 true ，不能则返回 false。
//
//示例 1：
//输入：flowerbed = [1,0,0,0,1], n = 1
//输出：true
//示例 2：
//输入：flowerbed = [1,0,0,0,1], n = 2
//输出：false
//
//提示：
//1 <= flowerbed.length <= 2 * 10^4
//flowerbed[i] 为 0 或 1
//flowerbed 中不存在相邻的两朵花
//0 <= n <= flowerbed.length
public class Problem_0605_CanPlaceFlowers {

    //整体思路：
    //从左往后遍历，如果当前位置的值为0，考虑pre和next是否同时为0，如果同时为0则可以种花，并把值改为1
    //需要注意0、n-1两个位置边界（0的pre认为是0，n-1的next认为是0）
    public static boolean canPlaceFlowers(int[] flowerbed, int k) {
        //1 <= flowerbed.length <= 2 * 10^4
        int n = flowerbed.length;
        int[] arr = new int[n + 2];
        for (int i = 0; i < n; i++) {
            arr[i + 1] = flowerbed[i];
        }
        int m = arr.length;
        int pre = 0;
        int next;
        int count = 0;
        for (int i = 1; i < m - 1; i++) {
            next = arr[i + 1];
            if (pre == 0 && next == 0 && arr[i] == 0) {
                count++;
                arr[i] = 1;
            }
            pre = arr[i];
        }
        return count >= k;
    }

}
