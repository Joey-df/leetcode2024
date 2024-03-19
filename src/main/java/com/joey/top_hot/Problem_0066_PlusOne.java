package com.joey.top_hot;

/**
 * 66. 加一
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * <p>
 * 示例 1：
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 * <p>
 * 示例 2：
 * 输入：digits = [4,3,2,1]
 * 输出：[4,3,2,2]
 * 解释：输入数组表示数字 4321。
 * <p>
 * 示例 3：
 * 输入：digits = [0]
 * 输出：[1]
 */
public class Problem_0066_PlusOne {

    public static int[] plusOne(int[] digits) {
        int N = digits.length;
        if (digits[N - 1] < 9) {
            digits[N - 1]++;
            return digits;
        }
        //最后一位等于9
        digits[N - 1] = 0;
        for (int i = N - 2; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        int[] ans = new int[N + 1];
        ans[0] = 1;
        return ans;
    }

    public static int[] func(int[] nums) {
        assert (nums != null && nums.length > 0);
        int N = nums.length;
        for (int i = N - 1; i >= 0; i--) {
            if (nums[i] < 9) {
                nums[i]++;
                return nums;
            } else { //==9
                nums[i] = 0;
            }
        }
        //全是9
        int[] ans = new int[N + 1];
        ans[0] = 1;
        return ans;
    }
}
