package com.joey.top_hot;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * 239. 滑动窗口最大值
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 * 示例 1：
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * 示例 2：
 * 输入：nums = [1], k = 1
 * 输出：[1]
 * 示例 3：
 * 输入：nums = [1,-1], k = 1
 * 输出：[1,-1]
 * 示例 4：
 * 输入：nums = [9,11], k = 2
 * 输出：[11]
 * 示例 5：
 * 输入：nums = [4,-2], k = 2
 * 输出：[4]
 */
public class Problem_0239_SlidingWindowMaximum {

    //会Time Limit Exceeded
    public int[] maxSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> maxpq = new PriorityQueue<>(Collections.reverseOrder());
        int n = nums.length;
        if (k > n) return new int[0];
        int m = n - k + 1;
        int[] ans = new int[m];
        for (int i = 0; i < n; i++) {
            maxpq.offer(nums[i]);
            //i>=k-1的时候收集答案
            if (i >= k - 1) {
                ans[i - k + 1] = maxpq.peek();
                maxpq.remove(nums[i - k + 1]);
            }
        }
        return ans;
    }

    //nums = [1,3,-1,-3,5,3,6,7], k = 3
    public static int[] maxSlidingWindow2(int[] nums, int k) {
        int L = 0, R = 0;
        LinkedList<Integer> dq = new LinkedList<>(); //严格大-->小，存下标
        int[] ans = new int[nums.length - k + 1];
        int index = 0;
        //[L,R) 初始为[0,0) 即窗口内一个元素也没有
        while (R < nums.length) {
            //元素从尾部进入双端队列
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[R]) { //R位置的元素要进窗口
                dq.pollLast();
            }
            //while退出来的状态为 队列为空，或者队尾元素大于 > nums[R]
            dq.offerLast(R);
            if (R - L == k - 1) { //先让窗口涨到k的长度
                ans[index++] = nums[dq.peekFirst()];
                if (dq.peekFirst() == L) dq.pollFirst();
                L++;
            }
            R++;
        }
        return ans;
    }
}
