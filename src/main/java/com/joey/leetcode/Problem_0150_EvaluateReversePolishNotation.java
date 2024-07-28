package com.joey.leetcode;

import java.util.Stack;

//150. 逆波兰表达式求值
//根据 逆波兰表示法，求表达式的值。
//有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
//说明：
//整数除法只保留整数部分。
//给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
//示例 1：
//输入：tokens = ["2","1","+","3","*"]
//输出：9
//解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
//示例 2：
//输入：tokens = ["4","13","5","/","+"]
//输出：6
//解释：该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
public class Problem_0150_EvaluateReversePolishNotation {

    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("+") ||
                    tokens[i].equals("-") ||
                    tokens[i].equals("*") ||
                    tokens[i].equals("/")
            ) {
                //pop弹出两个计算，结果再push进去
                cal(stack, tokens[i]);
            } else {
                stack.push(Integer.parseInt(tokens[i]));
            }
        }
        return stack.pop();
    }

    public static void cal(Stack<Integer> stack, String s) {
        int a = stack.pop();
        int b = stack.pop();
        switch (s) {
            case "+":
                stack.push(b + a);
                break;
            case "-":
                stack.push(b - a);
                break;
            case "*":
                stack.push(b * a);
                break;
            case "/":
                stack.push(b / a);
                break;
        }
    }

}
