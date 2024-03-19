package com.joey.top_hot;

//79. 单词搜索

/**
 * 给定一个char[][] matrix，也就是char类型的二维数组，再给定一个字符串word，
 * 可以从任何一个某个位置出发，可以走上下左右，能不能找到word？
 * char[][] m =
 * {
 * { 'a', 'b', 'z' },
 * { 'c', 'd', 'o' },
 * { 'f', 'e', 'o' }
 * }
 * 不可以走重复路的情况下，返回能不能找到
 * 比如，word = "zoooz"，是不可以找到的，因为允许走一条路径中已经走过的字符不能重复走
 */
public class Problem_0079_WordSearch {

    public static boolean exist(char[][] board, String word) {
        char[] w = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (func(board, i, j, w, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    //递归含义
    //m为固定输入矩阵
    //从(r,c)位置开始，能否走出word[k...]
    //word[0...k-1]已经搞定了，不用操心了！
    private static boolean func(char[][] m, int row, int col, char[] word, int k) {
        if (k == word.length) {
            //表示[0...word.length-1]已经搞定了
            return true;
        }
        if (row < 0 || row >= m.length || col < 0 || col >= m[0].length) {
            return false;
        }
        if (m[row][col] != word[k]) {
            return false;
        }

        m[row][col] = '.';
        // 走后续
        boolean can = func(m, row - 1, col, word, k + 1) ||
                func(m, row + 1, col, word, k + 1) ||
                func(m, row, col - 1, word, k + 1) ||
                func(m, row, col + 1, word, k + 1);
        m[row][col] = word[k];
        return can;
    }
}
