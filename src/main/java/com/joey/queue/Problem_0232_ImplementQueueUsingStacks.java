package com.joey.queue;

import java.util.Stack;

/**
 * @author pei.liu
 */

//232. 用栈实现队列
//https://leetcode.cn/problems/implement-queue-using-stacks/description/
public class Problem_0232_ImplementQueueUsingStacks {

    class MyQueue {

        private final Stack<Integer> in;
        private final Stack<Integer> out;

        public MyQueue() {
            in = new Stack<>();
            out = new Stack<>();
        }

        public void push(int x) {
            in.push(x);
            inToOut();
        }

        public int pop() {
            inToOut();
            return out.pop();
        }

        public int peek() {
            inToOut();
            return out.peek();
        }

        public boolean empty() {
            return in.isEmpty() && out.isEmpty();
        }

        // 1.out为空时才能倒
        // 2.要倒必须一次性倒完
        private void inToOut() {
            if (out.isEmpty()) {
                while (!in.isEmpty()) {
                    out.push(in.pop());
                }
            }
        }
    }

}
