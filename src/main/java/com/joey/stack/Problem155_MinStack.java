package com.joey.stack;

import java.util.Stack;

/**
 * @author pei.liu
 */
public class Problem155_MinStack {

    class MinStack {

        private Stack<Integer> data;
        private Stack<Integer> min;

        public MinStack() {
            data = new Stack<>();
            min = new Stack<>();
        }

        public void push(int val) {
            if (data.isEmpty()){
                min.push(val);
            } else {
                min.push(Math.min(min.peek(), val));
            }
            data.push(val);
        }

        public void pop() {
            if (!data.isEmpty()) {
                data.pop();
                min.pop();
            }
        }

        public int top() {
            return data.peek();
        }

        public int getMin() {
            return min.peek();
        }
    }

}
