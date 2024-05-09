package com.joey.top_hot;

/**
 * 169. 多数元素
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 * 示例 1：
 * 输入：[3,2,3]
 * 输出：3
 * <p>
 * 示例 2：
 * 输入：[2,2,1,1,1,2,2]
 * 输出：2
 */
public class Problem_0169_MajorityElement {

    //建立一种一次删除两种不同数的机制
    //最后剩下来的数就是要找的数
    //前提：the array is non-empty and the majority element always exist in the array.
    public static int majorityElement(int[] nums) {
        assert (nums != null && nums.length > 0);
        int cand = 0; //靶子
        int HP = 0; //0表示没有没有把子
        for (int num : nums) {
            if (HP == 0) { //没有靶子，立靶子
                cand = num;
                HP = 1;
            } else if (cand == num) {
                HP++;
            } else { //cand != num
                HP--;
            }
        }
        return cand;
    }

}
