package com.joey.newcoder;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pei.liu
 */
// 返回无序数组中累加和为给定值的最长子数组长度
// 给定一个无序数组arr, 其中元素可正、可负、可0
// 给定一个整数aim
// 求arr所有子数组中累加和为aim的最长子数组长度
// 测试链接 : https://www.nowcoder.com/practice/36fb0fd3c656480c92b569258a1223d5
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class Problem_LongestSubarraySumEqualsAim {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) in.nval;
            int[] arr = new int[n];
            in.nextToken();
            int aim = (int) in.nval;
            for (int i = 0; i < n; i++) {
                in.nextToken();
                arr[i] = (int) in.nval;
            }
            out.println(compute(arr, aim));
        }
        out.flush();
        out.close();
        br.close();
    }

    //每个位置求一个答案，求全局max
    private static int compute(int[] arr, int aim) {
        int n = arr.length;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); //0这个累加和最早出现在-1位置
        int sum = 0;
        int ans = -1;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            if (map.containsKey(sum - aim)) {
                ans = Math.max(ans, i - map.get(sum - aim));
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i); //只设置最早出现的位置
            }
        }
        return ans;
    }
}
