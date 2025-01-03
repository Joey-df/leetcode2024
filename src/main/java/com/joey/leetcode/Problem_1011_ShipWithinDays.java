package com.joey.leetcode;

import java.util.Arrays;

/**
 * @author pei.liu
 */
//1011. 在 D 天内送达包裹的能力
//传送带上的包裹必须在 days 天内从一个港口运送到另一个港口。
//传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量（weights）的顺序往传送带上装载包裹。
//我们装载的重量不会超过船的最大运载重量。
//返回能在 days 天内将传送带上的所有包裹送达的船的最低运载能力。
//
//示例 1：
//输入：weights = [1,2,3,4,5,6,7,8,9,10], days = 5
//输出：15
//解释：
//船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
//第 1 天：1, 2, 3, 4, 5
//第 2 天：6, 7
//第 3 天：8
//第 4 天：9
//第 5 天：10
//请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) 是不允许的。
//示例 2：
//输入：weights = [3,2,2,4,1,4], days = 3
//输出：6
//解释：
//船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
//第 1 天：3, 2
//第 2 天：2, 4
//第 3 天：1, 4
//示例 3：
//输入：weights = [1,2,3,1,1], days = 4
//输出：3
//解释：
//第 1 天：1
//第 2 天：2
//第 3 天：3
//第 4 天：1, 1
//
//提示：
//1 <= days <= weights.length <= 5 * 10^4
//1 <= weights[i] <= 500
public class Problem_1011_ShipWithinDays {

    //分割数组的最大值
    //每部分的累加和尽量小
    public static int shipWithinDays(int[] weights, int days) {
        int sum = 0;
        for (int w : weights) {
            sum += w;
        }
        int l = 1;
        int r = sum;
        int ans = 0;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (f(weights, m) <= days) {
                //每部分的累加和（船的载重）越大，需要的天数越小
                ans = m; //说明大于m的载重都达标
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    //船的载重为c的情况下，拉完所有货物需要几天
    //必须是连续的一段，即每部分累加和不超过c，数组会被分成几段
    public static int f(int[] nums, int c) {
        if (Arrays.stream(nums).max().orElse(-1) > c) {
            return Integer.MAX_VALUE;
        }
        int n = nums.length;
        int ans = 0;
        int r = 0;
        int windSum = 0;
        for (int l = 0; r < n; ) {
            while (r < n && windSum + nums[r] <= c) {
                windSum += nums[r++];
            }
            //while出来两种情况
            //1.r越界了
            //2.windSum > c
            ans++;
            l = r;
            windSum = 0;
        }
        return ans;
    }

}
