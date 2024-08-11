package com.joey.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pei.liu
 */

//952. 按公因数计算最大组件大小
//给定一个由不同正整数的组成的非空数组 nums ，考虑下面的图：
//有 nums.length 个节点，按从 nums[0] 到 nums[nums.length - 1] 标记；
//只有当 nums[i] 和 nums[j] 共用一个大于 1 的公因数时，nums[i] 和 nums[j]之间才有一条边。
//返回 图中最大连通组件的大小 。
//提示：
//1 <= nums.length <= 2 * 10^4
//1 <= nums[i] <= 10^5
//nums 中所有值都 不同
public class Problem_0952_LargestComponentSizeByCommonFactor {

    //质因子分解+并查集
    //对一个数进行质因子分解，复杂度O(根号N)
    //并查集O(1)
    //过一遍数组，对每个数进行质因子分解，并进行并查集的合并操作
    //最终复杂度O(根号nums中的最大值)*O(N)，在10的7次方以内

    public static int[] father;
    public static int[] size;
    public static Map<Integer, Integer> first; //first.get(i) = x 表示 i这个质因子首次出现在x位置

    public static void build(int n) {
        father = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            father[i] = i;
            size[i] = 1;
        }
        first = new HashMap<>();
    }

    public static int find(int i) {
        if (father[i] != i) {
            father[i] = find(father[i]);
        }
        return father[i];
    }

    public static void union(int x, int y) {
        int fx = find(x);
        int fy = find(y);
        if (fx != fy) {
            father[fx] = fy;
            size[fy] += size[fx];
        }
    }

    public static int maxSize() {
        int ans = 1;
        for (int s : size) {
            ans = Math.max(ans, s);
        }
        return ans;
    }

    public static int largestComponentSize(int[] nums) {
        int n = nums.length;
        build(n);
        for (int i = 0; i < n; i++) {
            int curr = nums[i];
            for (int j = 2; j * j <= curr; j++) {
                if (curr % j == 0) {
                    //说明j是一个质因子
                    if (first.containsKey(j)) {
                        int p = first.get(j); //j这个质因子首次出现在p位置
                        //p位置的数与i位置的数合并成一个集合
                        union(p, i);
                    } else {
                        first.put(j, i);
                    }
                    while (curr % j == 0) {
                        curr /= j;
                    }
                }
            }
            if (curr > 1) {
                //curr是剩下来的数
                //说明curr是一个质因子
                if (first.containsKey(curr)) {
                    int p = first.get(curr); //j这个质因子首次出现在p位置
                    //p位置的数与i位置的数合并成一个集合
                    union(p, i);
                } else {
                    first.put(curr, i);
                }
            }
        }
        return maxSize();
    }

}
