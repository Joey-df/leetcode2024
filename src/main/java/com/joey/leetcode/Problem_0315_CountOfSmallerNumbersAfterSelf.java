package com.joey.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//315. 计算右侧小于当前元素的个数
//给定一个整数数组 nums，按要求返回一个新数组 counts。
//数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
//示例：
//输入：nums = [5,2,6,1]
//输出：[2,1,1,0]
//解释：
//5 的右侧有 2 个更小的元素 (2 和 1)
//2 的右侧仅有 1 个更小的元素 (1)
//6 的右侧有 1 个更小的元素 (1)
//1 的右侧有 0 个更小的元素
//提示：
//1 <= nums.length <= 10^5
//-10^4 <= nums[i] <= 10^4
public class Problem_0315_CountOfSmallerNumbersAfterSelf {

    private static class Node {
        int value;
        int index;
        int countOfSmallerAfterSelf;

        public Node(int value, int index) {
            this.value = value;
            this.index = index;
            this.countOfSmallerAfterSelf = 0;
        }
    }

    public static List<Integer> countSmaller(int[] nums) {
        Node[] wrapNums = new Node[nums.length];
        for (int i = 0; i < nums.length; i++) {
            wrapNums[i] = new Node(nums[i], i);
        }

        //接下来对wrapNums进行排序，排序过程中压榨出右边小于自己的个数
        sort(wrapNums, 0, wrapNums.length - 1);
        //至此 wrapNums中的原始是按照value排好序的，需要还原成原始nums中的顺序
        List<Integer> ans = new ArrayList<>();
        Arrays.sort(wrapNums, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.index - o2.index;
            }
        });
        for (int i = 0; i < wrapNums.length; i++) {
            ans.add(wrapNums[i].countOfSmallerAfterSelf);
        }
        return ans;
    }

    //对wrapNums[L,R]范围上进行归并排序
    private static void sort(Node[] wrapNums, int L, int R) {
        if (L >= R) return;
        //L>R
        int mid = L + ((R - L) >> 1);
        sort(wrapNums, L, mid);
        sort(wrapNums, mid + 1, R);
        merge(wrapNums, L, mid, R);
    }

    //对wrapNums[L,R]范围上进行merge
    //wrapNums[L,mid]，wrapNums[mid+1,R]范围上均已经有序了
    private static void merge(Node[] wrapNums, int l, int mid, int r) {
        Node[] help = new Node[r - l + 1];
        int idx = r - l;//help专用 初始指向最后一个位置
        //从右往左遍历，左边>右边的时候产生答案
        int p1 = mid, p2 = r;
        while (p1 >= l && p2 >= mid + 1) {
            if (wrapNums[p1].value > wrapNums[p2].value) { //左边大先拷贝左边的
                help[idx--] = wrapNums[p1];
                wrapNums[p1--].countOfSmallerAfterSelf += (p2 - mid);
            } else { //右边>=左边
                help[idx--] = wrapNums[p2--];
            }
        }
        while (p1 >= l) {
            help[idx--] = wrapNums[p1--];
        }

        while (p2 >= mid + 1) {
            help[idx--] = wrapNums[p2--];
        }

        for (int i = 0; i < help.length; i++) { //刷回去
            wrapNums[i + l] = help[i];
        }
    }


    public static void main(String[] args) {
        System.out.println(countSmaller(new int[]{5, 2, 6, 1}));
    }
}
