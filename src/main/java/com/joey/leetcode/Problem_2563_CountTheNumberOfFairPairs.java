package com.joey.leetcode;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * @author pei.liu
 */
//2563. 统计公平数对的数目
//给你一个下标从 0 开始、长度为 n 的整数数组 nums ，和两个整数 lower 和 upper ，返回 公平数对的数目 。
//如果 (i, j) 数对满足以下情况，则认为它是一个 公平数对 ：
//0 <= i < j < n，且
//lower <= nums[i] + nums[j] <= upper
//示例 1：
//输入：nums = [0,1,7,4,4,5], lower = 3, upper = 6
//输出：6
//解释：共计 6 个公平数对：(0,3)、(0,4)、(0,5)、(1,3)、(1,4) 和 (1,5) 。
//示例 2：
//输入：nums = [1,7,9,2,5], lower = 11, upper = 11
//输出：1
//解释：只有单个公平数对：(2,3) 。
//
//提示：
//1 <= nums.length <= 10^5
//nums.length == n
//-10^9 <= nums[i] <= 10^9
//-10^9 <= lower <= upper <= 10^9
//二分查找
public class Problem_2563_CountTheNumberOfFairPairs {


    //lower <= nums[i] + nums[j] <= upper
    //即任意两个不同位置之和位于[low,up]范围上，所以和顺序无关，可以排序
    //设nums[i] + nums[j] = sum
    //转化为 count(sum <= upper) - count(sum <= lower-1)
    public static long countFairPairs(int[] nums, int lower, int upper) {
        return fun(nums, upper) - fun(nums, lower - 1);
    }

    //给定数组arr，求累加和<=target的数对有几个
    public static long fun(int[] arr, int target) {
        Arrays.sort(arr);
        int n = arr.length;
        int l = 0;
        int r = n - 1;
        long ans = 0;
        while (l < r) {
            int sum = arr[l] + arr[r];
            if (sum <= target) {
                ans += r - l; //以i做第一个数的数对个数
                l++; //看下一个位置
            } else {
                r--;
            }
        }
        return ans;
    }

    //TODO 复习
    //使用排序+treeMap 实现 <=某个数最右的位置，计算区间问题
    public static long countFairPairs2(int[] nums, int lower, int upper) {
        long ans = 0;
        //<当前数，当前数最后出现的位置>
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        treeMap.put(Integer.MIN_VALUE, -1);//兜底用的

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) { //对每一个位置i求一个答案
            int x = nums[i]; //固定一个数，求另一个出现的范围
            int minAdd = lower - x;
            int maxAdd = upper - x;

            //小于等于 minAdd-1 这个key最后出现的位置（即<=某个数最右的位置）
            int left = treeMap.floorEntry(minAdd - 1).getValue();
            //小于等于 maxAdd 这个key最后出现的位置（即<=某个数最右的位置）
            int right = treeMap.floorEntry(maxAdd).getValue();
            ans += right - left;

            treeMap.put(x, i);
        }

        return ans;
    }

    //lower <= nums[i] + nums[j] <= upper
    //任意两个i,j只要满足以上条件就可以，因为结果是统计数量，任意一个位置和哪些位置结合能产生答案是确定的，并不会因为排序产生变化，so，和顺序无关，
    //lower - nums[j] <= nums[i] <= upper - nums[j]
    //即到任意一个j位置，求[0,j-1]范围上哪些数落在[ lower - nums[j], upper - nums[j] ]范围上
    //即以nums[j]作为第二个数姿态的情况下，在此之前有多少个数满足条件
    //即在[0,j-1]上找 >=lower - nums[j]最左的位置
    //在[0,j-1]上找 <=upper - nums[j]最右的位置
    public static long countFairPairs3(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        int n = nums.length;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int a = lower - nums[i];
            int b = upper - nums[i];
            int l = findLeft(nums, 0, i - 1, a);
            int r = findRight(nums, 0, i - 1, b);
            System.out.printf("l=%d, r=%d\n", l, r);
            ans += (l == -1 || r == -1) ? 0 : r - l + 1;
        }
        return ans;
    }

    public static int findLeft(int[] arr, int l, int r, int target) {
        int ans = -1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (arr[m] >= target) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans; //-1表示没找到
    }

    public static int findRight(int[] arr, int l, int r, int target) {
        int ans = -1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (arr[m] <= target) {
                ans = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans; //-1表示没找到
    }

    public static long countFairPairs4(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        int n = nums.length;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int a = lower - nums[i];
            int b = upper - nums[i];
            int l = findRight(nums, 0, i - 1, a - 1);
            int r = findRight(nums, 0, i - 1, b);
            //System.out.printf("l=%d, r=%d\n", l, r);
            ans += r - l; // <=up - <=low-1 =>[low,up]
        }
        return ans;
    }

}
