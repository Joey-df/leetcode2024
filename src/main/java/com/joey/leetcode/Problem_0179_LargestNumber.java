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
        assert (nums != null && nums.length > 0);
        //先把整形数组变为字符串数组
        String[] arr = new String[nums.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = String.valueOf(nums[i]);
        }
        // 降序排序，按照字典顺序比较ab与ba
        Arrays.sort(arr, (a, b) -> (b + a).compareTo(a + b)); //字典序降序

        //处理异常case
        if ("0".equals(arr[0])) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for (String cur : arr) {
            sb.append(cur);
        }
        return sb.toString();
    }

}
