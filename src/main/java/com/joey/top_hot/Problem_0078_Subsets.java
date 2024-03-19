package com.joey.top_hot;


import java.util.ArrayList;
import java.util.List;

/**
 * 78. 数组的所有子集(子序列)【无重复值】
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 示例 2：
 *
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *
 * 提示：
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 */
public class Problem_0078_Subsets {

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums==null || nums.length==0) {
            return ans;
        }
        ArrayList<Integer> path = new ArrayList<>();
        func(nums, 0, path, ans);
        return ans;
    }

    //递归含义
    //arr固定输入参数
    //index：当前来到index位置做决定
    //[0...index-1]已经做好决定了，沿途做的决定放在path里
    //ans收集答案使用
    private static void func(int[] arr, int index, ArrayList<Integer> path, List<List<Integer>> ans) {
        if (index==arr.length) {
            //表示[0...arr.length-1]已经做好决定了
            //此时path的状态就是一种答案，收集
            ans.add(new ArrayList<>(path));
        } else {
            //要 or 不要
            func(arr, index+1, path, ans);//不要index位置的字符
            //要arr[index]
            path.add(arr[index]);
            func(arr, index+1, path, ans);
            path.remove(path.size()-1);
        }
    }
}
