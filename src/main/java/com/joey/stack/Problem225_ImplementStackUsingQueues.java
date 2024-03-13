package com.joey.stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author pei.liu
 */

//225. 用队列实现栈
//https://leetcode.cn/problems/implement-stack-using-queues/description/
public class Problem225_ImplementStackUsingQueues {

    class MyStack {

        private final LinkedList<Integer> q;
        private int size;

        public MyStack() {
            q = new LinkedList<>();
        }

        public void push(int x) {
            q.offerLast(x);
            size++;
        }

        public int pop() {
            for (int i = 0; i < size - 1; i++) {
                q.offerLast(q.pollFirst());
            }
            size--;
            return q.pollFirst();
        }

        public int top() {
            return q.peekLast();
        }

        public boolean empty() {
            return size == 0;
        }
    }

}
