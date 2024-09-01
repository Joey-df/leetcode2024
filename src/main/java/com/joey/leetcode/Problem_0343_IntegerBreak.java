package com.joey.leetcode;

import java.util.HashMap;

/**
 * @author pei.liu
 */

//343. 整数拆分
//给定一个正整数 n ，将其拆分为 k 个 正整数 的和（ k >= 2 ），并使这些整数的乘积最大化。
//返回 你可以获得的最大乘积 。
//提示:
//2 <= n <= 58
//本题与LCR 132. 砍竹子 II相同
//https://leetcode.cn/problems/jian-sheng-zi-ii-lcof/description/
//提示：
//2 <= bamboo_len <= 1000
//区别在于两题的数据量规模不一样
public class Problem_0343_IntegerBreak {


    public int integerBreak(int n) {
        HashMap<Integer, Integer> dp = new HashMap<>();
        return f(n, dp);
    }

    //n>=2
    public static int f(int n, HashMap<Integer, Integer> dp) {
        if (n == 2) return 1;
        if (n == 3) return 2;
        if (dp.containsKey(n)) {
            return dp.get(n);
        }
        int ans = 0;
        for (int i = 2; i < n; i++) {
            ans = Math.max(ans, Math.max(i * f(n - i, dp), i * (n - i)));
        }
        dp.put(n, ans);
        return ans;
    }

    public static int mod = 1000000007;

    //拓展
    //一个很大的数N一定要分成k份，求最大累乘积
    //k<=N
    public static int maxProduct(int n, int k) {
        return fun(n, k);
    }

    //rest分成k份，返回最大累乘积
    public static int fun(int rest, int k) {
        if (k == 1) {
            return rest;
        } else {
            int ans = Integer.MIN_VALUE;
            //保证rest-cur还能够分成k-1份
            for (int cur = 1; cur < rest && (rest - cur >= k - 1); cur++) {
                ans = Math.max(ans, cur * fun(rest - cur, k - 1) % mod);
            }
            return ans;
        }
    }

    //贪心：分开的几个数聚集在平均值周围，乘积应该可以最大化
    //比如10，分成3 3 4
    public static int maxProduct2(int n, int k) {
        //n=10, k=3
        //3 3 4
        int a = n / k; //3
        int b = n % k; //1
        long part1 = power(a, k - b);
        long part2 = power(a + 1, b);
        return (int) (part1 * part2) % mod;
    }

    //求a的p次方
    public static long power(int a, int p) {
        long ans = 1;
        int x = a;
        while (p != 0) {
            if ((p & 1) == 1) {
                ans = (ans * x) % mod;
            }
            x = x * x ;
            p >>= 1;
        }
        return ans;
    }


    public static void main(String[] args) {
        int N = 30;
        int times = 2000;
        System.out.println("测试开始");
        for (int i = 0; i < times; i++) {
            //Math.random() * N -> [0,N)
            //Math.random() * N + 1 -> [1,N]
            int n = (int) (Math.random() * N) + 1;
            int k = (int) (Math.random() * n) + 1;
            int ans1 = maxProduct(n, k);
            int ans2 = maxProduct2(n, k);
            if (ans1 != ans2) {
                System.out.printf("出错了 ans1=%d, ans2=%d\n", ans1, ans2);
            }
        }
        System.out.println("测试结束");
    }
}
