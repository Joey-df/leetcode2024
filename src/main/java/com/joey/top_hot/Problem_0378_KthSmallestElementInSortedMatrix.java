package com.joey.top_hot;

import java.util.PriorityQueue;

/**
 * 378. 有序矩阵中第 K 小的元素
 * 给你一个 n x n 矩阵 matrix ，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
 * 请注意，它是 排序后 的第 k 小元素，而不是第 k 个 不同 的元素。
 * <p>
 * 示例 1：
 * 输入：matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
 * 输出：13
 * 解释：矩阵中的元素为 [1,5,9,10,11,12,13,13,15]，第 8 小元素是 13
 * <p>
 * 示例 2：
 * 输入：matrix = [[-5]], k = 1
 * 输出：-5
 */
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
        boolean[][] set = new boolean[N][M]; //不重复放的机制
        heap.add(new Node(matrix[0][0], 0, 0));
        set[0][0] = true;
        int count = 0;
        Node cur = null;
        while (!heap.isEmpty()) {
            cur = heap.poll();
            if (++count == k) {//等价于 count==k-1; count++
                break;
            }
            int row = cur.row;
            int col = cur.col;
            if (row + 1 < N && !set[row + 1][col]) { //下方
                heap.add(new Node(matrix[row + 1][col], row + 1, col));
                set[row + 1][col] = true;
            }
            if (col + 1 < M && !set[row][col + 1]) { //右方
                heap.add(new Node(matrix[row][col + 1], row, col + 1));
                set[row][col + 1] = true;
            }
        }
        return cur.val;
    }

    public static void main(String[] args) {


        int[][] m = new int[][]{
                new int[]{1, 5, 9},
                new int[]{10, 11, 13},
                new int[]{12, 13, 15}
        };
        int k = 6;
        System.out.println(kthSmallest(m, k));
    }


}
