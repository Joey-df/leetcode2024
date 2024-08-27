package com.joey.leetcode;

import java.util.Arrays;

/**
 * @author pei.liu
 */
//881. 救生艇
//给定数组 people 。people[i]表示第 i 个人的体重 ，船的数量不限，每艘船可以承载的最大重量为 limit。
//每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。
//返回 承载所有人所需的最小船数 。
public class Problem_0881_BoatsToSavePeople {

    // 2,3,4,5,6,7,8
    // limit=10
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int n = people.length;
        int l = 0;
        int r = n - 1;
        int ans = 0;
        while (l <= r) {
            int sum = l == r ? people[l] : people[l] + people[r];
            if (sum > limit) {
                ans++;
                r--;
            } else {
                l++;
                r--;
                ans++;
            }
        }
        return ans;
    }

}
