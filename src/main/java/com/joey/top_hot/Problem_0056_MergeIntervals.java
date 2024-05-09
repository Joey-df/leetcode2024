package com.joey.top_hot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//56. 合并区间
//以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
//请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
//示例 1：
//输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
//输出：[[1,6],[8,10],[15,18]]
//解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
//示例2：
//输入：intervals = [[1,4],[4,5]]
//输出：[[1,5]]
//解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
//链接：https://leetcode-cn.com/problems/merge-intervals
public class Problem_0056_MergeIntervals {

    //[[1,3],[2,6],[8,10],[15,18]]
    public int[][] merge(int[][] arr) {
        if (arr == null || arr.length == 0) return new int[0][2];
        Arrays.sort(arr, (a, b) -> a[0] - b[0]); // important
        int n = arr.length;
        List<int[]> list = new ArrayList<>();
        list.add(arr[0]); // 先把第一个区间加进去
        for (int i = 1; i < n; i++) {
            int[] last = list.get(list.size() - 1);
            int[] cur = arr[i];
            if (cur[0] > last[1]) {
                list.add(cur);
            } else { // <=
                last[1] = Math.max(last[1], cur[1]);
            }
        }
        // collect ans
        int[][] ans = new int[list.size()][2];
        int i = 0;
        for (int[] cur : list) {
            ans[i++] = cur;
        }
        return ans;
    }
}
