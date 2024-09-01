package com.joey.leetcode;

import java.util.Arrays;

//179. 最大数
//给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
//<p>
//注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
//
//示例 1：
//输入：nums = [10,2]
//输出："210"
//示例 2：
//输入：nums = [3,30,34,5,9]
//输出："9534330"
//示例 3：
//输入：nums = [1]
//输出："1"
//示例 4：
//输入：nums = [10]
//输出："10"
public class Problem_0179_LargestNumber {

    public static String largestNumber(int[] nums) {
        int n = nums.length;
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(arr, (a, b) -> (b+a).compareTo(a+b));
        if (arr[0].equals("0")) return "0";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(arr[i]);
        }
        return sb.toString();
    }

}
