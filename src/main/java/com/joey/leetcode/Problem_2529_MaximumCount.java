package com.joey.leetcode;

/**
 * @author pei.liu
 */
//2529. 正整数和负整数的最大计数
//给你一个按 非递减顺序 排列的数组 nums ，返回正整数数目和负整数数目中的最大值。
//换句话讲，如果 nums 中正整数的数目是 pos ，而负整数的数目是 neg ，返回 pos 和 neg二者中的最大值。
//注意：0 既不是正整数也不是负整数。
//
//示例 1：
//输入：nums = [-2,-1,-1,1,2,3]
//输出：3
//解释：共有 3 个正整数和 3 个负整数。计数得到的最大值是 3 。
//示例 2：
//输入：nums = [-3,-2,-1,0,0,1,2]
//输出：3
//解释：共有 2 个正整数和 3 个负整数。计数得到的最大值是 3 。
//示例 3：
//输入：nums = [5,20,66,1314]
//输出：4
//解释：共有 4 个正整数和 0 个负整数。计数得到的最大值是 4 。
//
//提示：
//1 <= nums.length <= 2000
//-2000 <= nums[i] <= 2000
//nums 按 非递减顺序 排列。
public class Problem_2529_MaximumCount {

    public int maximumCount(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0] == 0 ? 0 : 1;
        }
        int left = left(nums, 0);
        int right = right(nums, 0);
        if (left == n - 1 && right == 0) {
            return 0;
        }
        int ans1 = n - left;
        int ans2 = right + 1;
        return Math.max(ans1, ans2);
    }

    //返回大于aim的最左位置
    public int left(int[] nums, int aim) {
        int n = nums.length;
        int l = 0, r = n - 1;
        int ans = r;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] > aim) {
                ans = m; //必须严格大于才收集
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    //返回小于aim的最右位置
    public int right(int[] nums, int aim) {
        int n = nums.length;
        int l = 0, r = n - 1;
        int ans = l;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] < aim) {
                ans = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }

}
