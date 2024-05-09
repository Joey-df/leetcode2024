package com.joey.top_hot;

import java.util.ArrayList;
import java.util.HashMap;

//350. 两个数组的交集 II
//给你两个整数数组 nums1 和 nums2 ，请你以数组形式返回两数组的交集。
//返回结果中每个元素出现的次数，应与元素在两个数组中都出现的次数一致（如果出现次数不一致，则考虑取较小值）。可以不考虑输出结果的顺序。
//
//示例 1：
//输入：nums1 = [1,2,2,1], nums2 = [2,2]
//输出：[2,2]
//示例 2:
//输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//输出：[4,9]
//
//提示：
//1 <= nums1.length, nums2.length <= 1000
//0 <= nums1[i], nums2[i] <= 1000
public class Problem_0350_IntersectionOfTwoArraysII {

    public static int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null && nums2 == null) { //都为空
            return null;
        }
        if (nums1 == null ^ nums2 == null) { //一个空一个不空
            return null;
        }
        HashMap<Integer, Integer> map1 = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();
        //对两个数组分别统计词频
        for (int e : nums1) {
            map1.put(e, map1.getOrDefault(e, 0) + 1);
        }
        for (int e : nums2) {
            map2.put(e, map2.getOrDefault(e, 0) + 1);
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int k : map1.keySet()) {
            if (map2.containsKey(k)) { //求交集的核心：两个map同时存在某个key
                int count = Math.min(map1.get(k), map2.get(k)); //如果出现次数不一致，则考虑取较小值
                for (int i = 0; i < count; i++) {
                    list.add(k);
                }
            }
        }
        //list转为数组
        int[] ans = new int[list.size()];
        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            ans[index++] = list.get(i);
        }
        return ans;
    }

}
