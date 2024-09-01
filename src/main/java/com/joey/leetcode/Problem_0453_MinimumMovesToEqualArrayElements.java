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

    //假设最后每个元素都变成x，则最小值每次都要+1
    //数组中的最小值为min，则操作次数为x-min
    //初始数组的累加和为sum
    //假设需要操作m次
    //sum + m*(n-1) = x*n
    //m=x-min
    //带入：sum + (x-min)*(n-1) = x*n
    //sum -x -min*n +min = 0
    // x = sum - min*(n-1)
    // ans = x-min
    //     = sum -min*n +min -min
    //     = sum -min*n
    public int minMoves(int[] nums) {
        int n = nums.length;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for (int num: nums) {
            sum += num;
            min = Math.min(min, num);
        }
        return sum - min * n;
    }

}
