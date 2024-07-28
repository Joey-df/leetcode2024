package com.joey.leetcode;

// ignore
//hard

//10. 正则表达式匹配
//给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
//'.' 匹配任意单个字符
//'*' 匹配零个或多个前面的那一个元素
//所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
//示例 1：
//输入：s = "aa" p = "a"
//输出：false
//解释："a" 无法匹配 "aa" 整个字符串。
//示例 2:
//输入：s = "aa" p = "a*"
//输出：true
//解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
//示例 3：
//输入：s = "ab" p = ".*"
//输出：true
//解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
//示例 4：
//输入：s = "aab" p = "c*a*b"
//输出：true
//解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
//示例 5：
//输入：s = "mississippi" p = "mis*is*p*."
//输出：false
//
//提示：
//1 <= s.length <= 20
//1 <= p.length <= 20
//s 只包含从 a-z 的小写字母。
//p 只包含从 a-z 的小写字母，以及字符 . 和 *。
//保证每次出现字符 * 时，前面都匹配到有效的字符
public class Problem_0010_RegularExpressionMatching {

    //有效性检查
    public static boolean isValid(char[] str, char[] pattern) {
        //目标串str中不能出现 点 或者 *
        for (char cha : str) {
            if (cha == '.' || cha == '*') {
                return false;
            }
        }
        //pattren串，不能以*开头，并且不能出现相邻的两个*
        for (int i = 0; i < pattern.length; i++) {
            if (pattern[i] == '*' && (i == 0 || pattern[i - 1] == '*')) {
                return false;
            }
        }
        return true;
    }

    // 课堂现场写
    public static boolean isMatch1(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        char[] str = s.toCharArray();
        char[] pattern = p.toCharArray();
        return isValid(str, pattern) && process1(str, pattern, 0, 0);
    }

    // 尝试
    // str[si.....] 能否被 pattern[pi...] 变出来
    // 潜台词：pi位置，pattern[pi] != '*'
    public static boolean process1(char[] str, char[] pattern, int si, int pi) {
        //base case 1
        if (si == str.length) { // si越界了
            if (pi == pattern.length) {
                return true; //空串匹配空串
            }
            //pi没到终止位置，[pi,pi+1]变成空串，&& [pi+2 ...]变成空串
            if (pi + 1 < pattern.length && pattern[pi + 1] == '*') {
                return process1(str, pattern, si, pi + 2);
            }
            return false;
        }
        //base case 2
        if (pi == pattern.length) {
            return si == str.length;
        }
        // si 没越界 pi 没越界 pi+1 不是*
        if (pi + 1 >= pattern.length || pattern[pi + 1] != '*') { //pi+1位置不是*的两种情况
            return ((str[si] == pattern[pi]) || (pattern[pi] == '.'))
                    && process1(str, pattern, si + 1, pi + 1); //si和pi能配上，并且后续能配上
        }
        // si 没越界 pi 没越界 pi+1 是* [pi]不可配[si]
        if (pattern[pi] != '.' && str[si] != pattern[pi]) {
            return process1(str, pattern, si, pi + 2); //pattern[pi,pi+1]变成空串
        }
        // si 没越界 pi 没越界 pi+1 是* [pi]可配[si]
        if (process1(str, pattern, si, pi + 2)) { //x*变成0份
            return true;
        }
        while (si < str.length && (str[si] == pattern[pi] || pattern[pi] == '.')) {
            if (process1(str, pattern, si + 1, pi + 2)) {
                return true;
            }
            si++;
        }
        return false;
    }

    // 改记忆化搜索
    public static boolean isMatch2(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        char[] str = s.toCharArray();
        char[] pattern = p.toCharArray();
        int[][] dp = new int[str.length + 1][pattern.length + 1];
        for (int si = 0; si <= str.length; si++) {
            for (int pi = 0; pi <= pattern.length; pi++) {
                dp[si][pi] = -1;
            }
        }
        // dp[si][pi] == -1
        // dp[si][pi] == 0 si pi false
        // dp[si][pi] == 1 si pi true
        return isValid(str, pattern) && process2(str, pattern, 0, 0, dp);
    }

    // 课堂现场写
    // str[si.....] 能否被 pattern[pi...] 变出来
    // 潜台词：pi位置，pattern[pi] != '*'
    public static boolean process2(char[] str, char[] pattern, int si, int pi, int[][] dp) {
        if (dp[si][pi] != -1) {
            return dp[si][pi] == 1;
        }
        // si pi 这个参数组合第一次算

        if (si == str.length) { // si越界了
            if (pi == pattern.length) {
                dp[si][pi] = 1;
                return true;
            }
            // (pi pi+1) pi+2 ....
            if (pi + 1 < pattern.length && pattern[pi + 1] == '*') {
                boolean ans = process2(str, pattern, si, pi + 2, dp);
                dp[si][pi] = ans ? 1 : 0;
                return ans;
            }
            dp[si][pi] = 0;
            return false;
        }
        // si 没越界
        if (pi == pattern.length) {
            boolean ans = si == str.length;
            dp[si][pi] = ans ? 1 : 0;
            return ans;
        }
        // si 没越界 pi 没越界
        if (pi + 1 >= pattern.length || pattern[pi + 1] != '*') {
            boolean ans = ((str[si] == pattern[pi]) || (pattern[pi] == '.'))
                    && process2(str, pattern, si + 1, pi + 1, dp);
            dp[si][pi] = ans ? 1 : 0;
            return ans;
        }
        // si 没越界 pi 没越界 pi+1 *
        if (pattern[pi] != '.' && str[si] != pattern[pi]) {
            boolean ans = process2(str, pattern, si, pi + 2, dp);
            dp[si][pi] = ans ? 1 : 0;
            return ans;
        }
        if (process2(str, pattern, si, pi + 2, dp)) {
            dp[si][pi] = 1;
            return true;
        }
        while (si < str.length && (str[si] == pattern[pi] || pattern[pi] == '.')) {
            if (process2(str, pattern, si + 1, pi + 2, dp)) {
                dp[si][pi] = 1;
                return true;
            }
            si++;
        }
        dp[si][pi] = 0;
        return false;
    }

}
