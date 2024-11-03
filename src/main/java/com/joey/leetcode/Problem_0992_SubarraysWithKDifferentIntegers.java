package com.joey.leetcode;

import java.util.HashMap;
import java.util.Map;

// 992.K个不同整数的子数组
// 给定一个正整数数组 nums和一个整数 k，返回 nums 中 「好子数组」 的数目。
// 如果 nums 的某个子数组中不同整数的个数恰好为 k
// 则称 nums 的这个连续、不一定不同的子数组为 「好子数组 」。
// 例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。
// 子数组 是数组的 连续 部分。
// 测试链接 : https://leetcode.cn/problems/subarrays-with-k-different-integers/
public class Problem_0992_SubarraysWithKDifferentIntegers {

    //算法原型：求含至多K种不同元素的子数组个数
    public static int subarraysWithKDistinct(int[] nums, int k) {
        return fun(nums, k) - fun(nums, k - 1);
    }

    //返回数组nums中不同元素<=k的子数组个数
    public static int fun(int[] nums, int k) {
        if (k == 0) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        int r = 0; //[i,r)
        int n = nums.length;
        for (int i = 0; i < n; i++) { //枚举每一个开头位置i，求一个答案，累加起来
            while (r < n && ok(map, nums[r], k)) {
                map.put(nums[r], map.getOrDefault(nums[r], 0) + 1);
                r++;
            }
            //while出来时两种情况
            //1.r越界
            //2.r不越界，但是r位置的数如果进窗口的话，种类就超过k了
            ans += r - i; //累加i位置的答案(必须以nums[i]开头的子数组个数)
            //i即将++，i位置的数要出窗口
            pop(map, nums[i]);
        }
        return ans;
    }

    //如果num加入map的size不超
    public static boolean ok(Map<Integer, Integer> map, int num, int k) {
        return map.size() < k || map.containsKey(num);
    }

    public static void pop(Map<Integer, Integer> map, int num) {
        if (map.get(num) == 1) {
            map.remove(num);
        } else {
            map.put(num, map.get(num) - 1);
        }
    }

}
