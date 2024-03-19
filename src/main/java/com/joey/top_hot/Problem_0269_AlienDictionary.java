package com.joey.top_hot;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 火星词典
 *
 * 现有一种使用字母的全新语言，这门语言的字母顺序与英语顺序不同。
 * 您有一个单词列表（从词典中获得的），该单词列表内的单词已经按这门新语言的字母顺序进行了排序。
 * 需要根据这个输入的列表，还原出此语言中已知的字母顺序。
 *
 * 示例1:
 * 输入:
 * [
 *   "wrt",
 *   "wrf",
 *   "er",
 *   "ett",
 *   "rftt"
 * ]
 * 输出: "wertf"
 *
 * 示例2:
 * 输入:
 * [
 *   "z",
 *   "x"
 * ]
 * 输出: "zx"
 *
 * 示例3:
 * 输入:
 * [
 *   "z",
 *   "x",
 *   "z"
 * ]
 * 输出: ""
 *
 * 解释: 非法排序， 返回"".
 * 提示:
 * 所有字母都是小写字母.
 * 如果a在b的前面，字典中a应该出现在b的前方
 * 如果是非法顺序，返回空
 * 返回任意一种合理排序
 */
//图的拓扑排序
public class Problem_0269_AlienDictionary {

    public static String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        int N = words.length;
        HashMap<Character, Integer> indegree = new HashMap<>();
        for (int i = 0; i < N; i++) {
            for (char c : words[i].toCharArray()) {
                indegree.put(c, 0);
            }
        }
        HashMap<Character, HashSet<Character>> graph = new HashMap<>();
        for (int i = 0; i < N - 1; i++) {
            char[] cur = words[i].toCharArray();
            char[] nex = words[i + 1].toCharArray();
            int len = Math.min(cur.length, nex.length);
            int j = 0;
            for (; j < len; j++) {
                if (cur[j] != nex[j]) {
                    if (!graph.containsKey(cur[j])) {
                        graph.put(cur[j], new HashSet<>());
                    }
                    if (!graph.get(cur[j]).contains(nex[j])) {
                        graph.get(cur[j]).add(nex[j]);
                        indegree.put(nex[j], indegree.get(nex[j]) + 1);
                    }
                    break;
                }
            }
            if (j < cur.length && j == nex.length) {
                return "";
            }
        }
        StringBuilder ans = new StringBuilder();
        Queue<Character> q = new LinkedList<>();
        for (Character key : indegree.keySet()) {
            if (indegree.get(key) == 0) {
                q.offer(key);
            }
        }
        while (!q.isEmpty()) {
            char cur = q.poll();
            ans.append(cur);
            if (graph.containsKey(cur)) {
                for (char next : graph.get(cur)) {
                    indegree.put(next, indegree.get(next) - 1);
                    if (indegree.get(next) == 0) {
                        q.offer(next);
                    }
                }
            }
        }
        return ans.length() == indegree.size() ? ans.toString() : "";
    }

}
