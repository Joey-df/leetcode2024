package com.joey.top_hot;

import java.util.*;

/**
 * 46. 全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 示例 2：
 * <p>
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * 示例 3：
 * <p>
 * 输入：nums = [1]
 * 输出：[[1]]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 */
public class Problem_0046_Permutations {

    public List<List<Integer>> permute(int[] arr) {
        List<List<Integer>> ans = new ArrayList<>();
        if (arr == null || arr.length == 0) return ans;
        fun(arr, 0, ans);
        return ans;
    }

    //arr固定输入数组
    //当前来到index位置，[0,index-1]范围已经搞定了，形成的路径就存放在arr中
    //这个题不需要额外的数据结构存放path，就用arr存放路径
    //ans，收集答案
    public void fun(int[] arr, int index, List<List<Integer>> ans) {
        if (index == arr.length) {
            //collect ans
            ArrayList<Integer> cur = new ArrayList<>();
            for (int n : arr) cur.add(n);
            ans.add(cur);
            return;
        }
        //主逻辑
        //index位置还有字符
        //[index,len-1]的所有字符均有机会来带index位置
        for (int i = index; i < arr.length; i++) {
            swap(arr, index, i);
            fun(arr, index + 1, ans);
            swap(arr, index, i); // clear
        }
    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
