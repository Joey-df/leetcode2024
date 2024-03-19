package com.joey.top_hot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 207. 课程表
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 *
 * 在选修某些课程之前需要一些先修课程。
 * 先修课程按数组 prerequisites 给出，
 * 其中 prerequisites[i] = [ai, bi] ，
 * 表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 *
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 *
 * 示例 1：
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 *
 * 示例 2：
 * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 */
//图的拓扑排序
public class Problem_0207_CourseSchedule {

    // 一个node，就是一个课程
    // name是课程的编号
    // in是课程的入度
    public static class Node {
        public int name;
        public int in;
        public ArrayList<Node> nexts;

        public Node(int n) {
            name = n;
            in = 0;
            nexts = new ArrayList<>();
        }
    }

    public static boolean canFinish1(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) {
            return true;
        }
        HashMap<Integer, Node> nodes = new HashMap<>();
        for (int[] arr : prerequisites) {
            int to = arr[0];
            int from = arr[1];
            if (!nodes.containsKey(to)) {
                nodes.put(to, new Node(to));
            }
            if (!nodes.containsKey(from)) {
                nodes.put(from, new Node(from));
            }
            Node t = nodes.get(to);
            Node f = nodes.get(from);
            f.nexts.add(t);
            t.in++;
        }
        int needPrerequisiteNums = nodes.size();
        Queue<Node> zeroInQueue = new LinkedList<>();
        for (Node node : nodes.values()) {
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }
        int count = 0;
        while (!zeroInQueue.isEmpty()) {
            Node cur = zeroInQueue.poll();
            count++;
            for (Node next : cur.nexts) {
                if (--next.in == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return count == needPrerequisiteNums;
    }

    // 和方法1算法过程一样
    // 但是写法优化了
    public static boolean canFinish2(int courses, int[][] relation) {
        if (relation == null || relation.length == 0) {
            return true;
        }
        ArrayList<ArrayList<Integer>> nexts = new ArrayList<>();
        for (int i = 0; i < courses; i++) {
            nexts.add(new ArrayList<>());
        }
        int[] in = new int[courses];
        for (int[] arr : relation) {
            nexts.get(arr[1]).add(arr[0]);
            in[arr[0]]++;
        }
        int[] zero = new int[courses];
        int l = 0;
        int r = 0;
        for (int i = 0; i < courses; i++) {
            if (in[i] == 0) {
                zero[r++] = i;
            }
        }
        int count = 0;
        while (l != r) {
            count++;
            for (int next : nexts.get(zero[l++])) {
                if (--in[next] == 0) {
                    zero[r++] = next;
                }
            }
        }
        return count == nexts.size();
    }

}

