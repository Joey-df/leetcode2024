package com.joey.leetcode;

// 1234.替换子串得到平衡字符串
// 有一个只含有 'Q', 'W', 'E', 'R' 四种字符，且长度为 n 的字符串。
// 假如在该字符串中，这四个字符都恰好出现 n/4 次，那么它就是一个「平衡字符串」。
// 给你一个这样的字符串 s，请通过「替换一个子串」的方式，使原字符串 s 变成一个「平衡字符串」。
// 你可以用和「待替换子串」长度相同的 任何 其他字符串来完成替换。
// 请返回待替换子串的最小可能长度。
// 如果原字符串自身就是一个平衡字符串，则返回 0。
// 测试链接 : https://leetcode.cn/problems/replace-the-substring-for-balanced-string/
public class Problem_1234_ReplaceTheSubstringForBalancedString {

    //假设字符串长度40
    //Q：13   多3
    //W：12   多2
    //E：8    缺2
    //R：7    缺3
    //即找到包含3个Q2个W的最短子串，即多出来的一定要替换掉
    //于是变成76题，求最短覆盖子串长度
    public static int balancedString(String s) {
        char[] str = s.toCharArray();
        int n = str.length;
        int[] cnts = new int[4];
        for (char c : str) {
            cnts[index(c)]++;
        }
        int all = 0; //总共欠几个
        for (int i = 0; i < 4; i++) {
            if (cnts[i] <= n / 4) {
                cnts[i] = 0;
            } else {
                cnts[i] -= n / 4;
                all += cnts[i];
            }
        }
        int ans = n;
        //cnts: 欠帐表
        int r = 0;
        for (int i = 0; i < n; i++) { //枚举每个开头位置i，求一个答案，求全局min
            while (r < n && all > 0) {
                //如果在--之前>0，说明是在欠账表中的
                if (cnts[index(str[r])]-- > 0) {
                    all--;
                }
                r++;
            }
            //从while出来两种情况
            //1 r越界了
            //2 r没越界，但是all == 0了
            if (all == 0) {
                ans = Math.min(ans, r - i);
            }
            //i即将++
            if (cnts[index(str[i])]++ == 0) { //如果++之前==0，说明重新欠账
                all++;
            }
        }
        return ans;
    }

    public static int index(char c) {
        return c == 'Q' ? 0 : (c == 'W' ? 1 : (c == 'E' ? 2 : 3));
    }

}
