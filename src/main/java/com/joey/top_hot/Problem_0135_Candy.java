package com.joey.top_hot;

/**
 * @author pei.liu
 */
//135. 分发糖果
//n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
//你需要按照以下要求，给这些孩子分发糖果：
//每个孩子至少分配到 1 个糖果。
//相邻两个孩子评分更高的孩子会获得更多的糖果。
//请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
//
//示例 1：
//输入：ratings = [1,0,2]
//输出：5
//解释：你可以分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果。
//示例 2：
//输入：ratings = [1,2,2]
//输出：4
//解释：你可以分别给第一个、第二个、第三个孩子分发 1、2、1 颗糖果。
//     第三个孩子只得到 1 颗糖果，这满足题面中的两个条件。
//
//提示：
//n == ratings.length
//1 <= n <= 2 * 10^4
//0 <= ratings[i] <= 2 * 10^4
public class Problem_0135_Candy {

    //给定分数数组arr，表示每个孩子的得分
    //每个孩子至少分到1颗糖，相邻孩子得分高的必须得到更多的糖
    //返回满足条件最少的糖数
    public static int candy(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        if (arr.length == 1) return 1;
        int N = arr.length;
        int[] left = new int[N];
        left[0] = 1;
        for (int i = 1; i < N; i++) {
            // 比左边大就+1，否则就归一
            left[i] = arr[i] > arr[i - 1] ? left[i - 1] + 1 : 1;
        }

        int[] right = new int[N];
        right[N - 1] = 1;
        for (int j = N - 2; j >= 0; j--) {
            // 比邮编大就+1，否则就归一
            right[j] = arr[j] > arr[j + 1] ? right[j + 1] + 1 : 1;
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            ans += Math.max(left[i], right[i]); // 取max是因为，只有取max才能保证双向的规则
        }
        return ans;
    }


    // 进阶问题：在原问题的基础上，增加一个原则：
    // 相邻的孩子间如果分数一样，分的糖果数必须一样
    // 返回至少需要分多少糖
    public static int followUp(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        if (arr.length == 1) return 1;
        int N = arr.length;
        int[] left = new int[N];
        //三个原则：
        //比左边大就加1
        //和左边一样就继承
        //比左边小就归1
        left[0] = 1;
        for (int i = 1; i < N; i++) {
            left[i] = arr[i] > arr[i - 1] ? left[i - 1] + 1
                    : (arr[i] == arr[i - 1] ? left[i - 1] : 1);
        }

        int[] right = new int[N];
        right[N - 1] = 1;
        for (int j = N - 2; j >= 0; j--) {
            right[j] = arr[j] > arr[j + 1] ? right[j + 1] + 1
                    : (arr[j] == arr[j + 1] ? right[j + 1] : 1);
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            ans += Math.max(left[i], right[i]);
        }
        return ans;
    }

}
