package com.joey.leetcode;

/**
 * @author pei.liu
 */
//2426. 满足不等式的数对数目
//给你两个下标从 0 开始的整数数组 nums1 和 nums2 ，两个数组的大小都为 n ，同时给你一个整数 diff ，统计满足以下条件的 数对 (i, j) ：
//0 <= i < j <= n - 1 且
//nums1[i] - nums1[j] <= nums2[i] - nums2[j] + diff.
//请你返回满足条件的 数对数目 。
//示例 1：
//输入：nums1 = [3,2,5], nums2 = [2,2,1], diff = 1
//输出：3
//解释：
//总共有 3 个满足条件的数对：
//1. i = 0, j = 1：3 - 2 <= 2 - 2 + 1 。因为 i < j 且 1 <= 1 ，这个数对满足条件。
//2. i = 0, j = 2：3 - 5 <= 2 - 1 + 1 。因为 i < j 且 -2 <= 2 ，这个数对满足条件。
//3. i = 1, j = 2：2 - 5 <= 2 - 1 + 1 。因为 i < j 且 -3 <= 2 ，这个数对满足条件。
//所以，我们返回 3 。
//示例 2：
//输入：nums1 = [3,-1], nums2 = [-2,2], diff = -1
//输出：0
//解释：
//没有满足条件的任何数对，所以我们返回 0 。
//
//提示：
//n == nums1.length == nums2.length
//2 <= n <= 10^5
//-10^4 <= nums1[i], nums2[i] <= 10^4
//-10^4 <= diff <= 10^4
public class Problem_2426_NumberOfPairsSatisfyingInequality {

    //nums1[i] - nums1[j] <= nums2[i] - nums2[j] + diff.
    //nums1[i] - nums2[i] <= nums1[j] - nums2[j] + diff.
    //两个数组加工得到arr
    //变成求arr[i] <= arr[j] + diff
    //变成到每个位置求 arr[j] >= arr[i] - diff 的数量
    //到每个位置 如何快速求出 在此之前 小于等于 当前数的个数
    //=> 归并分治
    public static int diff;

    public long numberOfPairs(int[] nums1, int[] nums2, int d) {
        int n = nums1.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = nums1[i] - nums2[i];
        }
        diff = d;
        return fun(arr, 0, n - 1);
    }

    //返回在arr[l,r]范围上，满足arr[i]-diff <= arr[j]的个数
    //完事之后数组变有序
    private long fun(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int m = l + (r - l) / 2;
        return fun(arr, l, m)
                + fun(arr, m + 1, r)
                + merge(arr, l, m, r);
    }

    //arr[l,m] [m+1,r]已经有序
    //1. 让数组整体变有序
    //2. 计算出跨左右产生的数量
    private long merge(int[] arr, int l, int m, int r) {
        //统计部分
        //diff=1
        //2 5    3  6
        //
        long ans = 0;
        int ri = l;
        for (int j = m + 1; j <= r; j++) {
            while (ri <= m && arr[ri] - diff <= arr[j]) {
                ri++;
            }
            ans += ri - l; //累加j位置的答案
        }
        //归并排序部分
        int[] help = new int[r - l + 1];
        int a = l;
        int b = m + 1;
        int ci = 0;
        while (a <= m && b <= r) {
            help[ci++] = arr[a] <= arr[b] ? arr[a++] : arr[b++];
        }
        while (a <= m) help[ci++] = arr[a++];
        while (b <= r) help[ci++] = arr[b++];
        //刷回去
        for (int i = 0; i < r - l + 1; i++) {
            arr[i + l] = help[i];
        }
        return ans;
    }

}
