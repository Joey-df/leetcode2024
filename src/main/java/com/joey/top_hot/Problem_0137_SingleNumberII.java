package com.joey.top_hot;

/**
 * 137. 只出现一次的数字 II
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。
 * 请你找出并返回那个只出现了一次的元素。
 *
 * 示例 1：
 * 输入：nums = [2,2,3,2]
 * 输出：3
 *
 * 示例 2：
 * 输入：nums = [0,1,0,1,0,1,99]
 * 输出：99
 */
public class Problem_0137_SingleNumberII {
    public static int singleNumber(int[] nums) {
        return func(nums, 3, 1);
    }

    //数组中只有一种元素出现n次，其他元素都出现k次，k>n
    //找出出现n次的数
    public static int func(int[] nums, int k, int n) {
        assert (nums != null && nums.length > 0 && k > n && n >= 1);
        int[] count = new int[32];
        for (int num : nums) {
            for (int i = 0; i <= 31; i++) { //每个二进制位，包括符号位
                //count[i] += ((num >> i) & 1);
                count[i] += ((num & (1 << i)) != 0) ? 1 : 0; //取出num二进制数第i位的状态（1或0）
            }
        }
        for (int i = 0; i < 32; i++) {
            count[i] = (count[i] % k) / n;
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            ans |= (count[i] << i);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5, 5, 5, -6, -6, -6, -7};
        System.out.println(singleNumber(nums));
    }
}
