package com.joey.top_hot;

/**
 * 240. 搜索二维矩阵 II
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 * <p>
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 */
public class Problem_0240_SearchA2DMatrixII {
    //在 行有序 列有序 的矩阵中 找target
    //从右上角出发，当前数>target,往左找；
    //当前数<target,往下找；(row,col)越界了 停
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        //一开始，定位到右上角
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                row++; //如果 当前元素 < target，向下找
            } else {
                col--; //如果 当前元素 > target，向左找
            }
        }
        //如果越界了还没找到，说明不存在
        return false;
    }
}
