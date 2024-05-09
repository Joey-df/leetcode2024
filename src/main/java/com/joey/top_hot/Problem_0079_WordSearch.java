package com.joey.top_hot;

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
//比如，word = "zoooz"，是不可以找到的，因为允许走一条路径中已经走过的字符不能重复走
public class Problem_0079_WordSearch {

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (fun(board, i, j, word.toCharArray(), 0)) {
                    return true;
                }
            }
        }
        return false;
    }


    //递归含义：
    //从(i,j)出发能不能走出word[k...]，word[...k-1]都不用再考虑了
    private boolean fun(char[][] board, int i, int j, char[] word, int k) {
        if (k == word.length) return true;
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }
        //(i,j)不越界
        if (board[i][j] != word[k]) {
            return false;
        }
        // board[i][j] == word[k]
        char c = board[i][j];
        board[i][j] = '0';
        boolean ans = fun(board, i - 1, j, word, k + 1) ||
                fun(board, i + 1, j, word, k + 1) ||
                fun(board, i, j - 1, word, k + 1) ||
                fun(board, i, j + 1, word, k + 1);
        board[i][j] = c; //恢复
        return ans;
    }

}
