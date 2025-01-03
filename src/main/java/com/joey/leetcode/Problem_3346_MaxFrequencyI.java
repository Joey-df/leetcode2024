package com.joey.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pei.liu
 */
//3346. 执行操作后元素的最高频率 I
//给你一个整数数组 nums 和两个整数 k 和 numOperations 。
//你必须对 nums 执行 操作  numOperations 次。每次操作中，你可以：
//选择一个下标 i ，它在之前的操作中 没有 被选择过。
//将 nums[i] 增加范围 [-k, k] 中的一个整数。
//在执行完所有操作以后，请你返回 nums 中出现 频率最高 元素的出现次数。
//一个元素 x 的 频率 指的是它在数组中出现的次数。
//提示：
//1 <= nums.length <= 10^5
//1 <= nums[i] <= 10^5
//0 <= k <= 10^5
//0 <= numOperations <= nums.length
public class Problem_3346_MaxFrequencyI {

    //对数组进行排序
    //遍历数组nums，到每个i位置，对当前数：
    //找>=当前数最左的位置l
    //找<=当前数最右的位置r
    //当前i位置的答案：min(r-l+1，numOperations)
    //含义：这个范围上的数，每次增加[-k,k]之间的的一个值，会变的和当前数一样
    //每个位置的答案求全局max
    public static int maxFrequency(int[] nums, int k, int numOperations) {
        Arrays.sort(nums);
        int n = nums.length;
        Map<Integer, Integer> cnts = new HashMap<>();
        int maxCnt = 0;
        for (int i = 0; i < n; i++) {
            cnts.put(nums[i], cnts.getOrDefault(nums[i],0)+1);
            maxCnt = Math.max(maxCnt, cnts.get(nums[i]));
        }
        if (numOperations == 0) {
            return maxCnt;
        }
        int ans =Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int l = left(nums,0,n-1, nums[i]-k);
            int r = right(nums,0,n-1, nums[i]+k);

            int l2 = left(nums,0,n-1, nums[i]);
            int r2 = right(nums,0,n-1, nums[i]+2*k);
            //只减少
            int l3 = left(nums,0,n-1, nums[i]-2*k);
            int r3 = right(nums,0,n-1, nums[i]);
            System.out.printf("i=%d, l=%d, r=%d, l2=%d, r2=%d\n",i, l, r, l2,r2);
            if (l!=-1 && r!=-1) {
                ans = Math.max(ans,Math.min(numOperations,r - l +1 - cnts.get(nums[i])) + cnts.get(nums[i]));
            }
            if (l2!=-1 && r2!=-1) {
                ans = Math.max(ans,Math.min(numOperations,Math.min(r2 - l2+1,numOperations)));
            }
            if (l3!=-1 && r3!=-1) {
                ans = Math.max(ans,Math.min(numOperations,Math.min(r3 - l3+1,numOperations)));
            }
        }
        return ans;
    }

    public static int left(int[] nums, int l, int r, int target) {
        int ans = -1;
        while (l<=r) {
            int m=l+(r-l) /2;
            if (nums[m] >= target) {
                ans=m;
                r=m-1;
            } else {
                l=m+1;
            }
        }
        return ans;
    }

    public static int right(int[] nums, int l, int r, int target) {
        int ans = -1;
        while (l<=r) {
            int m=l+(r-l) /2;
            if (nums[m] <= target) {
                ans=m;
                l=m+1;
            } else {
                r=m-1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {53,88};
        int k=27;
        int op = 2;
        System.out.println(maxFrequency(nums, k ,op));
    }

}
