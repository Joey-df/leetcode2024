package com.joey.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author pei.liu
 */
//373. 查找和最小的 K 对数字
//给定两个以 非递减顺序排列 的整数数组 nums1 和 nums2 , 以及一个整数 k 。
//定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
//请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。
//提示:
//
//1 <= nums1.length, nums2.length <= 10^5
//-10^9 <= nums1[i], nums2[i] <= 10^9
//nums1 和 nums2 均为 升序排列
//1 <= k <= 10^4
//k <= nums1.length * nums2.length
public class Problem_0373_FindKPairsWithSmallestSums {

    //超出内存限制：因为visited规模太大了
    public List<List<Integer>> kSmallestPairs1(int[] nums1, int[] nums2, int k) {
        //如果两个组数没排序的话，要先排序
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int n = nums1.length;
        int m = nums2.length;
        //两个小数组代表的值，谁的累加和小，谁排前面
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) ->
                Integer.compare(nums1[a[0]] + nums2[a[1]], nums1[b[0]] + nums2[b[1]]));
        boolean[][] visited = new boolean[n][m]; //标记当前位置是否已经进过小根堆
        List<List<Integer>> ans = new ArrayList<>();
        visited[0][0] = true;
        q.offer(new int[]{0, 0});
        int clt = 0;
        while (clt++ < k && !q.isEmpty()) {
            //出来一个进两个（相邻的两个位置）
            //出来的那个永远是当前最小的
            int[] curr = q.poll();
            int x = curr[0];
            int y = curr[1];
            ans.add(Arrays.asList(nums1[x], nums2[y]));
            int nx = x + 1;
            int ny = y + 1;
            if (nx < n && !visited[nx][y]) {
                q.offer(new int[]{nx, y});
                visited[nx][y] = true;
            }
            if (ny < m && !visited[x][ny]) {
                q.offer(new int[]{x, ny});
                visited[x][ny] = true;
            }
        }
        return ans;
    }


    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        //如果两个组数没排序的话，要先排序
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int n = nums1.length;
        int m = nums2.length;
        List<List<Integer>> ans = new ArrayList<>();
        //0:nums1中的下标
        //1:nums2中的下标
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> Integer.compare(nums1[a[0]] + nums2[a[1]], nums1[b[0]] + nums2[b[1]]));
        //先把nums1中和nums2[0]组合的前k个放进去
        for (int i = 0; i < Math.min(n, k); i++) {
            q.offer(new int[]{i, 0});
        }
        int clt = 0;
        while (clt++ < k && !q.isEmpty()) {
            int[] curr = q.poll();
            int x = curr[0];
            int y = curr[1];
            ans.add(Arrays.asList(nums1[x], nums2[y]));
            //(x+1, y)之前一定已经进过了
            //只需要(x,y+1)进就可以了
            if (y + 1 < m) {
                q.offer(new int[]{x, y + 1});
            }
        }
        return ans;
    }

}
