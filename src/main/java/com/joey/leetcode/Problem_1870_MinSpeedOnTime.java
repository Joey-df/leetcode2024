package com.joey.leetcode;

/**
 * @author pei.liu
 */
//1870. 准时到达的列车最小时速
//给你一个浮点数 hour ，表示你到达办公室可用的总通勤时间。要到达办公室，你必须按给定次序乘坐 n 趟列车。
//另给你一个长度为 n 的整数数组 dist ，其中 dist[i] 表示第 i 趟列车的行驶距离（单位是千米）。
//每趟列车均只能在整点发车，所以你可能需要在两趟列车之间等待一段时间。
//例如，第 1 趟列车需要 1.5 小时，那你必须再等待 0.5 小时，搭乘在第 2 小时发车的第 2 趟列车。
//返回能满足你在时限前到达办公室所要求全部列车的 最小正整数 时速（单位：千米每小时），如果无法准时到达，则返回 -1 。
//生成的测试用例保证答案不超过 10^7 ，且 hour 的 小数点后最多存在两位数字 。
//示例 1：
//输入：dist = [1,3,2], hour = 6
//输出：1
//解释：速度为 1 时：
//- 第 1 趟列车运行需要 1/1 = 1 小时。
//- 由于是在整数时间到达，可以立即换乘在第 1 小时发车的列车。第 2 趟列车运行需要 3/1 = 3 小时。
//- 由于是在整数时间到达，可以立即换乘在第 4 小时发车的列车。第 3 趟列车运行需要 2/1 = 2 小时。
//- 你将会恰好在第 6 小时到达。
//示例 2：
//输入：dist = [1,3,2], hour = 2.7
//输出：3
//解释：速度为 3 时：
//- 第 1 趟列车运行需要 1/3 = 0.33333 小时。
//- 由于不是在整数时间到达，故需要等待至第 1 小时才能搭乘列车。第 2 趟列车运行需要 3/3 = 1 小时。
//- 由于是在整数时间到达，可以立即换乘在第 2 小时发车的列车。第 3 趟列车运行需要 2/3 = 0.66667 小时。
//- 你将会在第 2.66667 小时到达。
//示例 3：
//输入：dist = [1,3,2], hour = 1.9
//输出：-1
//解释：不可能准时到达，因为第 3 趟列车最早是在第 2 小时发车。
//
//提示：
//n == dist.length
//1 <= n <= 10^5
//1 <= dist[i] <= 10^5
//1 <= hour <= 10^9
//hours 中，小数点后最多存在两位数字
public class Problem_1870_MinSpeedOnTime {

    //答案有没有范围：
    //最小速度：1
    //最大速度：数组中最大值（每趟列车均只能在整点发车，所以你可能需要在两趟列车之间等待一段时间）
    //返回能满足你在时限前到达办公室所要求全部列车的 最小正整数 时速
    public static int minSpeedOnTime(int[] dist, double hour) {
        int max = 10^7; //生成的测试用例保证答案不超过 10^7 ，且 hour 的 小数点后最多存在两位数字 。
        int l = 1;
        int r = max;
        int ans = -1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            double need = f(dist, m); //速度越大，需要的时间越短，反比
            if (need <= hour) {
                //右边都达标
                ans = m;
                r = m - 1; //左边二分
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    //速度定成speed
    //需要的时间是多少
    public static double f(int[] dist, int speed) {
        double ans = 0;
        int n = dist.length;
        //前n-1个向上取整，最后一个直接除
        for (int i = 0; i < n - 1; i++) {
            ans += (double) ((dist[i] + speed - 1) / speed);
        }
        ans += (double) dist[n - 1] / speed;
        return ans;
    }

    public static void main(String[] args) {
        int[] dist = {1, 3, 2};
        double hour = 2.7;
        System.out.println(minSpeedOnTime(dist, hour));
    }

}
