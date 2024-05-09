package com.joey.top_hot;

import java.util.PriorityQueue;

/**
 * @author pei.liu
 */
//1845. 座位预约管理系统
//请你设计一个管理 n 个座位预约的系统，座位编号从 1 到 n 。
//请你实现 SeatManager 类：
//SeatManager(int n) 初始化一个 SeatManager 对象，它管理从 1 到 n 编号的 n 个座位。所有座位初始都是可预约的。
//int reserve() 返回可以预约座位的 最小编号 ，此座位变为不可预约。
//void unreserve(int seatNumber) 将给定编号 seatNumber 对应的座位变成可以预约。
//提示：
//1 <= n <= 10^5
//1 <= seatNumber <= n
//每一次对 reserve 的调用，题目保证至少存在一个可以预约的座位。
//每一次对 unreserve 的调用，题目保证 seatNumber 在调用函数前都是被预约状态。
//对 reserve 和 unreserve 的调用 总共 不超过 105 次。
public class Problem_1845_SeatManager {

    //思路与算法
    //根据 提示，假设我们使用数据结构 available 来维护所有可以预约的座位，我们需要分析 reserve 与 unreserve 的具体需求：
    //对于 reserve 方法，我们需要弹出并返回 available 中的最小元素；
    //对于 unreserve 方法，我们需要将 seatNumber 添加至 available 中。
    //因此我们可以使用二叉堆实现的优先队列作为 available。对于一个最小堆，可以在 O(logn) 的时间复杂度内完成单次「添加元素」与「弹出最小值」的操作。
    //
    //时间复杂度：O(n+(q1+q2)*log(n))
    //q1、q2分别为reserve、unreserve操作的次数
    //空间复杂度：O(n)，heap的空间开销。
    static class SeatManager {

        PriorityQueue<Integer> minheap;

        public SeatManager(int n) {
            minheap = new PriorityQueue<>();
            for (int i = 1; i <= n; i++) {
                minheap.offer(i); //将n个座位加入到minheap中
            }
        }

        public int reserve() {
            if (minheap.isEmpty()) {
                return -1;
            }
            return minheap.poll();
        }

        public void unreserve(int seatNumber) {
            minheap.offer(seatNumber);
        }
    }
}
