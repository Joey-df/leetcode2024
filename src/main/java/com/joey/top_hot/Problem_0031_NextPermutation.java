package com.joey.top_hot;

//31. 下一个排列
//实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列（即，组合出下一个更大的整数）。
//如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
//必须 原地 修改，只允许使用额外常数空间。
//示例 1：
//输入：nums = [1,2,3]
//输出：[1,3,2]
//示例 2：
//输入：nums = [3,2,1]
//输出：[1,2,3]
//示例 3：
//输入：nums = [1,1,5]
//输出：[1,5,1]
//示例 4：
//输入：nums = [1]
//输出：[1]
//提示：
//1 <= nums.length <= 100
//0 <= nums[i] <= 100
public class Problem_0031_NextPermutation {

    //流程
    //从右往左(N-2开始)，找第一个降序的元素下标firstLess
    //从右往左(N-1开始)，找第一个比nums[firstLess]大的元素下标j
    //交换 firstLess, j 对应元素
    //将nums[firstLess+1...]整体反转
    //例如：
    //5 4 76321
    // 先从右往左找到第一个产生下降趋势的位置，即 4的位置， 即firstLess
    // 再从右往左找到第一个大于4的位置，即6的位置 j，firstLess和j位置交换
    //得到 5 6 74321，然后将 [firstLess+1～N-1]翻转，得到
    //5 6 12347
    public static void nextPermutation(int[] nums) {
        int N = nums.length;
        int firstLess = -1; //从右往左，第一个下降的位置
        for (int i = N - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                firstLess = i;
                break;
            }
        }
        if (firstLess < 0) { //最大的排列，下一个排列是最小的排列
            reverse(nums, 0, N - 1);
        } else {
            int rightClosestMore = -1;
            // 找最靠右的、同时比nums[firstLess]大的数，位置在哪
            // 这里其实也可以用二分优化，但是这种优化无关紧要了
            for (int i = N - 1; i > firstLess; i--) {
                if (nums[i] > nums[firstLess]) {
                    rightClosestMore = i;
                    break;
                }
            }
            swap(nums, firstLess, rightClosestMore);
            reverse(nums, firstLess + 1, N - 1);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] + arr[j];
        arr[j] = arr[i] - arr[j];
        arr[i] = arr[i] - arr[j];
    }

    private static void reverse(int[] arr, int L, int R) {
        while(L < R) {
            swap(arr, L++, R--);
        }
    }

}
