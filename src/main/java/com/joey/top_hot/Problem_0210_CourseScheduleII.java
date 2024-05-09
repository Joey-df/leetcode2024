package com.joey.top_hot;

import java.util.*;

//210. 课程表 II
//现在你总共有 n 门课需要选，记为 0 到 n-1。
//在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
//给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。
//可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
//示例 1:
//输入: 2, [[1,0]]
//输出: [0,1]
//解释: 总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
//示例 2:
//输入: 4, [[1,0],[2,0],[3,1],[3,2]]
//输出: [0,1,2,3] or [0,2,1,3]
//解释: 总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
//因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
public class Problem_0210_CourseScheduleII {

    static class Node {
        int name;
        int in;
        ArrayList<Node> nexts;

        public Node(int name) {
            this.name = name;
            this.nexts = new ArrayList<>();
        }
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] ans = new int[numCourses];
        //先初始化一份ans（没有任何依赖关系的）
        for (int i = 0; i < numCourses; i++) {
            ans[i] = i;
        }
        //如果没有任何依赖的条件，直接返回ans
        if (prerequisites == null || prerequisites.length == 0) {
            return ans;
        }
        HashMap<Integer, Node> nodes = new HashMap<>();
        for (int[] cur : prerequisites) {
            int to = cur[0];
            int from = cur[1];
            if (!nodes.containsKey(to)) {
                nodes.put(to, new Node(to));
            }
            if (!nodes.containsKey(from)) {
                nodes.put(from, new Node(from));
            }
            nodes.get(from).nexts.add(nodes.get(to));
            nodes.get(to).in++;
        }

        int index = 0;
        // nodes中没有，说明不依赖其他任何课程，先处理无依赖的课程
        for (int i = 0; i < numCourses; i++) {
            if (!nodes.containsKey(i)) {
                ans[index++] = i;
            }
        }
        //队列中存放入度为0的结点
        LinkedList<Node> q = new LinkedList<>();
        for (Node node : nodes.values()) {
            if (node.in == 0) {
                q.addLast(node);
            }
        }
        int count = 0;
        while (!q.isEmpty()) {
            Node cur = q.pollFirst();
            count++;
            ans[index++] = cur.name; //入度为0的结点出队列之后要加入ans
            for (Node next : cur.nexts) {
                if (--next.in == 0) {
                    q.addLast(next);
                }
            }
        }
        if (count == nodes.size()) {
            return ans;
        }
        return new int[0];
    }
}

