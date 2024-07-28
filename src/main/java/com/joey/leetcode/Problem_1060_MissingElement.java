package com.joey.leetcode;

/**
 * @author pei.liu
 */
//1060. 有序数组中的缺失元素
//给出一个有序数组 A，数组中的每个数字都是 独一无二的，找出从数组最左边开始的第 K 个缺失数字。
//示例 1：
//输入：A = [4,7,9,10], K = 1
//输出：5
//解释：
//第一个缺失数字为 5 。
//示例 2：
//输入：A = [4,7,9,10], K = 3
//输出：8
//解释：
//缺失数字有 [5,6,8,...]，因此第三个缺失数字为 8 。
//示例 3：
//输入：A = [1,2,4], K = 3
//输出：6
//解释：
//缺失数字有 [3,5,6,7,...]，因此第三个缺失数字为 6 。
//提示：
//1 <= A.length <= 50000
//1 <= A[i] <= 1e7
//1 <= K <= 1e8
public class Problem_1060_MissingElement {

    //一次遍历
    //相邻的数做差，进行判断，对 k 进行更新，直到 k <= 0 停止
    //A = [4,7,9,10], K = 3
    public static int missingElement(int[] nums, int k) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i + 1] - nums[i] - 1 < k) {
                k -= nums[i + 1] - nums[i] - 1;
            } else {
                return nums[i] + k;
            }
        }
        return nums[n - 1] + k;
    }

    public static int missingElement2(int[] nums, int k) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            //打到中点mid，计算[0,mid]范围上缺失数字的个数
            int miss = countMissing(nums, mid);
            //如果miss不够k个，说明缺失的数字在mid的右边
            if (miss < k) {
                l = mid + 1;
            } else if (miss > k) { //如果miss超过k个，说明缺失的数字在mid的左边
                r = mid - 1;
            } else {
                //如果miss==k，缺失的数字就是 nums[mid] - 1
                //比如[4, 7, 9, 10], mid=2, 缺失的数字是5 6 8总共3个，缺失的8就等于nums[2]-1
                return nums[mid] - 1;
            }
        }
        //比如 A = [1,2,4], K = 3
        return nums[r] + k - countMissing(nums, r);
    }

    //函数功能：[0,i]位置上缺失了几个数
    //比如[4, 7, 9, 10], i=2, 缺失的数字是5 6 8总共3个，等于9-4-2 = 3
    public static int countMissing(int[] nums, int i) {
        return nums[i] - nums[0] - i;
    }

    public static void main(String[] args) {
        System.out.println(missingElement2(new int[]{4, 7, 9, 10}, 3));
    }
}
