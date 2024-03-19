package com.joey.top_hot;

import java.util.HashMap;
import java.util.Map;

/**
 * 560. 和为K的子数组
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 *
 * 示例 1 :
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 *
 * 说明 :
 * 数组的长度为 [1, 20,000]。
 * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 */
//算法原型：数组累加和问题三连的第二连
public class Problem_0560_SubarraySumEqualsK {

    //显然的结论：
    //[0,R]的累加和为sum，[0,L]的累加和为sum2，则[L+1,R]的累加和为sum-sum2
    public static int subarraySum(int[] nums, int k) {
        if (nums==null || nums.length==0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>(); //<累加和，出现的次数>
        map.put(0,1); //very important
        int sum = 0; //[0...i]的累加和
        int ans = 0;
        for (int i=0; i<nums.length; i++) {
            sum += nums[i];
            //比如k=200，当前i位置累加和sum=1000，查800这个累加和出现的次数，就是当前i位置结尾达标的子数组个数
            if (map.containsKey(sum-k)) {
                ans += map.get(sum-k); //以当前i位置结尾的子数组个数
            }
            if (!map.containsKey(sum)) {
                map.put(sum, 1);
            } else {
                map.put(sum, map.get(sum) + 1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1};
        int k = 2;
        System.out.println(subarraySum(nums, k));
    }
}
