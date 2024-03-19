package com.joey.top_hot;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 350. 两个数组的交集 II
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * 示例 1：
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 * <p>
 * 示例 2:
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[4,9]
 */
public class Problem_0350_IntersectionOfTwoArraysII {

    public static int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null && nums2 == null) {
            return null;
        }
        if (nums1 == null ^ nums2 == null) { //一个空一个不空
            return null;
        }
        HashMap<Integer, Integer> map1 = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            map1.put(nums1[i], map1.getOrDefault(nums1[i], 0) + 1);
        }
        for (int n : nums2) {
            map2.put(n, map2.getOrDefault(n, 0) + 1);
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int k : map1.keySet()) {
            if (map2.containsKey(k)) {
                int count = Math.min(map1.get(k), map2.get(k));
                for (int i = 0; i < count; i++) {
                    list.add(k);
                }
            }
        }
        int[] ans = new int[list.size()];
        int index = 0;
        for (int n : list) {
            ans[index++] = n;
        }
        return ans;
    }

    private static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{4, 9, 5, 9};
        int[] nums2 = new int[]{9, 4, 9, 8, 4};
        int[] intersect = intersect(nums1, nums2);
        print(intersect);
    }
}
