package com.joey.leetcode;

/**
 * 75. 颜色分类
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，
 * 使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * <p>
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * 示例 1：
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 * <p>
 * 示例 2：
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 * <p>
 * 示例 3：
 * 输入：nums = [0]
 * 输出：[0]
 * <p>
 * 示例 4：
 * 输入：nums = [1]
 * 输出：[1]
 */
public class Problem_0075_SortColors {
    //就是荷兰国旗问题
    public void sortColors(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int n = arr.length;
        int l = 0, r = n - 1;
        int less = l - 1;
        int more = r + 1;
        int i = l;
        int base = 1;
        while (i < more) {
            if (arr[i] == base) i++;
            else if (arr[i] < base) swap(arr, ++less, i++);
            else swap(arr, --more, i);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

}
