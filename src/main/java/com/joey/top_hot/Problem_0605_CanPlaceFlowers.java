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
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (flowerbed == null || flowerbed.length == 0 || n < 0) {
            return false;
        }
        //[1,0,1,0,1,0,1]
        //0
        int count = 0;
        int len = flowerbed.length;
        for (int i = 0; i < len; i++) {
            if (flowerbed[i] == 0) {
                //考虑两个边界情况：
                //第一个位置没有左边(认为左边为0)
                //最后一个位置没有右边(认为右边为0)
                int pre = i == 0 ? 0 : flowerbed[i - 1]; // 当前i位置的前一个位置
                int next = i == len - 1 ? 0 : flowerbed[i + 1]; // 当前i位置的后一个位置
                if (pre == 0 && next == 0) {
                    count++;
                    flowerbed[i] = 1;
                }
            }
        }
        return count >= n; //能种下的花是否大于等于n
    }

}
