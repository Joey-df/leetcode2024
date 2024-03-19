package com.joey.top_hot;

//289. 生命游戏
// 有关这个游戏更有意思、更完整的内容：
// https://www.bilibili.com/video/BV1rJ411n7ri
// 也推荐这个up主
public class Problem_0289_GameOfLife {

    public static void gameOfLife(int[][] board) {
        int N = board.length;
        int M = board[0].length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int neighbors = neighbors(board, i, j);
                //如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
                //如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
                if (neighbors == 3 //不管是0还是1，只要邻居有3个1，下一步都是1
                        || (board[i][j] == 1 && neighbors == 2)) { //1的周围有2个1，下一步是1
                    board[i][j] |= 2; //第二个二进制位变1
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                board[i][j] >>= 1;
            }
        }
    }

    // b[i][j] 这个位置的数，周围8个方向一共有几个1
    public static int neighbors(int[][] b, int i, int j) {
        return f(b, i - 1, j - 1)
                + f(b, i - 1, j)
                + f(b, i - 1, j + 1)
                + f(b, i, j - 1)
                + f(b, i, j + 1)
                + f(b, i + 1, j - 1)
                + f(b, i + 1, j)
                + f(b, i + 1, j + 1);
    }

    // b[i][j] 上面有1，就返回1，上面不是1，就返回0
    public static int f(int[][] b, int i, int j) {
        return (i >= 0 && i < b.length && j >= 0 && j < b[0].length
                && (b[i][j] & 1) == 1) //不越界 并且 末位为1（因为第二位可能被改过）
                ? 1 : 0;
    }
}
