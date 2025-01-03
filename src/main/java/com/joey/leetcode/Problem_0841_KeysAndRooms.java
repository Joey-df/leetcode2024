package com.joey.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author pei.liu
 */
//841. 钥匙和房间
//有 n 个房间，房间按从 0 到 n - 1 编号。最初，除 0 号房间外的其余所有房间都被锁住。你的目标是进入所有的房间。然而，你不能在没有获得钥匙的时候进入锁住的房间。
//当你进入一个房间，你可能会在里面找到一套 不同的钥匙，每把钥匙上都有对应的房间号，即表示钥匙可以打开的房间。你可以拿上所有钥匙去解锁其他房间。
//给你一个数组 rooms 其中 rooms[i] 是你进入 i 号房间可以获得的钥匙集合。如果能进入 所有 房间返回 true，否则返回 false。
//示例 1：
//输入：rooms = [[1],[2],[3],[]]
//输出：true
//解释：
//我们从 0 号房间开始，拿到钥匙 1。
//之后我们去 1 号房间，拿到钥匙 2。
//然后我们去 2 号房间，拿到钥匙 3。
//最后我们去了 3 号房间。
//由于我们能够进入每个房间，我们返回 true。
//示例 2：
//输入：rooms = [[1,3],[3,0,1],[2],[0]]
//输出：false
//解释：我们不能进入 2 号房间。
//
//提示：
//n == rooms.length
//2 <= n <= 1000
//0 <= rooms[i].length <= 1000
//1 <= sum(rooms[i].length) <= 3000
//0 <= rooms[i][j] < n
//所有 rooms[i] 的值 互不相同
public class Problem_0841_KeysAndRooms {

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        return dfs(0, visited, rooms) == n;
    }

    //当前来到i号房间，从每个房间能进入的房间放在rooms中
    //返回从i出发，能进入的房间数
    public int dfs(int i, boolean[] visited, List<List<Integer>> rooms) {
        int ans = 1; //i号房间算一个
        visited[i] = true;
        List<Integer> nexts = rooms.get(i); //当前i房间获得的钥匙集合，即可以去往的房间集合
        for (int next : nexts) {
            if (!visited[next]) {
                visited[next] = true;
                ans += dfs(next, visited, rooms);
            }
        }
        return ans;
    }


    //图的宽度优先遍历bfs
    public boolean canVisitAllRooms2(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        return bfs(0, rooms, visited) == n;
    }

    public int bfs(int start, List<List<Integer>> rooms, boolean[] visited) {
        LinkedList<Integer> q = new LinkedList<>();
        q.offer(start);
        int ans = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int curr = q.poll();
                if (visited[curr]) continue;
                visited[curr] = true;
                ans++;
                List<Integer> nexts = rooms.get(curr); //下一层
                for (int next : nexts) {
                    if (!visited[next]) {
                        q.offer(next);
                    }
                }
            }
        }
        return ans;
    }

}
