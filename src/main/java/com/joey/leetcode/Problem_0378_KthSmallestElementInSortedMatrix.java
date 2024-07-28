package com.joey.leetcode;

import java.util.PriorityQueue;

//378. 有序矩阵中第 K 小的元素
//给你一个 n x n 矩阵 matrix ，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
//请注意，它是 排序后 的第 k 小元素，而不是第 k 个 不同 的元素。
//你必须找到一个内存复杂度优于 O(n^2) 的解决方案。
//提示：
//n == matrix.length
//n == matrix[i].length
//1 <= n <= 300
//-10^9 <= matrix[i][j] <= 10^9
//题目数据 保证 matrix 中的所有行和列都按 非递减顺序 排列
//1 <= k <= n^2
public class Problem_0378_KthSmallestElementInSortedMatrix {

    private static class Node {
        int val;
        int row;
        int col;

        public Node(int v, int r, int c) {
            val = v;
            row = r;
            col = c;
        }
    }

    //思路：
    //因为行有序、列也有序，所以第一小的数是左上角[0,0]
    //第二小的数只可能来自于[0,1] 或 [1,0]
    //所以使用小根堆，弹出k次，就找到第k小的数了
    public static int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Node> heap = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
        int N = matrix.length;
        int M = matrix[0].length;
        boolean[][] visited = new boolean[N][M]; //不重复放的机制
        heap.add(new Node(matrix[0][0], 0, 0));
        visited[0][0] = true;
        int count = 0;
        Node cur = null;
        while (!heap.isEmpty()) {
            cur = heap.poll();
            //数够k个
            if (++count == k) {//等价于 count==k-1; count++
                break;
            }
            int row = cur.row;
            int col = cur.col;
            //把当前弹出元素的 下方、右方的元素，加入小根堆
            if (row + 1 < N && !visited[row + 1][col]) { //下方
                heap.add(new Node(matrix[row + 1][col], row + 1, col));
                visited[row + 1][col] = true;
            }
            if (col + 1 < M && !visited[row][col + 1]) { //右方
                heap.add(new Node(matrix[row][col + 1], row, col + 1));
                visited[row][col + 1] = true;
            }
        }
        return cur.val;
    }

}
