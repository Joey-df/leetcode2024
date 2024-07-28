package com.joey.leetcode;

import java.util.PriorityQueue;

//215. 数组中的第K个最大元素
//给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
//请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
//你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
//
//示例 1:
//输入: [3,2,1,5,6,4], k = 2
//输出: 5
//示例 2:
//输入: [3,2,3,1,2,4,5,5,6], k = 4
//输出: 4
//
//提示：
//1 <= k <= nums.length <= 10^5
//-10^4 <= nums[i] <= 10^4
public class Problem_0215_KthLargestElementInAnArray {

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private static int[] partition(int[] arr, int l, int r) {
        int base = arr[r];
        int less = l - 1;
        int more = r + 1;
        int i = l;
        while (i < more) {
            if (arr[i] == base) {
                i++;
            } else if (arr[i] < base) {
                swap(arr, i++, ++less);
            } else {
                swap(arr, i, --more);
            }
        }
        return new int[]{less + 1, more - 1};
    }

    //递归
    //在nums[l,r]范围上找位于index下标的数，返回
    private static int find(int[] nums, int l, int r, int index) {
        int[] range = partition(nums, l, r);
        if (index >= range[0] && index <= range[1]) {
            return nums[index];
        } else if (index < range[0]) {
            return find(nums, l, range[0] - 1, index);
        }
        //index>range[1]
        return find(nums, range[1] + 1, r, index);
    }

    //迭代
    //无序数组中，找到位于index位置的元素，返回
    //潜台词：index一定在[l,r]范围上
    public static int find2(int[] arr, int l, int r, int index) {
        while (l < r) {
            swap(arr, (l + (int) (Math.random() * (r - l + 1))), r);
            int[] range = partition(arr, l, r);
            if (index >= range[0] && index <= range[1]) {
                return arr[index];
            } else if (index < range[0]) {
                r = range[0] - 1;
            } else {
                l = range[1] + 1;
            }
        }
        return arr[l];
    }

    public static int findKthLargest(int[] nums, int k) {
        return find(nums, 0, nums.length - 1, nums.length - k);
    }

    //方法2：使用门槛堆
    //时间复杂度：O(n*logk)
    public static int findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int e : nums) {
            //不够k个直接加
            if (heap.isEmpty() || heap.size() < k) {
                heap.offer(e);
            } else { //够k个了，如果当前元素e大于堆顶元素，用e替换堆顶
                if (e > heap.peek()) {
                    heap.poll();
                    heap.offer(e);
                }
            }
        }
        return heap.peek();
    }

}
