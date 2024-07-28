package com.joey.leetcode;

//453. 最小操作次数使数组元素相等
//给定一个长度为 n 的 非空 整数数组，每次操作将会使 n - 1 个元素增加 1。找出让数组所有元素相等的最小操作次数。
//示例：
//输入：
//[1,2,3]
//输出：
//3
//解释：
//只需要3次操作（注意每次操作会增加两个元素的值）：
//[1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
//孪生题：leetcode 462
public class Problem_0453_MinimumMovesToEqualArrayElements {

    //数学解
    //int n = nums.length;
    //min: nums中的最小值
    //sum: 移动前的总和
    //总共操作m次: ans
    //每次操作使得n-1个数 +1
    //x: 经过m次操作，nums中的每个元素最终都会变成x （x = min+m）即最小值添加m次=x
    //sum + m * (n-1) = n * x
    //sum + m * (n-1) = n * (min+m)
    //sum - m = n * min
    //m = sum - n * min
    public int minMoves(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for (int num : nums) {
            min = Math.min(min, num);
            sum += num;
        }
        return sum - n * min;
    }

}
