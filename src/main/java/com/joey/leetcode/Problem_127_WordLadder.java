package com.joey.leetcode;

import java.util.*;

/**
 * @author pei.liu
 */
public class Problem_127_WordLadder {

    static Set<String> visited = new HashSet<>();
    static Set<String> words;
    static Queue<String> q = new LinkedList<>();
    public int ladderLength(String begin, String end, List<String> wordList) {
        visited.clear();
        words = new HashSet<>(wordList);
        q.clear();
        q.offer(begin);
        int level = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String curr = q.poll();
                if (end.equals(curr)) {
                    return level;
                }
                help(curr);
            }
            level++;
        }
        return 0;
    }

    public static void help(String curr) {
        for (int j = 0; j < curr.length(); j++) { //curr每个位置的字符都替换一遍
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == curr.charAt(j)) continue;
                String str = curr.substring(0, j) + c + curr.substring(j + 1);
                if (words.contains(str) && !visited.contains(str)) {
                    q.offer(str);
                    visited.add(str);
                }
            }
        }
    }

    public static void main(String[] args) {
        char[] chars = {'1','6','3'};
        int ans=0;
        for (char c: chars) {
            ans *=10;
            ans += c-'0';
        }
        System.out.println(ans);
    }
}
