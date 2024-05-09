package com.joey.top_hot;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 347. 前 K 个高频元素
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 * 示例 1:
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 * 输入: nums = [1], k = 1
 * 输出: [1]
 */
public class Problem_0347_TopKFrequentElements {

    private static class Node {
        int val;
        int count;

        public Node(int v, int c) {
            val = v;
            count = c;
        }
    }

    //思路
    //先计算出词频，然后使用门槛堆，求出现频率前K大的元素，使用大小为K的小根堆
    public static int[] topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }
        Map<Integer, Node> map = new HashMap<>();
        for (int cur : nums) {
            if (!map.containsKey(cur)) {
                map.put(cur, new Node(cur, 1));
            } else {
                map.get(cur).count++;
            }
        }
        PriorityQueue<Node> heap = new PriorityQueue<>((o1, o2) -> o1.count - o2.count);
        for (int key : map.keySet()) {
            Node cur = map.get(key);
            if (heap.size() < k) {
                heap.add(cur);
            } else {
                if (cur.count > heap.peek().count) {
                    heap.poll(); //弹出堆顶
                    heap.add(cur);
                }
            }
        }

        int[] ans = new int[k];
        int index = 0;
        while (!heap.isEmpty()) {
            ans[index++] = heap.poll().val;
        }
        return ans;
    }
}
