package com.joey.leetcode;

//338. 比特位计数
//给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
public class Problem_0338_CountingBits {

    //O(n*sizeof(integer))
    public static int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            ans[i] = help(i);
        }
        return ans;
    }

    public static int help(int n) {
        //统计n中二进制1的个数
        int ans = 0;
        while (n != 0) {
            int rightOne = n & (~n + 1); //最右侧的1
            ans++;
            n ^= rightOne; //消除最右侧的1
        }
        return ans;
    }

    //最优解
    //Explaination.
    //Take number X for example, 10011001.
    //Divide it in 2 parts:
    //<1>the last digit ( 1 or 0, which is " i&1 ", equivalent to " i%2 " )
    //<2>the other digits ( the number of 1, which is " f[i >> 1] ", equivalent to " f[i/2] " )
    public int[] countBits2(int num) {
        int[] ans = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            ans[i] = ans[i >> 1] + (i & 1);
        }
        return ans;
    }

}
