package com.joey.top_hot;

import java.util.Stack;

/**
 * 155. 最小栈
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 */
public class Problem_0155_MinStack {

    Stack<Integer> normal;
    Stack<Integer> min;

    public Problem_0155_MinStack() {
        normal = new Stack<>();
        min = new Stack<>();
    }

    public void push(int val) {
        normal.push(val);
        if (min.isEmpty())
            min.push(val);
        else
            min.push(Math.min(val, min.peek()));
    }

    public void pop() {
        normal.pop();
        min.pop();
    }

    public int top() {
        return normal.peek();
    }

    public int getMin() {
        return min.peek();
    }
}
