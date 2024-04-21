package com.joey.od.s200;

/**
 * @author pei.liu
 */

import java.util.Scanner;

public class 园区参观路径 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt(); // 长 -> 行数
        int n = sc.nextInt(); // 宽 -> 列数

        int[][] grid = new int[m][n]; // 地图矩阵
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        //dp[i][j]的含义：机器人从[0,0]出发，到达[i,j]位置的方法数是多少？
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0] == 0 ? 1 : 0;
        //第一行
        for (int j = 1; j < n; j++) {
            dp[0][j] = (dp[0][j - 1] == 1 && grid[0][j] == 0) ? 1 : 0;
        }
        //第一列
        for (int i = 1; i < m; i++) {
            dp[i][0] = (dp[i - 1][0] == 1 && grid[i][0] == 0) ? 1 : 0;
        }
        //普遍位置
        //从左往右，再依次从上往下
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (grid[i][j] == 0) {
                    int fromUp = grid[i - 1][j] == 0 ? dp[i - 1][j] : 0;
                    int fromLeft = grid[i][j - 1] == 0 ? dp[i][j - 1] : 0;
                    dp[i][j] = fromUp + fromLeft;
                }
                //grid[i][j] == 1 时， dp[i][j] = 0
                //因为java中数组默认值就是0，所以不用再单独写
            }
        }
        System.out.println(dp[m - 1][n - 1]);
    }
}