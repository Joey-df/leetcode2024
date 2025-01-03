package com.joey.leetcode;

/**
 * @author pei.liu
 */
//702. 搜索长度未知的有序数组
//这是一个交互问题。
//您有一个升序整数数组，其长度未知。您没有访问数组的权限，但是可以使用 ArrayReader 接口访问它。你可以调用 ArrayReader.get(i):
//返回数组第ith个索引(0-indexed)处的值(即 secret[i])，或者
//如果 i  超出了数组的边界，则返回 2^31 - 1
//你也会得到一个整数 target。
//如果存在secret[k] == target，请返回索引 k 的值；否则返回 -1
//你必须写一个时间复杂度为 O(log n) 的算法。
//
//示例 1：
//输入: secret = [-1,0,3,5,9,12], target = 9
//输出: 4
//解释: 9 存在在 nums 中，下标为 4
//示例 2：
//输入: secret = [-1,0,3,5,9,12], target = 2
//输出: -1
//解释: 2 不在数组中所以返回 -1
//
//提示：
//1 <= secret.length <= 10^4
//-10^4 <= secret[i], target <= 10^4
//secret 严格递增
public class Problem_0702_SearchUnknown {

    /**
     * // This is ArrayReader's API interface.
     * // You should not implement it, or speculate about its implementation
     * interface ArrayReader {
     * public int get(int index) {}
     * }
     */
    interface ArrayReader {
        int get(int index);
    }

    int MAXN = 10001;

    public int search(ArrayReader reader, int target) {
        int l = 0;
        int r = MAXN;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (reader.get(m) >= target) {
                if (reader.get(m) == target) return m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return -1;
    }

}
