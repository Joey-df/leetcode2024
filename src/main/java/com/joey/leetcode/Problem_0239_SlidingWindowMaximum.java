package com.joey.leetcode;

import java.util.LinkedList;

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

    //nums = [1,3,-1,-3,5,3,6,7], k = 3
    public static int[] maxSlidingWindow(int[] nums, int k) {
        //大->小
        LinkedList<Integer> q = new LinkedList<>(); //放下标
        int n = nums.length;
        int len = n - k + 1;
        int[] ans = new int[len];
        int l = 0;
        int r = 0;
        //先让窗口涨到k-1长度
        for (; r < k - 1; r++) {
            while (!q.isEmpty() && nums[q.peekLast()] <= nums[r]) { //违反大到小
                q.pollLast();
            }
            q.offerLast(r);
        }
        //枚举每一个开头(结尾)位置
        for (; r < n; l++, r++) {
            while (!q.isEmpty() && nums[q.peekLast()] <= nums[r]) {
                q.pollLast();
            }
            //进一个，收集答案，出一个
            q.offerLast(r);
            int h = q.peekFirst();
            ans[l] = nums[h];
            if (h == l) {
                q.pollFirst();
            }
        }
        return ans;
    }

}
