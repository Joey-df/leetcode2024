package com.joey.top_hot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 406. 根据身高重建队列
 * 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。
 * 每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
 * 请你重新构造并返回输入数组 people 所表示的队列。
 * 返回的队列应该格式化为数组 queue ，
 * 其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。
 */
public class Problem_0406_QueueReconstructionByHeight {

    //1、排序策略：hi降序，hi一样按ki升序
    //2、从前往后遍历将i位置的原始插入到 需求所代表的位置，结束
    //时间复杂度：O(n^2)
    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0) return new int[0][2];
        int n = people.length;
        //hi降序，hi一样按ki升序
        Arrays.sort(people, (a, b) -> a[0] != b[0] ? b[0] - a[0] : a[1] - b[1]);
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int[] cur = people[i];
            list.add(cur[1], cur); //把当前元素加入到ki的位置
        }
        int[][] ans = new int[n][2];
        for (int i = 0; i < n; i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

}
