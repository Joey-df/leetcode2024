package com.joey.top_hot;

import java.util.Stack;

/**
 * 224. 基本计算器
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
 * s 表示一个有效的表达式
 * <p>
 * 示例 1：
 * 输入：s = "1 + 1"
 * 输出：2
 * 示例 2：
 * 输入：s = " 2-1 + 2 "
 * 输出：3
 * 示例 3：
 * 输入：s = "(1+(4+5+2)-3)+(6+8)"
 * 输出：23
 */
public class Problem_0224_BasicCalculator {

    //Simple iterative solution by identifying characters one by one. One important thing is that the input is valid, which means the parentheses are always paired and in order.
    //Only 5 possible input we need to pay attention:
    //
    //digit: it should be one digit from the current number
    //'+': number is over, we can add the previous number and start a new number
    //'-': same as above
    //'(': push the previous result and the sign into the stack, set result to 0, just calculate the new result within the parenthesis.
    //')': pop out the top two numbers from stack, first one is the sign before this pair of parenthesis, second is the temporary result before this pair of parenthesis. We add them together.
    //Finally if there is only one number, from the above solution, we haven't add the number to the result, so we do a check see if the number is zero.
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int result = 0;
        int number = 0;
        int sign = 1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                number = 10 * number + (int) (c - '0');
            } else if (c == '+') {
                result += sign * number;
                number = 0;
                sign = 1;
            } else if (c == '-') {
                result += sign * number;
                number = 0;
                sign = -1;
            } else if (c == '(') {
                //we push the result first, then sign;
                stack.push(result);
                stack.push(sign);
                //reset the sign and result for the value in the parenthesis
                sign = 1;
                result = 0;
            } else if (c == ')') {
                result += sign * number;
                number = 0;
                result *= stack.pop();    //stack.pop() is the sign before the parenthesis
                result += stack.pop();   //stack.pop() now is the result calculated before the parenthesis
            }
        }
        if (number != 0) result += sign * number;
        return result;
    }

}
