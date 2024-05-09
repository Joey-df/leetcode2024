package com.joey.top_hot;

/**
 * @author pei.liu
 */
//906. 超级回文数
//如果一个正整数自身是回文数，而且它也是一个回文数的平方，那么我们称这个数为超级回文数。
//现在，给定两个正整数 L 和 R （以字符串形式表示），返回包含在范围 [L, R] 中的超级回文数的数目。
//
//示例：
//输入：L = "4", R = "1000"
//输出：4
//解释：
//4，9，121，以及 484 是超级回文数。
//注意 676 不是一个超级回文数： 26 * 26 = 676，但是 26 不是回文数。
//提示：
//1 <= len(L) <= 18
//1 <= len(R) <= 18
//L 和 R 是表示 [1, 10^18) 范围的整数的字符串。
//int(L) <= int(R)
public class Problem_0906_SuperpalindromesInRange {

    //思路其实很直观
    //按照范围去找基础数字i，然后构建回文数字m，然后判断 m的平方 是否在范围内以及是否是回文，是的话则计数器+1
    //i的查找范围：题目告诉我们范围是10^18，那么R就是10^9，而i保守可以设置到10^5,范围是[1,100000]
    //回文存在两种情况：偶数和奇数，为了处理简单，也分开来遍历
    //注意事项：考虑到数值范围，计算得用long
    public int superpalindromesInRange(String left, String right) {
        int res = 0;
        long l = Long.parseLong(left);
        long r = Long.parseLong(right);
        // 先判断偶数的情况 ABCCBA
        for (int i = 1; i < 100000; i++) {
            String s = String.valueOf(i);
            String s2 = reverse(s);
            long m = Long.parseLong(s + s2);
            m *= m;
            // 超过范围提前结束遍历
            if (m > r) {
                break;
            } else if (m >= l && isPalindrom(m)) {
                res++;
            }
        }

        // 再判断奇数的情况 ABCBA
        for (int i = 1; i < 100000; i++) {
            String s = String.valueOf(i);
            String s2 = reverse(s);
            s2 = s2.substring(1);
            long m = Long.parseLong(s + s2);
            m *= m;
            // 超过范围提前结束遍历
            if (m > r) {
                break;
            } else if (m >= l && isPalindrom(m)) {
                res++;
            }
        }
        return res;
    }

    //翻转字符串
    private String reverse(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] str = s.toCharArray();
        int n = str.length;
        int l = 0;
        int r = n - 1;
        while (l < r) {
            char t = str[l];
            str[l] = str[r];
            str[r] = t;
            l++;
            r--;
        }
        return new String(str);
    }

    public boolean isPalindrom(long num) {
        long n = num;
        // 倒序的数字
        long ans = 0;
        while (n > 0) {
            ans = ans * 10 + n % 10;
            n /= 10;
        }
        // 直接比较是否相等即可
        return ans == num;
    }

}
