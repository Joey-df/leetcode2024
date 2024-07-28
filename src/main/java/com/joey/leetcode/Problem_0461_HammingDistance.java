package com.joey.leetcode;

/**
 * @author pei.liu
 */
//461. 汉明距离
//两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
//给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
//示例 1：
//输入：x = 1, y = 4
//输出：2
//解释：
//1   (0 0 0 1)
//4   (0 1 0 0)
//       ↑   ↑
//上面的箭头指出了对应二进制位不同的位置。
//示例 2：
//输入：x = 3, y = 1
//输出：1
public class Problem_0461_HammingDistance {

    public int hammingDistance(int x, int y) {
        int eor = x ^ y; //相同位上都是1会变成0
        //统计eor中1的数量
        int count = 0;
        int n = eor;
        while (n != 0) {
            int rightOne = n & -n; //提取出最右侧的1
            count++;
            n = n ^ rightOne; //消掉最右侧的1
        }
        return count;
    }

}
