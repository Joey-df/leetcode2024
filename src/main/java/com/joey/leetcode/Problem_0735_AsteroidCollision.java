package com.joey.leetcode;

import java.util.Stack;

/**
 * @author pei.liu
 */
//735. 小行星碰撞
//给定一个整数数组 asteroids，表示在同一行的小行星。
//对于数组中的每一个元素，其绝对值表示小行星的大小，正负表示小行星的移动方向（正表示向右移动，负表示向左移动）。
// 每一颗小行星以相同的速度移动。
//找出碰撞后剩下的所有小行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。
// 如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。
//
//示例 1：
//输入：asteroids = [5,10,-5]
//输出：[5,10]
//解释：10 和 -5 碰撞后只剩下 10 。 5 和 10 永远不会发生碰撞。
//示例 2：
//输入：asteroids = [8,-8]
//输出：[]
//解释：8 和 -8 碰撞后，两者都发生爆炸。
//示例 3：
//输入：asteroids = [10,2,-5]
//输出：[10]
//解释：2 和 -5 发生碰撞后剩下 -5 。10 和 -5 发生碰撞后剩下 10 。
//示例 4：
//输入：asteroids = [-2,-1,1,2]
//输出：[-2,-1,1,2]
//解释：-2 和 -1 向左移动，而 1 和 2 向右移动。 由于移动方向相同的行星不会发生碰撞，所以最终没有行星发生碰撞。
//
//提示：
//2 <= asteroids.length <= 10^4
//-1000 <= asteroids[i] <= 1000
//asteroids[i] != 0
public class Problem_0735_AsteroidCollision {

    //思路：使用一个栈
    //当前元素为正数：直接入栈
    //如果当前元素为负数，需要分情况
    // 1.先循环弹栈，直到Math.abs(asteroid) < 栈顶元素为止
    // 2.如果栈为空 或者 栈顶元素为负数，直接入栈；
    //   否则栈不为空，并且栈顶元素为正数，如果绝对值相等，栈顶出栈
    public int[] asteroidCollision(int[] asteroids) {
        if (asteroids == null || asteroids.length == 0) return new int[]{};
        Stack<Integer> stack = new Stack<>();
        for (int asteroid : asteroids) { // num != 0
            if (asteroid == 0) continue;
            //当前元素如果为正数：直接入栈
            if (asteroid > 0) {
                stack.push(asteroid);
            } else { // 当前元素为负数
                //比如栈里：10 5 2，当前元素是-6，那么2 5依次出栈，-6也不用再入栈了
                while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < Math.abs(asteroid)) {
                    stack.pop();
                }
                if (stack.isEmpty() || stack.peek() < 0) {
                    stack.push(asteroid);
                } else if (stack.peek() == Math.abs(asteroid)) {
                    // 栈顶元素大于0, 如果绝对值相等，栈顶出栈（例如栈顶是8，当前元素是-8），如果栈顶绝对值大，当前元素抛弃（例如栈顶是10，当前元素是-6）
                    stack.pop();
                }
            }
        }
        int[] ans = new int[stack.size()];
        for (int i = ans.length - 1; i >= 0; i--) {
            ans[i] = stack.pop();
        }
        return ans;
    }
}
