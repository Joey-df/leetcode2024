package com.joey.leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//315. 计算右侧小于当前元素的个数
//给定一个整数数组 nums，按要求返回一个新数组 counts。
//数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
//示例：
//输入：nums = [5,2,6,1]
//输出：[2,1,1,0]
//解释：
//5 的右侧有 2 个更小的元素 (2 和 1)
//2 的右侧仅有 1 个更小的元素 (1)
//6 的右侧有 1 个更小的元素 (1)
//1 的右侧有 0 个更小的元素
//提示：
//1 <= nums.length <= 10^5
//-10^4 <= nums[i] <= 10^4
public class Problem_0315_CountOfSmallerNumbersAfterSelf {

    static class Info {
        int i;
        int val;
        int count;

        public Info(int i, int val, int count) {
            this.i = i;
            this.val = val;
            this.count = count;
        }
    }

    //归并分治
    public static List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        Info[] infos = new Info[n];
        for (int i = 0; i < n; i++) {
            infos[i] = new Info(i, nums[i], 0);
        }
        fun(infos, 0, n - 1);
        Arrays.sort(infos, (a, b) -> a.i - b.i);
        return Arrays.stream(infos).map(a -> a.count).collect(Collectors.toList());
    }

    //函数功能
    //求出arr[l,r]上每个位置右边比自己小的元素个数
    //并且完事之后，数组在val层面上变有序
    public static void fun(Info[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int m = l + (r - l) / 2;
        fun(arr, l, m);
        fun(arr, m + 1, r);
        merge(arr, l, m, r);
    }

    //arr[l,m]
    //arr[m+1,r]在val层面上已经变有序
    private static void merge(Info[] arr, int l, int m, int r) {
        //统计部分
        //2 5    1 6
        int ri = m + 1;
        for (int i = l; i <= m; i++) { //更新左半区的答案
            while (ri <= r && arr[ri].val < arr[i].val) {
                ri++;
            }
            //更新i位置的答案
            arr[i].count += ri - (m + 1);
        }
        //归并排序部分
        Info[] help = new Info[r - l + 1];
        int a = l;
        int b = m + 1;
        int ci = 0;
        while (a <= m && b <= r) {
            help[ci++] = arr[a].val <= arr[b].val ? arr[a++] : arr[b++];
        }
        while (a <= m) help[ci++] = arr[a++];
        while (b <= r) help[ci++] = arr[b++];
        //刷回去
        for (int i = 0; i < r - l + 1; i++) {
            arr[i + l] = help[i];
        }
    }


}
