package com.joey.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pei.liu
 */
//1865. 找出和为指定值的下标对
//给你两个整数数组 nums1 和 nums2 ，请你实现一个支持下述两类查询的数据结构：
//累加 ，将一个正整数加到 nums2 中指定下标对应元素上。
//计数 ，统计满足 nums1[i] + nums2[j] 等于指定值的下标对 (i, j) 数目（0 <= i < nums1.length 且 0 <= j < nums2.length）。
//实现 FindSumPairs 类：
//FindSumPairs(int[] nums1, int[] nums2) 使用整数数组 nums1 和 nums2 初始化 FindSumPairs 对象。
//void add(int index, int val) 将 val 加到 nums2[index] 上，即，执行 nums2[index] += val 。
//int count(int tot) 返回满足 nums1[i] + nums2[j] == tot 的下标对 (i, j) 数目。
//提示：
//1 <= nums1.length <= 1000
//1 <= nums2.length <= 10^5
//1 <= nums1[i] <= 10^9
//1 <= nums2[i] <= 10^5
//0 <= index < nums2.length
//1 <= val <= 10^5
//1 <= tot <= 10^9
//最多调用 add 和 count 函数各 1000 次
public class Problem_1865_FindingPairsWithACertainSum {

    //支持随时更新nums2某个下标的值
    //支持随时查询nums1[i] + nums2[j] == tot的个数
    //应该要用hashMap可以做到O(1)查询
    //因为nums1不会更新，是静态的，可以初始化一个map，key为元素值，val为出现的位置个数
    //nums2也可以使用一个map存，动态的
    //add方法对nums2指定位置+完，更新map的两个key
    //count的时候对对两个map进行遍历
    static class FindSumPairs {
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        int[] arr; //每次对nums2更新的时候需要

        public FindSumPairs(int[] nums1, int[] nums2) {
            arr = nums2;
            for (int i = 0; i < nums1.length; i++) {
                map1.put(nums1[i], map1.getOrDefault(nums1[i], 0) + 1);
            }
            for (int i = 0; i < nums2.length; i++) {
                map2.put(nums2[i], map2.getOrDefault(nums2[i], 0) + 1);
            }
        }

        public void add(int index, int val) {
            int old = arr[index];
            int curr = arr[index] + val;
            arr[index] = curr;
            map2.put(old, map2.getOrDefault(old, 0) - 1); //老值词频减1
            map2.put(curr, map2.getOrDefault(curr, 0) + 1); //新值词频+1
        }

        //比如tot=2
        public int count(int tot) {
            int ans = 0;
            for (int key : map1.keySet()) { //因为nums1长度较短，这样高效
                int count1 = map1.getOrDefault(key, 0); //key=2出现的次数
                int count2 = map1.getOrDefault(tot - key, 0); //在map2中找5出现的次数
                ans += count1 * count2;
            }
            return ans;
        }
    }

}
