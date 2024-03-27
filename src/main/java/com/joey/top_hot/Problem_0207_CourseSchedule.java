package com.joey.top_hot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 207. 课程表
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * <p>
 * 在选修某些课程之前需要一些先修课程。
 * 先修课程按数组 prerequisites 给出，
 * 其中 prerequisites[i] = [ai, bi] ，
 * 表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 * <p>
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 * <p>
 * 示例 2：
 * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 */
//图的拓扑排序
public class Problem_0207_CourseSchedule {

    static class Node {
        int name; // 课程编号
        int in; // 入度
        ArrayList<Node> nexts; // 邻居

        public Node(int name) {
            this.name = name;
            this.nexts = new ArrayList<>();
        }
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) return true;
        HashMap<Integer, Node> map = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            int[] cur = prerequisites[i];
            int to = cur[0];
            int from = cur[1];
            if (!map.containsKey(to)) {
                map.put(to, new Node(to));
            }
            if (!map.containsKey(from)) {
                map.put(from, new Node(from));
            }
            map.get(to).in++;
            map.get(from).nexts.add(map.get(to));
        }

        LinkedList<Integer> q = new LinkedList<>();
        for (int name : map.keySet()) {
            if (map.get(name).in == 0) {
                q.addLast(name);
            }
        }
        int count = 0;
        while (!q.isEmpty()) {
            int name = q.pollFirst();
            count++;
            Node cur = map.get(name);
            for (Node node : cur.nexts) {
                if (--node.in == 0) {
                    q.addLast(node.name);
                }
            }
        }
        return count == map.size();
    }

}

