package com.joey.leetcode;

// ignore
//KMP
public class Problem_0028_ImplementStrStr {

    // 在s中寻找是否包含m字符串，
    // 如果包含返回起始位置，不包含返回-1
    // 前提：s、m都不为空
    public static int findIndex(String s, String m) {
        if (s.length() < m.length()) return -1;
        char[] str = s.toCharArray();
        char[] match = m.toCharArray();
        int x = 0;//s中对比已经来到的位置
        int y = 0;//match中对比已经来到的位置
        int[] nexts = getNexts(match);
        while (x < str.length && y < match.length) {
            if (str[x] == match[y]) {
                x++;
                y++;
            } else if (nexts[y] != -1) { //y还没有跳到0
                y = nexts[y];
            } else {// nexts[y]==-1 即 y==0
                x++;
            }
        }
        return y == match.length ? x - y : -1;
    }

    //生成nexts数组
    //nexts[i]的含义：[0...i-1]范围上不取到整体的 前缀等于后缀的最大长度
    private static int[] getNexts(char[] match) {
        if (match.length == 1) {
            return new int[]{-1};
        }
        int[] nexts = new int[match.length];
        nexts[0] = -1;//人为规定
        nexts[1] = 0; //人为规定
        int i = 2;
        int cn = 0; // 表示nexts[i-1]的值; cn位置的字符是和当前i-1位置比较的字符
        while (i < match.length) {
            if (match[i - 1] == match[cn]) {
                nexts[i] = cn + 1;
                i++;
                cn++;
            } else if (nexts[cn] != -1) { //代表 cn>0 即 还没跳到0
                cn = nexts[cn];//cn往左跳
            } else { //cn==0
                nexts[i++] = 0;
            }
        }
        return nexts;
    }

    public static void main(String[] args) {
        String s = "sadasabc1234def";
        String m = "abc1234";
        System.out.println(findIndex(s, m));
    }

}
