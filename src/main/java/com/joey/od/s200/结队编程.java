package com.joey.od.s200;

/**
 * @author pei.liu
 */

import java.util.Scanner;

public class 结队编程 {
    static long ans = 0; // 用于保存逆序对的数量，初始化为0

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 读取数组的长度

        int[] w = new int[n];

        // 读取数组元素
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }

        calculate(w); // 计算原数组的逆序对数量
        calculate(reverseArray(w)); // 计算反转数组的逆序对数量

        System.out.println(ans); // 输出总的逆序对数量
    }

    // 计算逆序对数量的方法
    static void calculate(int[] arr) {
        int n = arr.length;
        int[] rightBig = new int[n];
        int[] leftSmall = new int[n];

        // 计算每个元素左边比它小的元素个数
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    leftSmall[i]++;
                }
            }

            // 计算每个元素右边比它大的元素个数
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > arr[i]) {
                    rightBig[i]++;
                }
            }
        }

        // 计算逆序对数量
        for (int i = 0; i < n; i++) {
            ans += leftSmall[i] * rightBig[i];
        }
    }

    // 反转数组的方法
    static int[] reverseArray(int[] arr) {
        int n = arr.length;
        int[] reversed = new int[n];

        // 将数组反转
        for (int i = 0; i < n; i++) {
            reversed[i] = arr[n - i - 1];
        }

        return reversed;
    }
}

