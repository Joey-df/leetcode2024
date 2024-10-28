package com.joey.leetcode;

import java.util.List;
import java.util.TreeSet;

/**
 * @author pei.liu
 */
//632. 最小区间
//你有 k 个 非递减排列 的整数列表。找到一个 最小 区间，使得 k 个列表中的每个列表至少有一个数包含在其中。
//我们定义如果 b-a < d-c 或者在 b-a == d-c 时 a < c，则区间 [a,b] 比 [c,d] 小。
//
//示例 1：
//输入：nums = [[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
//输出：[20,24]
//解释：
//列表 1：[4, 10, 15, 24, 26]，24 在区间 [20,24] 中。
//列表 2：[0, 9, 12, 20]，20 在区间 [20,24] 中。
//列表 3：[5, 18, 22, 30]，22 在区间 [20,24] 中。
public class Problem_0632_SmallestRange {

    static class Node {
        int v; // 值
        int i; // 来自的数组下标
        int j; // 来自下标i数组的位置j

        public Node(int v, int i, int j) {
            this.v = v;
            this.i = i;
            this.j = j;
        }
    }

    //有序表
    public int[] smallestRange(List<List<Integer>> nums) {
        TreeSet<Node> treeSet = new TreeSet<>((a, b) -> {
            if (a.v == b.v) {
                return a.i - b.i;
            } else {
                return a.v - b.v;
            }
        });
        //nums中每个小数组是有序的
        for (int i = 0; i < nums.size(); i++) { //先把每个小数组的第一个元素（最小的）放进去
            List<Integer> curr = nums.get(i);
            int v = curr.get(0);
            treeSet.add(new Node(v, i, 0));
        }

        int k = nums.size();
        int width = Integer.MAX_VALUE;
        int l = -1;
        int r = -1;
        while (treeSet.size() == k) {
            Node last = treeSet.last();
            Node first = treeSet.pollFirst();
            int currW = last.v - first.v;
            if (currW < width) {
                width = currW;
                l = first.v;
                r = last.v;
            }
            if (first.j + 1 < nums.get(first.i).size()) {
                treeSet.add(new Node(nums.get(first.i).get(first.j + 1), first.i, first.j + 1));
            }
        }
        return new int[]{l, r};
    }

}
