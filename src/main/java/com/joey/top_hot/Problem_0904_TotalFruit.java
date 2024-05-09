package com.joey.top_hot;

import java.util.HashMap;

/**
 * @author pei.liu
 */
//904. 水果成篮
//题意可以抽象为：给定数组s，求只包含两种数的最大子数组长度
//示例 1：
//输入：fruits = [1,2,1]
//输出：3
//解释：可以采摘全部 3 棵树。
//示例 2：
//输入：fruits = [0,1,2,2]
//输出：3
//解释：可以采摘 [1,2,2] 这三棵树。
//如果从第一棵树开始采摘，则只能采摘 [0,1] 这两棵树。
//示例 3：
//输入：fruits = [1,2,3,2,2]
//输出：4
//解释：可以采摘 [2,3,2,2] 这四棵树。
//如果从第一棵树开始采摘，则只能采摘 [1,2] 这两棵树。
//示例 4：
//输入：fruits = [3,3,3,1,2,1,1,2,3,3,4]
//输出：5
//解释：可以采摘 [1,2,1,1,2] 这五棵树。
//
//提示：
//1 <= fruits.length <= 10^5
//0 <= fruits[i] < fruits.length
public class Problem_0904_TotalFruit {

    //[3,3,3,1,2,1,1,2,3,3,4]
    //思路：滑动窗口
    //需要建立一种维持窗口内水果种类的机制：使用hashmap
    public int totalFruit(int[] fruits) {
        if (fruits == null || fruits.length == 0) {
            return 0;
        }
        int n = fruits.length;
        //key：水果的种类
        //val：每种水果最后出现的位置
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        int l = 0;
        int r = 0; // 窗口的范围是[l,r)
        while (r < n) {
            while (r < n && (map.size() < 2 || map.containsKey(fruits[r]))) { // r往右扩的逻辑
                map.put(fruits[r], r);
                r++;
            }
            // r来到违规的位置，或者越界位置，更新答案
            ans = Math.max(ans, r - l);
            // 找到map中值最小对应key
            int fruit = find(map);
            int j = map.remove(fruit); // 返回的是key对应的value
            l = j + 1; // l来到j的下一个位置
        }
        return ans;
    }

    // 找到map中值最小对应key
    private int find(HashMap<Integer, Integer> map) {
        int ans = -1;
        int value = Integer.MAX_VALUE;
        for (int key : map.keySet()) {
            if (map.get(key) < value) {
                value = map.get(key);
                ans = key;
            }
        }
        return ans;
    }

}
