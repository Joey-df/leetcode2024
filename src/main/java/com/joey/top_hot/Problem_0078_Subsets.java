package com.joey.top_hot;


import java.util.ArrayList;
import java.util.List;

/**
 * 78. 数组的所有子集(子序列)【无重复值】
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 示例 2：
 * <p>
 * 输入：nums = [0]
 * 输出：[[],[0]]
 * <p>
 * 提示：
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 */
public class Problem_0078_Subsets {

    public List<List<Integer>> subsets(int[] arr) {
        List<List<Integer>> ans = new ArrayList<>();
        if (arr == null || arr.length == 0) return ans;
        fun(arr, 0, new ArrayList<>(), ans);
        return ans;
    }

    //递归含义
    //arr[0,index-1]已经做好决定了，不用再操心了，沿途的路径存放在path里
    //ans收集答案
    private void fun(int[] arr, int index, ArrayList<Integer> path, List<List<Integer>> ans) {
        if (index == arr.length) {
            //collect ans
            List<Integer> cur = new ArrayList<>();
            for (int i = 0; i < path.size(); i++) {
                cur.add(path.get(i));
            }
            ans.add(cur);
            return;
        }
        //index还没到末尾
        fun(arr, index + 1, path, ans); // 不要
        path.add(arr[index]);
        fun(arr, index + 1, path, ans); // 要
        path.remove(path.size() - 1); //clear
    }
}
