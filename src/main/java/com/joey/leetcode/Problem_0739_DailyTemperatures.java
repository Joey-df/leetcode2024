package com.joey.leetcode;


import java.util.Iterator;
import java.util.Stack;

/**
 * 739. 每日温度
 * 请根据每日 气温 列表 temperatures ，请计算在每一天需要等几天才会有更高的温度。
 * 如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * <p>
 * 示例 1:
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 * <p>
 * 示例 2:
 * 输入: temperatures = [30,40,50,60]
 * 输出: [1,1,1,0]
 * <p>
 * 示例 3:
 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 */
//单调栈
public class Problem_0739_DailyTemperatures {

    //求每个位置右边离自己最近比自己大的数，使用单调栈
    //单调栈：自底向上，大 -> 小
    public int[] dailyTemperatures1(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) {
            return new int[0];
        }
        int n = temperatures.length;
        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>(); //存下标
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) { //违反 大 -> 小
                int curi = stack.pop();
                ans[curi] = i - curi;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) { //可以省略，因为数组初始化就是0
            ans[stack.pop()] = 0;
        }
        return ans;
    }

    public static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] help = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temperatures[i] >= temperatures[stack.peek()]) {
                int curri = stack.pop();
                help[curri] = i;
            }
            stack.push(i);
        }
        //清算
        while (!stack.isEmpty()) {
            int curri = stack.pop();
            help[curri] = -1;
        }
        //修正
        for (int i = n - 1; i >= 0; i--) {
            if (help[i] != -1 && temperatures[i] == temperatures[help[i]]) {
                help[i] = help[help[i]];
            }
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = help[i] == -1 ? 0 : help[i] - i;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1,2,4,4,6};
        System.out.println(dailyTemperatures(arr));
    }
}
