package com.joey.top_hot;

//55. 跳跃游戏
//给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
//数组中的每个元素代表你在该位置可以跳跃的最大长度。
//判断你是否能够到达最后一个下标。
//示例 1：
//输入：nums = [2,3,1,1,4]
//输出：true
//解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
//示例 2：
//输入：nums = [3,2,1,0,4]
//输出：false
//解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
//提示：
//1 <= nums.length <= 3 * 10^4
//0 <= nums[i] <= 10^5
public class Problem_0055_JumpGame {

    public boolean canJump(int[] arr) {
        if (arr == null || arr.length == 0) return false;
        int n = arr.length;
        int max = arr[0]; //目前位置能跳到的最右位置
        for (int i = 1; i < n; i++) {
            if (max >= n - 1) {
                return true;
            }
            //如果目前能跳到的最右位置 到不了 当前位置i，返回false
            if (max < i) {
                return false;
            }
            max = Math.max(max, i + arr[i]);
        }
        return true;
    }

}
