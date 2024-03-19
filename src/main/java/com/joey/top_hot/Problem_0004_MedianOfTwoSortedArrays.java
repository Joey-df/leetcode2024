package com.joey.top_hot;

//算法原型：无序数组中找第k大的数
public class Problem_0004_MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int len = n + m;
        int[] arr = new int[len];
        System.arraycopy(nums1, 0, arr, 0, n);
        System.arraycopy(nums2, 0, arr, 0 + n, m);
        if (len % 2 == 0) { //偶数
            return (double) (findK(arr, (len / 2) - 1) + findK(arr, (len / 2))) / 2;
        }
        //奇数长度
        return (double) findK(arr, len / 2);
    }

    //无序数组中找位于index位置的数
    private int findK(int[] arr, int index) {
        int n = arr.length;
        int l = 0;
        int r = n - 1;
        int[] range;
        int base;
        while (l < r) {
            base = arr[l + (int) (Math.random() * (r - l + 1))];
            range = partition(arr, l, r, base);
            if (index >= range[0] && index <= range[1]) {
                return arr[index];
            } else if (index < range[0]) {
                r = range[0] - 1;
            } else {
                l = range[1] + 1;
            }
        }
        //l==r时跳出
        return arr[l];
    }


    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private int[] partition(int[] arr, int l, int r, int base) {
        if (l > r) return new int[]{-1, -1};
        if (l == r) return new int[]{l, r};
        int less = l - 1;
        int more = r + 1;
        int i = l;
        while (i < more) {
            if (arr[i] == base) i++;
            else if (arr[i] < base) swap(arr, i++, ++less);
            else swap(arr, --more, i);
        }
        return new int[]{less + 1, more - 1};
    }
}
