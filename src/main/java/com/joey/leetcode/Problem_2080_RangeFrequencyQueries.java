package com.joey.leetcode;

import java.util.*;

/**
 * @author pei.liu
 */
//2080. 区间内查询数字的频率
//请你设计一个数据结构，它能求出给定子数组内一个给定值的 频率 。
//子数组中一个值的 频率 指的是这个子数组中这个值的出现次数。
//请你实现 RangeFreqQuery 类：
//RangeFreqQuery(int[] arr) 用下标从 0 开始的整数数组 arr 构造一个类的实例。
//int query(int left, int right, int value) 返回子数组 arr[left...right] 中 value 的 频率 。
//一个 子数组 指的是数组中一段连续的元素。arr[left...right] 指的是 nums 中包含下标 left 和 right 在内 的中间一段连续元素。
//
//示例 1：
//输入：
//["RangeFreqQuery", "query", "query"]
//[[[12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]], [1, 2, 4], [0, 11, 33]]
//输出：
//[null, 1, 2]
//解释：
//RangeFreqQuery rangeFreqQuery = new RangeFreqQuery([12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]);
//rangeFreqQuery.query(1, 2, 4); // 返回 1 。4 在子数组 [33, 4] 中出现 1 次。
//rangeFreqQuery.query(0, 11, 33); // 返回 2 。33 在整个子数组中出现 2 次。
//
//提示：
//1 <= arr.length <= 10^5
//1 <= arr[i], value <= 10^4
//0 <= left <= right < arr.length
//调用 query 不超过 10^5 次。
public class Problem_2080_RangeFrequencyQueries {

    //区间查询元素频率
    //每次查询需要在接近O(1)的时间内完成
    //HashMap
    //key:元素
    //value:元素出现的下标组成的list
    //每次查询先根据key查询对应的list，
    //然后在list上进行二分，大于等于left的最左 l ，小于等于right的最右 r
    //l r代表list中的下标位置，做减法得到答案
    static class RangeFreqQuery {
        Map<Integer, List<Integer>> map = new HashMap<>();

        public RangeFreqQuery(int[] arr) {
            int n = arr.length;
            for (int i = 0; i < n; i++) {
                map.computeIfAbsent(arr[i], k -> new ArrayList<>())
                        .add(i);
            }
        }

        public int query(int left, int right, int value) {
            if (!map.containsKey(value)) {
                return 0;
            }
            List<Integer> indies = map.get(value);
            int li = left(indies, left);
            int ri = right(indies, right);
            if (li > ri) return 0;
            return ri - li + 1;
        }

        private int left(List<Integer> arr, int aim) {
            int l = 0;
            int r = arr.size() - 1;
            int ans = arr.size();
            while (l <= r) {
                int m = l + (r - l) / 2;
                if (arr.get(m) >= aim) {
                    ans = m;
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            return ans;
        }

        private int right(List<Integer> arr, int aim) {
            int l = 0;
            int r = arr.size() - 1;
            int ans = -1;
            while (l <= r) {
                int m = l + (r - l) / 2;
                if (arr.get(m) <= aim) {
                    ans = m;
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            return ans;
        }
    }
}
