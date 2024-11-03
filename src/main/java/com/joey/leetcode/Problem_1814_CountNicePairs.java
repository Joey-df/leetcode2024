package com.joey.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pei.liu
 */
//1814. 统计一个数组中好对子的数目
//给你一个数组 nums ，数组中只包含非负整数。定义 rev(x) 的值为将整数 x 各个数字位反转得到的结果。
//比方说 rev(123) = 321 ， rev(120) = 21 。我们称满足下面条件的下标对 (i, j) 是 好的 ：
//0 <= i < j < nums.length
//nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
//请你返回好下标对的数目。由于结果可能会很大，请将结果对 109 + 7 取余 后返回。
//
//示例 1：
//输入：nums = [42,11,1,97]
//输出：2
//解释：两个坐标对为：
// - (0,3)：42 + rev(97) = 42 + 79 = 121, 97 + rev(42) = 97 + 24 = 121 。
// - (1,2)：11 + rev(1) = 11 + 1 = 12, 1 + rev(11) = 1 + 11 = 12 。
//示例 2：
//输入：nums = [13,10,35,24,76]
//输出：4
//
//提示：
//1 <= nums.length <= 10^5
//0 <= nums[i] <= 10^9
public class Problem_1814_CountNicePairs {

    public static int mod = 1000000007;

    //nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
    //推倒出
    //nums[i] - rev(nums[i]) == nums[j] - rev(nums[j])
    //使用HashMap
    public static int countNicePairs(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int curr = nums[i] - rev(nums[i]);
            int same = map.getOrDefault(curr, 0); //curr这个key之间出现了几次
            //求解当前i位置的答案
            //来到每一个i位置，看当前位置的key在之前出现过几次，和当前位置配对
            ans = (ans + same + mod) % mod;
            map.put(curr, same + 1); //维护map
        }
        return ans;
    }

    public static int rev(int num) {
        int ans = 0;
        while (num != 0) {
            int curr = num % 10;
            ans = ans * 10 + curr;
            num /= 10;
        }
        return ans;
    }

}
