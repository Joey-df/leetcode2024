package com.joey.top_hot;

import java.util.Stack;

//402. 移掉K位数字让剩下的数字最小
//给你一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小。
//请你以字符串形式返回这个最小的数字。
//示例 1 ：
//输入：num = "1432219", k = 3
//输出："1219"
//解释：移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219 。
//示例 2 ：
//输入：num = "10200", k = 1
//输出："200"
//解释：移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
//示例 3 ：
//输入：num = "10", k = 2
//输出："0"
//解释：从原数字移除所有的数字，剩余为空就是 0 。
//提示：
//1 <= k <= num.length <= 10^5
//num 仅由若干位数字（0 - 9）组成
//除了 0 本身之外，num 不含任何前导零
public class Problem_0402_RemoveKDigits {

    public static String removeKdigits(String num, int k) {
        //从底向上单调递增的栈
        //去除所有的下降趋势
        Stack<Integer> stack = new Stack<>();
        char[] str = num.toCharArray();
        int count = 0;
        int n = str.length;
        for (int i = 0; i < n; i++) {
            int cur = str[i] - '0';
            while (!stack.isEmpty() && stack.peek() > cur && ++count <= k) { //"1432219"
                stack.pop();
            }
            stack.push(cur);
        }
        while (count < k) { //"num=1112，k=1"这种case
            stack.pop();
            count++;
        }
        StringBuilder sb = new StringBuilder();
        for (int c : stack) {
            sb.append(c);
        }
        //删除前导0
        while (sb.length() > 1 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }
        return sb.toString().length() > 0 ? sb.toString() : "0";
    }

}
