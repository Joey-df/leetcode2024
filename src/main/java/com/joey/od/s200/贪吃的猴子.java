package com.joey.od.s200;

/**
 * @author pei.liu
 */

import java.util.Arrays;
import java.util.Scanner;

public class 贪吃的猴子 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int len = Integer.parseInt(sc.nextLine());
        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = Integer.parseInt(sc.nextLine());

        System.out.println(getResult(len, nums, n));
    }

    public static int getResult(int len, int[] nums, int n) {
        // 初始时，左边选择0个，因此左边选择的香蕉数为 0
        int leftSum = 0;

        // 初始时，右边选择n个，因此右边选择的香蕉数为 nums[len-n] ~ nums[len - 1] 这个n个元素之和
        int rightSum = 0;
        for (int i = len - n; i < len; i++) {
            rightSum += nums[i];
        }

        // 如果选择数n == len，即全选，此时直接返回初始rightSum
        if (len == n) {
            return rightSum;
        }

        // 如果不是全选
        // sum记录当前选择结果
        int sum = leftSum + rightSum;
        // ans记录所有选择结果中最大的
        int ans = sum;

        // l指向左边将要获得的，即左边获得一个
        int l = 0;
        // r指向右边将要失去的，即右边失去一个
        int r = len - n;

        while (l < n) {
            sum += (nums[l++] - nums[r++]);
            ans = Math.max(ans, sum);
        }

        return ans;
    }
}