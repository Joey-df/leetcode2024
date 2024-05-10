package com.joey.top_hot;

import java.util.Arrays;

/**
 * @author pei.liu
 */
//853. 车队
//在一条单行道上，有 n 辆车开往同一目的地。目的地是几英里以外的 target 。
//给定两个整数数组 position 和 speed ，长度都是 n ，其中 position[i] 是第 i 辆车的位置， speed[i] 是第 i 辆车的速度(单位是英里/小时)。
//一辆车永远不会超过前面的另一辆车，但它可以追上去，并与前车 以相同的速度 紧接着行驶。此时，我们会忽略这两辆车之间的距离，也就是说，它们被假定处于相同的位置。
//车队 是一些由行驶在相同位置、具有相同速度的车组成的非空集合。注意，一辆车也可以是一个车队。
//即便一辆车在目的地才赶上了一个车队，它们仍然会被视作是同一个车队。
//返回到达目的地的 车队数量 。
//
//示例 1：
//输入：target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
//输出：3
//解释：
//从 10 和 8 开始的车会组成一个车队，它们在 12 处相遇。
//从 0 处开始的车无法追上其它车，所以它自己就是一个车队。
//从 5 和 3 开始的车会组成一个车队，它们在 6 处相遇。
//请注意，在到达目的地之前没有其它车会遇到这些车队，所以答案是 3。
//示例 2:
//输入: target = 10, position = [3], speed = [3]
//输出: 1
//解释: 只有一辆车，因此只有一个车队。
//示例 3:
//输入: target = 100, position = [0,2,4], speed = [4,2,1]
//输出: 1
//解释:
//以0(速度4)和2(速度2)出发的车辆组成车队，在4点相遇。舰队以2的速度前进。
//然后，车队(速度2)和以4(速度1)出发的汽车组成一个车队，在6点相遇。舰队以1的速度前进，直到到达目标。
//
//提示：
//n == position.length == speed.length
//1 <= n <= 10^5
//0 < target <= 10^6
//0 <= position[i] < target
//position 中每个值都 不同
//0 < speed[i] <= 10^6
public class Problem_0853_CarFleet {

    //一辆车永远不会超过前面的另一辆车，但它可以追上去，并与前车 以相同的速度 紧接着行驶。
    //这句话很重要，最终车队会碰撞在一起形成几个团，最终求的就是团的数量
    //1 <= n <= 10^5这个数据限制，O(n^2)肯定不行，需要寻找O(nlogn) 或者O(n)的 解
    //题目给定的position是乱序的，需要先排序
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[]{position[i], speed[i]};
        }
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        int ans = 0;
        for (int i = n - 1; i >= 0; ) {
            int[] cur = arr[i]; //领头车
            double time = (target - cur[0]) * 1.0 / cur[1];//领头车到达终点的时间
            int j = i - 1;
            while (j >= 0 && (target - arr[j][0]) * 1.0 / arr[j][1] <= time) {
                j--;
            }
            ans++;//发现一个车队
            i = j; //下一轮寻找
        }
        return ans;
    }

}
