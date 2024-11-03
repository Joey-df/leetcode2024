package com.joey.newcoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.Map;

// 返回无序数组中正数和负数个数相等的最长子数组长度
// 给定一个无序数组arr，其中元素可正、可负、可0
// 求arr所有子数组中正数与负数个数相等的最长子数组的长度
// 测试链接 : https://www.nowcoder.com/practice/545544c060804eceaed0bb84fcd992fb
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class Problem_PositivesEqualsNegtivesLongestSubarray {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) in.nval;
            int[] arr = new int[n];
            for (int i = 0, num; i < n; i++) {
                in.nextToken();
                num = (int) in.nval;
                arr[i] = num;
            }
            out.println(compute(arr));
        }
        out.flush();
        out.close();
        br.close();
    }

    //将原数组转换为-1 0 1组成的数组，然后求累加和=0的最长子数组长度即可
    public static int compute(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = nums[i] > 0 ? 1 : (nums[i] < 0 ? -1 : 0);
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int ans = -1;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            if (map.containsKey(sum)) { //即找sum-0最早出现的位置
                ans = Math.max(ans, i - map.get(sum));
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i); //只记录最早出现的位置
            }
        }
        return ans;
    }

}
