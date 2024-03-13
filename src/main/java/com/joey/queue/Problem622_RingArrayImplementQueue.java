package com.joey.queue;

/**
 * @author pei.liu
 */

//622. 设计循环队列
//使用【环形数组】实现队列
//https://leetcode.cn/problems/design-circular-queue/description/
//设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”。
//循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。
//
//你的实现应该支持如下操作：
//MyCircularQueue(k): 构造器，设置队列长度为 k 。
//Front: 从队首获取元素。如果队列为空，返回 -1 。
//Rear: 获取队尾元素。如果队列为空，返回 -1 。
//enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
//deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
//isEmpty(): 检查循环队列是否为空。
//isFull(): 检查循环队列是否已满。
public class Problem622_RingArrayImplementQueue {

    class MyCircularQueue {

        int[] buffer;
        int size;
        int l; //从头弹出元素的话，应该从l位置取出
        int r; //加入元素的话，应该放在r位置；从尾部弹出元素的话应该弹出r的前一个位置

        public MyCircularQueue(int k) {
            buffer = new int[k];
            size = 0;
            l = 0;
            r = 0;
        }

        public boolean enQueue(int value) {
            if (isFull()) return false;
            buffer[r] = value;
            r = r == buffer.length - 1 ? 0 : r + 1;
            size++;
            return true;
        }

        public boolean deQueue() {
            if (isEmpty()) return false;
            l = l == buffer.length - 1 ? 0 : l + 1;
            size--;
            return true;
        }

        public int Front() {
            if (isEmpty()) return -1;
            return buffer[l];
        }

        public int Rear() {
            if (isEmpty()) return -1;
            int index = r == 0 ? buffer.length - 1 : r - 1;
            return buffer[index];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == buffer.length;
        }

    }

}
