package com.joey.leetcode;

//79. 单词搜索
//给定一个char[][] matrix，也就是char类型的二维数组，再给定一个字符串word，
//可以从任何一个某个位置出发，可以走上下左右，能不能找到word？
//char[][] m =
//{
//{ 'a', 'b', 'z' },
//{ 'c', 'd', 'o' },
//{ 'f', 'e', 'o' }
//}
//不可以走重复路的情况下，返回能不能找到
//比如，word = 'zoooz'，是不可以找到的，因为允许走一条路径中已经走过的字符不能重复走
public class Problem_0079_WordSearch {

    public static boolean exist(char[][] board, String word) {
        int n = board.length;
        if (n == 0) return false;
        int m = board[0].length;
        if (m == 0) return false;
        char[] str = word.toCharArray();
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (can(board, i, j, str, 0, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    //从[i,j]开始能不能走出word[k...]
    public static boolean can(char[][] board, int i, int j, char[] word, int k, boolean[][] visited) {
        int n = board.length;
        int m = board[0].length;
        if (k == word.length) {
            //如果走到了word的越界位置，说明能走出来
            return true;
        }
        if (i < 0 || i >= n || j < 0 || j >= m || board[i][j] != word[k] || visited[i][j]) {
            //走到board外面去了，或者board[i][j] != word[k]，说明走不出来
            return false;
        }
        //board[i][j] == word[k]
        visited[i][j] = true;
        boolean ans = can(board, i - 1, j, word, k + 1, visited)
                || can(board, i + 1, j, word, k + 1, visited)
                || can(board, i, j - 1, word, k + 1, visited)
                || can(board, i, j + 1, word, k + 1, visited);
        visited[i][j] = false;
        return ans;
    }

    public static void main(String[] args) {
        //board = [['A','B','C','E'],['S','F','C','S'],[S','F','C','S']], word = 'ABCCED'
        char[][] board = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word = "ABCCED";
        System.out.println(exist(board, word));
    }
}
