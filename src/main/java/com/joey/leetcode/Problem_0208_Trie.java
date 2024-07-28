package com.joey.leetcode;

/**
 * 208. 实现 Trie (前缀树)
 */
public class Problem_0208_Trie {

    static class Trie {
        private static class Node {
            private int pass;
            private int end;
            private Node[] paths;

            public Node() {
                pass = 0;
                end = 0;
                // 0--a
                // 1--b
                // 2--c
                // 25--z
                // 初始化每个位置的值都是null
                paths = new Node[26]; //word 和 prefix 仅由小写英文字母组成
            }
        }

        private Node root;

        public Trie() {
            root = new Node();
        }

        //向前缀树中添加一个word
        public void insert(String word) {
            if (word == null || "".equals(word) || word.length() == 0) {
                return;
            }
            Node ptr = root;//指针先来到root
            ptr.pass++;
            char[] str = word.toCharArray();
            for (char c : str) {
                int road = c - 'a'; //由字符，对应成走向那条路
                if (ptr.paths[road] == null) {
                    ptr.paths[road] = new Node(); //没有路就新建
                }
                ptr = ptr.paths[road]; //ptr往下跳
                ptr.pass++;
            }
            ptr.end++;
        }

        //搜索指定的word在树中是否存在
        public boolean search(String word) {
            if (word == null || "".equals(word) || word.length() == 0) {
                return false;
            }
            Node ptr = root;
            char[] str = word.toCharArray();
            for (char c : str) {
                if (ptr.paths[c - 'a'] == null) { //走着走着没路了
                    return false;
                }
                ptr = ptr.paths[c - 'a'];
            }
            //ptr来到word的最后一个字符
            return ptr.end > 0;
        }

        //查询是否存在以prefix开头的单词
        public boolean startsWith(String prefix) {
            if (prefix == null || "".equals(prefix) || prefix.length() == 0) {
                return false;
            }
            Node ptr = root;
            char[] str = prefix.toCharArray();
            for (char c : str) {
                if (ptr.paths[c - 'a'] == null) {
                    return false;
                }
                ptr = ptr.paths[c - 'a'];
            }
            return ptr.pass > 0;
        }

        //删除
        public void delete(String word) {
            if (this.search(word)) { //存在才可以删
                Node ptr = root;
                ptr.pass--;
                char[] str = word.toCharArray();
                for (char c : str) {
                    ptr.paths[c - 'a'].pass--;
                    if (ptr.paths[c - 'a'].pass == 0) {
                        ptr.paths[c - 'a'] = null;
                        return;
                    }
                    ptr = ptr.paths[c - 'a'];
                }
                ptr.end--;
            }
        }
    }
}
