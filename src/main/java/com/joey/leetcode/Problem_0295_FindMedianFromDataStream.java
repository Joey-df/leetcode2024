package com.joey.leetcode;

import java.util.PriorityQueue;

/**
 * 295. 数据流的中位数
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 *
 * 例如，
 *
 * [2,3,4] 的中位数是 3
 *
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 *
 * 设计一个支持以下两种操作的数据结构：
 *
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 * 示例：
 *
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 * 进阶:
 *
 * 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
 * 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
 */
public class Problem_0295_FindMedianFromDataStream {

    /** initialize your data structure here. */
    class MedianFinder {

        private PriorityQueue<Integer> maxh;
        private PriorityQueue<Integer> minh;

        public MedianFinder() {
            maxh = new PriorityQueue<>((a, b) -> b - a); // left部分（数比较小的部分）
            minh = new PriorityQueue<>((a, b) -> a - b); // right部分（数比较大的部分）
        }

        public void addNum(int num) {
            //如果大根堆为空，直接进大根堆（第一个元素）
            //如果新加元素<=大根堆堆顶，进大根堆；否则进小根堆
            //大根堆，小根堆大小相差2时，调整一下平衡
            if (maxh.isEmpty() || maxh.peek() >= num) {
                maxh.add(num);
            } else {
                minh.add(num);
            }
            balance();
        }

        public double findMedian() {
            if (maxh.size() == minh.size()) {
                return (double) (maxh.peek() + minh.peek()) / 2;
            } else {
                return maxh.size() > minh.size() ? maxh.peek() : minh.peek();
            }
        }

        private void balance() {
            if (Math.abs(maxh.size() - minh.size()) == 2) {
                if (maxh.size() > minh.size()) {
                    minh.add(maxh.poll());
                } else {
                    maxh.add(minh.poll());
                }
            }
        }

    }
}
