package com.joey.top_hot;

// ignore
//48. 旋转图像
//给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
//你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
public class Problem_0048_RotateImage {

    public void rotate(int[][] matrix) {
        int leftTopRow = 0;//左上角的行号
        int leftTopCol = 0;//左上角的列号
        int rightDownRow = matrix.length - 1;//右下角的行号
        int rightDownCol = matrix[0].length - 1;//右下角的列号
        while (leftTopRow < rightDownRow) { //相等时是中心点，停
            printCircle(matrix, leftTopRow++, leftTopCol++, rightDownRow--, rightDownCol--);
        }
    }

    //打印一圈
    private void printCircle(int[][] matrix, int a, int b, int c, int d) {
        for (int i = 0; i < d - b; i++) {//组数
            int temp = matrix[a][b + i];
            matrix[a][b + i] = matrix[c - i][b];
            matrix[c - i][b] = matrix[c][d - i];
            matrix[c][d - i] = matrix[a + i][d];
            matrix[a + i][d] = temp;
        }
    }

}
