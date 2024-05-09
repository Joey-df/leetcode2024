package com.joey.top_hot;

// ignore
/**
 * 73. 矩阵置零
 * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
 */
public class Problem_0073_SetMatrixZeroes {

    public static void setZeroes(int[][] matrix) {
        boolean row0Zero = false; //控制0行是否变0
        boolean col0Zero = false; //控制0列是否变0
        int i = 0;
        int j = 0;
        //遍历第0行，如果出现0，说明第0行要全部变0
        for (i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                row0Zero = true;
                break;
            }
        }
        //遍历第0列，如果出现0，说明第0列要全部变0
        for (i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                col0Zero = true;
                break;
            }
        }
        //遍历整个矩阵，使用0行和0列进行变0的行列标记
        for (i = 1; i < matrix.length; i++) {
            for (j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0; //i行的第0列标记为0，说明改行要全部变0
                    matrix[0][j] = 0; //0行的第j列标记为0，说明改列要全部变0
                }
            }
        }
        //根据上一步的标记，进行变0操作
        for (i = 1; i < matrix.length; i++) {
            for (j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (row0Zero) {
            for (i = 0; i < matrix[0].length; i++) {
                matrix[0][i] = 0;
            }
        }
        if (col0Zero) {
            for (i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }

}