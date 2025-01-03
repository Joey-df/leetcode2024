package com.joey.leetcode;

import java.util.Arrays;

/**
 * @author pei.liu
 */
//2300. 咒语和药水的成功对数
//给你两个正整数数组 spells 和 potions ，长度分别为 n 和 m ，其中 spells[i] 表示第 i 个咒语的能量强度，potions[j] 表示第 j 瓶药水的能量强度。
//同时给你一个整数 success 。一个咒语和药水的能量强度 相乘 如果 大于等于 success ，那么它们视为一对 成功 的组合。
//请你返回一个长度为 n 的整数数组 pairs，其中 pairs[i] 是能跟第 i 个咒语成功组合的 药水 数目。
public class Problem_2300_SuccessfulPairs {

    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n = spells.length;
        int m = potions.length;
        Arrays.sort(potions);
        int[] ans = new int[n];
        int i = 0;
        for (int spell : spells) {
            int l = 0;
            int r = m - 1;
            int curr = m;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if ((long) potions[mid] * spell >= success) {
                    curr = mid;
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            ans[i++] = m - curr;
        }
        return ans;
    }

}
