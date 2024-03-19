package com.joey.top_hot;

/**
 * 215. 数组中的第K个最大元素
 * 在未排序的数组中找到第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 *
 * 示例 2:
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 */
public class Problem_0215_KthLargestElementInAnArray {

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private static int[] partition(int[]arr, int l, int r) {
        int base = arr[r];
        int less=l-1;
        int more=r+1;
        int i=l;
        while (i<more) {
            if (arr[i]==base) {
                i++;
            } else if (arr[i] < base) {
                swap(arr, i++, ++less);
            } else {
                swap(arr, i, --more);
            }
        }
        return new int[]{less+1, more-1};
    }

    //在nums[l,r]范围上找位于index下标的数，返回
    private static int find(int[] nums, int l, int r, int index) {
        int[] range = partition(nums, l, r);
        if (index>=range[0] && index<=range[1]) {
            return nums[index];
        } else if(index<range[0]) {
            return find(nums, l, range[0]-1, index);
        }
        //index>range[1]
        return find(nums, range[1]+1, r, index);
    }

    public static int findKthLargest(int[] nums, int k) {
        return find(nums, 0, nums.length-1, nums.length-k);
    }

    public static void main(String[] args) {
        System.out.println(findKthLargest(new int[]{3,2,1,5,6,4}, 2));
    }
}
