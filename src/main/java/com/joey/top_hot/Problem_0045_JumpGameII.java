package com.joey.top_hot;

// ignore
//45. 跳跃游戏 II
//给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
//数组中的每个元素代表你在该位置可以跳跃的最大长度。
//你的目标是使用 最少的跳跃次数 到达数组的最后一个位置。
//假设你总是可以到达数组的最后一个位置。
//示例 1:
//输入: nums = [2,3,1,1,4]
//输出: 2
//解释: 跳到最后一个位置的最小跳跃数是 2。
//     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
//示例 2:
//输入: nums = [2,3,0,1,4]
//输出: 2
//提示:
//1 <= nums.length <= 10^4
//0 <= nums[i] <= 1000
public class Problem_0045_JumpGameII {

    public static int jump(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int step = 0; //当前至少跳几步能到i位置
        int cur = 0; //跳的步数不超过step的情况下，最右能到哪
        int next = arr[0]; //跳的步数不超过step+1的情况下，最右能到哪
        for (int i = 1; i < arr.length; i++) {
            //过滤：跳的步数不超过step+1的情况下，最右能到最后，直接返回
            if(next >= arr.length - 1){
                return step + 1;
            }
            //跳的步数不超过step的情况下到不了i位置，必须增加步数 step++
            //增加步数之后，cur更新为next
            if (cur < i) {
                step++;
                cur = next;
            }
            //每一步看能不能推高next
            next = Math.max(next, i + arr[i]);
        }
        return step;
    }

}
