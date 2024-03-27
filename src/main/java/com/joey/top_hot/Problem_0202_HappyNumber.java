package com.joey.top_hot;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * 202. 快乐数
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * <p>
 * 「快乐数」定义为：
 * <p>
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果 可以变为  1，那么这个数就是快乐数。
 * 如果 n 是快乐数就返回 true ；不是，则返回 false 。
 */
public class Problem_0202_HappyNumber {

    //完全根据快乐数定义用代码模拟
    public boolean isHappy(int n) {
        //19
        Set<Integer> set = new HashSet<>();
        int num = n;
        while (num != 1) {
            int sum = 0;
            while (num != 0) {
                int d = num % 10; // 9
                sum += d * d;
                num = num / 10; // 1
            }
            num = sum; // 下一轮的总和：如82
            if (set.contains(num)) {
                break; // 出现了循环
            }
            set.add(num);
        }
        // num == 1 或者break跳出的
        return num == 1;
    }
}
