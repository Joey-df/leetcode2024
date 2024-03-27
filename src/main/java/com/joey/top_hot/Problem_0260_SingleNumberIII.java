package com.joey.top_hot;

//260. 只出现一次的数字 III
//给你一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。
//找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。
//你必须设计并实现线性时间复杂度的算法且仅使用常量额外空间来解决此问题。
public class Problem_0260_SingleNumberIII {

    public int[] singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) return new int[2];
        int eor = 0;
        for (int num : nums) eor ^= num;
        //eor=两个出现奇数次的数的 ^ 结果
        int a = 0;
        int rightOne = eor & -eor;
        for (int num : nums) {
            if ((rightOne & num) == 0) {
                a ^= num;
            }
        }
        return new int[]{a, a ^ eor};
    }

}
