package com.joey.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author pei.liu
 */
//658. 找到 K 个最接近的元素
//给定一个 排序好 的数组 arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。
//整数 a 比整数 b 更接近 x 需要满足：
//|a - x| < |b - x| 或者
//|a - x| == |b - x| 且 a < b
//
//示例 1：
//输入：arr = [1,2,3,4,5], k = 4, x = 3
//输出：[1,2,3,4]
//示例 2：
//输入：arr = [1,1,2,3,4,5], k = 4, x = -1
//输出：[1,1,2,3]
//
//提示：
//1 <= k <= arr.length
//1 <= arr.length <= 10^4
//arr 按 升序 排列
//-10^4 <= arr[i], x <= 10^4
public class Problem_0658_FindClosestElements {

    //找k的个数可以二分得到
    //如果x的个数 >= k个，返回k个x
    //否则如果 < k 个，比如m个，先加入m个x，l指向小于x的第一个位置，r指向>x的第一个位置
    //利用小根堆，每次加入 l r 位置的数，l--，r++，够k个返回
    public List<Integer> findClosestElements(int[] nums, int k, int x) {
        int n = nums.length;
        List<Integer> clt = new ArrayList<>();
        int right = left(nums, x + 1);
        int left = left(nums, x);
        int xCnt = right - left;
        for (int i = 0; i < Math.min(xCnt, k); i++) {
            clt.add(x);
        }
        if (xCnt >= k) {
            return clt;
        }
        //0：与x的差值绝对值
        //1：实际值
        //2：位置
        //3：方向 0 左， 1 右
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        int l = left - 1;
        int r = right;
        if (l >= 0) q.offer(new int[]{Math.abs(nums[l] - x), nums[l], l, 0});
        if (r < n) q.offer(new int[]{Math.abs(nums[r] - x), nums[r], r, 1});
        while (!q.isEmpty() && clt.size() < k) {
            int[] node = q.poll();
            clt.add(node[1]);
            if (node[3] == 0 && node[2] > 0) {
                int nextl = node[2] - 1;
                q.offer(new int[]{Math.abs(nums[nextl] - x), nums[nextl], nextl, 0});
            }
            if (node[3] == 1 && node[2] < n - 1) {
                int nextr = node[2] + 1;
                q.offer(new int[]{Math.abs(nums[nextr] - x), nums[nextr], nextr, 1});
            }
        }
        clt.sort(Integer::compare);
        return clt;
    }

    public static int left(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        int ans = nums.length;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] >= target) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

}
