package com.joey.queue;

/**
 * @author pei.liu
 */


//641. 设计循环双端队列
public class Problem641_MyCircularDeque {

    class MyCircularDeque {

        int[] buffer;
        int l;
        int r; // 每次添加和弹出元素，需要先计算l和r（下标）
        int size;
        int limit;

        public MyCircularDeque(int k) {
            buffer = new int[k];
            limit = k;
        }

        public boolean insertFront(int value) {
            if (isFull()) return false;
            if (isEmpty()) {
                l = r = 0;
                buffer[0] = value;
            } else {
                l = l == 0 ? buffer.length - 1 : l - 1;
                buffer[l] = value;
            }
            size++;
            return true;
        }

        public boolean insertLast(int value) {
            if (isFull()) return false;
            if (isEmpty()) {
                l = r = 0;
                buffer[0] = value;
            } else {
                r = r == buffer.length - 1 ? 0 : r + 1;
                buffer[r] = value;
            }
            size++;
            return true;
        }

        public boolean deleteFront() {
            if (isEmpty()) return false;
            l = l == buffer.length - 1 ? 0 : l + 1;
            size--;
            return true;
        }

        public boolean deleteLast() {
            if (isEmpty()) return false;
            r = r == 0 ? buffer.length - 1 : r - 1;
            size--;
            return true;
        }

        public int getFront() {
            if (isEmpty()) return -1;
            return buffer[l];
        }

        public int getRear() {
            if (isEmpty()) return -1;
            return buffer[r];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }
    }
}
