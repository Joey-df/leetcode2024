package com.joey.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pei.liu
 */
//2364. 统计坏数对的数目
//给你一个下标从 0 开始的整数数组 nums 。如果 i < j 且 j - i != nums[j] - nums[i] ，那么我们称 (i, j) 是一个 坏数对 。
//请你返回 nums 中 坏数对 的总数目。
//示例 1：
//输入：nums = [4,1,3,3]
//输出：5
//解释：数对 (0, 1) 是坏数对，因为 1 - 0 != 1 - 4 。
//数对 (0, 2) 是坏数对，因为 2 - 0 != 3 - 4, 2 != -1 。
//数对 (0, 3) 是坏数对，因为 3 - 0 != 3 - 4, 3 != -1 。
//数对 (1, 2) 是坏数对，因为 2 - 1 != 3 - 1, 1 != 2 。
//数对 (2, 3) 是坏数对，因为 3 - 2 != 3 - 3, 1 != 0 。
//总共有 5 个坏数对，所以我们返回 5 。
//示例 2：
//输入：nums = [1,2,3,4,5]
//输出：0
//解释：没有坏数对。
//
//提示：
//1 <= nums.length <= 10^5
//1 <= nums[i] <= 10^9
//1814的变形题
public class Problem_2364_CountNumberOfBadPairs {

    //求解满足j - i != nums[j] - nums[i]的个数
    //推倒出
    //求解满足nums[i] - i != nums[j] - j的个数
    //对于i < j组成的数对总数all可以直接算出来
    //必须以i位置作为第二个数的情况如下
    //i=0: 0对
    //i=1：1对 (0,1)
    //i=2：2对 (0,2) (1,2)
    //i=3：3对 (0,3) (1,3) (2,3)
    //i=n-1：n-1对
    //总共：0+1+2+3+...+n-1，即n*(n-1)/2对
    //满足nums[i] - i == nums[j] - j的个数称为好对数的话
    //坏对数=all-好对数
    //好对数可以使用1814的方法求解
    //至此，得到原问题的答案
    public long countBadPairs(int[] nums) {
        int n=nums.length;
        Map<Integer, Long> map = new HashMap<>();
        long good = 0;
        long all = 0;
        for (int i = 0; i < n; i++) {
            int curr = nums[i] - i;
            //curr这个key在此之前出现过 M 次，和当前位置结合，得到M个
            long same = map.getOrDefault(curr, 0L);
            good += same;
            all += i; //当前位置作为第二个数的姿态，总共的数对个数有 i 个
            map.put(curr, same + 1);
        }
        return all - good;
    }

    public long countBadPairs1(int[] nums) {
        int n=nums.length;
        Map<Integer, Long> map = new HashMap<>();
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int curr = nums[i] - i;
            //curr这个key在此之前出现过 M 次，和当前位置结合，得到M个
            long same = map.getOrDefault(curr, 0L);
            //当前位置作为第二个数的姿态，总共的数对个数有 i 个
            ans += i - same; //当前i位置的总数 - 当前i位置的好数对数量
            map.put(curr, same + 1);
        }
        return ans;
    }

}
