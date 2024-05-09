package com.joey.top_hot;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * @author pei.liu
 */
//480. 滑动窗口中位数
//中位数是有序序列最中间的那个数。如果序列的长度是偶数，则没有最中间的数；此时中位数是最中间的两个数的平均数。
//例如：
//[2,3,4]，中位数是 3
//[2,3]，中位数是 (2 + 3) / 2 = 2.5
//给你一个数组 nums，有一个长度为 k 的窗口从最左端滑动到最右端。窗口中有 k 个数，每次窗口向右移动 1 位。
//你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。
public class Problem_0480_MedianSlidingWindow {


    PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder()); // left half
    PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(); // right half
    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length - k + 1;
        if (n <= 0) {
            return new double[0];
        }
        double[] result = new double[n];

        for (int i = 0; i < nums.length; i++) {
            add(nums[i]); // 添加元素 & balance
            if (i >= k - 1) { // 够窗口了开始收集答案
                result[i - k + 1] = getMedian();
                remove(nums[i - k + 1]); //左边的数出窗口，从heap中删除
            }
        }

        return result;
    }

    private void add(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }
        balance();
    }


    private void balance() {
        // 只有相差2 才balance
        if (Math.abs(maxHeap.size() - minHeap.size()) == 2) {
            if (maxHeap.size() > minHeap.size()) {
                minHeap.add(maxHeap.poll());
            } else {
                maxHeap.add(minHeap.poll());
            }
        }
    }

    private void remove(int num) {
        if (maxHeap.contains(num)) {
            maxHeap.remove(num);
        } else {
            minHeap.remove(num);
        }
        balance();
    }

    private double getMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (double) (maxHeap.peek() * 0.5 + minHeap.peek() * 0.5);
        } else {
            return maxHeap.size() > minHeap.size() ? maxHeap.peek() : minHeap.peek();
        }
    }

}
