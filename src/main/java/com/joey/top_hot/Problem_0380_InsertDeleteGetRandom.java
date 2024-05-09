package com.joey.top_hot;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 380. O(1) 时间插入、删除和获取随机元素
 * 设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构。
 * <p>
 * insert(val)：当元素 val 不存在时，向集合中插入该项。
 * remove(val)：元素 val 存在时，从集合中移除该项。
 * getRandom：随机返回现有集合中的一项。每个元素应该有相同的概率被返回。
 */
public class Problem_0380_InsertDeleteGetRandom {

    public class RandomizedSet {

        private Map<Integer, Integer> keyIndexMap; //<key, index>
        private Map<Integer, Integer> indexKeyMap; //<index, key>
        private int size;

        public RandomizedSet() {
            keyIndexMap = new HashMap<>();
            indexKeyMap = new HashMap<>();
            size = 0;
        }

        public boolean insert(int val) {
            if (keyIndexMap.containsKey(val)) {
                return false; //已经存在直接返回false
            } else {
                //还不存在就添加
                keyIndexMap.put(val, size); //<3,0>
                indexKeyMap.put(size, val); //<0,3>
                size++; //加完之后size+1
                return true;
            }
        }

        public boolean remove(int val) {
            if (!keyIndexMap.containsKey(val)) {
                return false;//不存在直接返回false
            } else {
                //存在就删除
                int lastIndex = size - 1;
                int lastKey = indexKeyMap.get(lastIndex);//先找到最后一次添加进去的元素是谁
                int removeIndex = keyIndexMap.get(val);//要删除元素的index
                keyIndexMap.put(lastKey, removeIndex);//填洞
                indexKeyMap.put(removeIndex, lastKey);//填洞
                //删除val
                keyIndexMap.remove(val);
                indexKeyMap.remove(lastIndex);
                size--;
                return true;
            }
        }

        public int getRandom() {
            if (size == 0) {
                return -1;
            }
            int rindex = (int) (Math.random() * size);
            return indexKeyMap.get(rindex);
        }
    }

}
