package com.joey.od.s200;

/**
 * 题目描述
 * 孙悟空爱吃蟠桃，有一天趁着蟠桃园守卫不在来偷吃。已知蟠桃园有 N 棵蟠桃树，每棵树上都桃子，守卫将在 H 小时后回来。
 * 孙悟空可以决定他吃蟠桃的速度 K （个/每小时），每个小时选一棵桃树，并从树上吃掉 K 个，如果K大于该树上所有桃子个数，则全部吃掉，并且这一小时剩余的时间里不再吃桃。
 * 孙悟空喜欢慢慢吃，但又想在守卫回来前吃完桃子。
 * 请返回孙悟空可以在 H 小时内吃掉所有桃子的最小速度 K （K 为整数）。如果以任何速度都吃不完所有桃子，则返回 0。
 * <p>
 * 输入描述
 * 第一行输入为 N个数字， N 表示桃树的数量，这 N 个数字表示每棵桃树上蟠桃的数量。
 * 第二行输入为一个数字，表示守卫离开的时间 H。
 * 其中数字通过空格分割， N、 H 为正整数，每棵树上都有蟠桃，且 0<N<10000, 0 < H < 10000。
 * <p>
 * 输出描述
 * 输出吃掉所有蟠桃的最小速度 K，无解或输入异常时输出 0。
 */


import java.util.Arrays;
import java.util.Scanner;

// lc875 爱吃香蕉的可可
public class 爱吃蟠桃的孙悟空 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int h = Integer.parseInt(sc.nextLine());

        System.out.println(minEatingSpeed(arr, h));
    }

    // 时间复杂度O(n * log(max))，额外空间复杂度O(1)
    public static int minEatingSpeed(int[] piles, int h) {
        // 最小且达标的速度，范围[l,r]
        int l = 1;
        int r = 0;
        for (int pile : piles) {
            r = Math.max(r, pile);
        }
        // [l,r]不停二分
        int ans = 0;
        int m = 0;
        while (l <= r) {
            // m = (l + r) / 2
            m = l + ((r - l) >> 1);
            if (f(piles, m) <= h) {
                // 达标！
                // 记录答案，去左侧二分
                ans = m;
                // l....m....r
                // l..m-1
                r = m - 1;
            } else {
                // 不达标
                l = m + 1;
            }
        }
        return ans;
    }

    // 香蕉重量都在piles
    // 速度就定成speed
    // 返回吃完所有的香蕉，耗费的小时数量
    public static long f(int[] piles, int speed) {
        long ans = 0;
        for (int pile : piles) {
            // (a/b)结果向上取整，如果a和b都是非负数，可以写成(a+b-1)/b
            // "讲解032-位图"讲了这种写法，不会的同学可以去看看
            // 这里不再赘述
            ans += (pile + speed - 1) / speed;
        }
        return ans;
    }
}