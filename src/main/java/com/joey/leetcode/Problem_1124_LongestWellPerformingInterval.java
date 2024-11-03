package com.joey.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pei.liu
 */
// 表现良好的最长时间段
// 给你一份工作时间表 hours，上面记录着某一位员工每天的工作小时数
// 我们认为当员工一天中的工作小时数大于 8 小时的时候，那么这一天就是 劳累的一天
// 所谓 表现良好的时间段 ，意味在这段时间内，「劳累的天数」是严格 大于 不劳累的天数
// 请你返回 表现良好时间段 的最大长度
//示例 1：
//输入：hours = [9,9,6,0,6,6,9]
//输出：3
//解释：最长的表现良好时间段是 [9,9,6]。
//示例 2：
//输入：hours = [6,6,6]
//输出：0
//
//提示：
//1 <= hours.length <= 10^4
//0 <= hours[i] <= 16
// 测试链接 : https://leetcode.cn/problems/longest-well-performing-interval/
public class Problem_1124_LongestWellPerformingInterval {

    //前缀和
    public int longestWPI(int[] hours) {
        int n = hours.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = hours[i] > 8 ? 1 : -1;
        }
        int ans = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            if (sum > 0) {
                ans = i + 1;
            } else { //sum<=0
                if (map.containsKey(sum - 1)) {
                    ans = Math.max(ans, i - map.get(sum - 1));
                }
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return ans;
    }

}
