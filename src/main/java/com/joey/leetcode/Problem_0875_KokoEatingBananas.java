package com.joey.leetcode;

/**
 * @author pei.liu
 */
//875. 爱吃香蕉的珂珂
//珂珂喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 h 小时后回来。
//珂珂可以决定她吃香蕉的速度 k （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 k 根。
//如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。
//珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
//返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）。
public class Problem_0875_KokoEatingBananas {

    //吃香蕉的速度存在一个范围
    //[1,max]:如果以最慢的速度吃香蕉，每小时吃一根，吃掉所有香蕉需要的小时为sum
    //        如果以max的速度吃香蕉则每堆香蕉只需要一小时，大于max的速度没意义，因为大于max每堆香蕉也需要1小时
    //有了范围，接下来的问题就是在这个范围上找符合条件的最小的速度
    //分析单调性：
    //速度越快，需要的时间越短，速度越慢，需要的时间越长
    //假设以v的速度吃饭所有香蕉需要a小时，a<=h,则大于v的速度都达标，在v的左侧继续二分；否则在v的右边二分
    public int minEatingSpeed(int[] piles, int h) {
        long l = 1;
        long r = piles[0];
        for (int e : piles) {
            r = Math.max(r, e);
        }
        long ans = r;
        while (l <= r) {
            long m = l + ((r - l) >> 1);
            long need = fun(piles, m);
            if (need <= h) { //右边的都达标，m代表速度，速度变大，需要的时间变小
                //达标
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return (int) ans;
    }

    //以speed的速度吃香蕉，需要的小时返回
    public static long fun(int[] arr, long speed) {
        long need = 0;
        for (int i = 0; i < arr.length; i++) {
            need += (arr[i] + speed - 1) / speed;
        }
        return need;
    }
}

