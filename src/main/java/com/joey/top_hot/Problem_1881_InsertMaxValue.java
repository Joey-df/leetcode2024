package com.joey.top_hot;

/**
 * @author pei.liu
 */
//1881. 插入后的最大值
//给你一个非常大的整数 n 和一个整数数字 x ，大整数 n 用一个字符串表示。n 中每一位数字和数字 x 都处于闭区间 [1, 9] 中，且 n 可能表示一个 负数 。
//你打算通过在 n 的十进制表示的任意位置插入 x 来 最大化 n 的 数值。但 不能 在负号的左边插入 x 。
//例如，如果 n = 73 且 x = 6 ，那么最佳方案是将 6 插入 7 和 3 之间，使 n = 763 。
//如果 n = -55 且 x = 2 ，那么最佳方案是将 2 插在第一个 5 之前，使 n = -255 。
//返回插入操作后，用字符串表示的 n 的最大值。
//
//示例 1：
//输入：n = "99", x = 9
//输出："999"
//解释：不管在哪里插入 9 ，结果都是相同的。
//示例 2：
//输入：n = "-13", x = 2
//输出："-123"
//解释：向 n 中插入 x 可以得到 -213、-123 或者 -132 ，三者中最大的是 -123 。
//
//提示：
//1 <= n.length <= 10^5
//1 <= x <= 9
//n中每一位的数字都在闭区间 [1, 9] 中。
//n 代表一个有效的整数。
//当 n 表示负数时，将会以字符 '-' 开始。
public class Problem_1881_InsertMaxValue {

    //如果n是正数，从右往左，找最后一个小于x的位置，插到它前面，比如m=512，x=9；m=456，x=1
    //如果n是负数，从右往左，找最后一个大于x的位置，插到它前面，比如m=-13，x=9；m=-13，x=2
    public String maxValue(String num, int x) {
        boolean neg = false;
        if (num.charAt(0) == '-') {
            neg = true;
            num = num.substring(1);
        }
        int n = num.length();
        char[] chararr = num.toCharArray();
        int[] arr = new int[n];
        for (int i = 0; i < chararr.length; i++) {
            arr[i] = chararr[i] - '0';
        }
        int[] help = new int[n + 1]; //要插入x，所以是 n+1
        int m = help.length;
        //插入位置，初始化为m-1
        int insert = m - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (neg) { //如果n是负数，从右往左，找最后一个大于x的位置，插到它前面，比如m=-13，x=9；m=-13，x=2
                if (arr[i] > x) {
                    insert = i;
                }
            } else { //如果n是正数，从右往左，找最后一个小于x的位置，插到它前面，比如m=512，x=9；m=456，x=1
                if (arr[i] < x) {
                    insert = i;
                }
            }
        }

        //插入
        for (int i = 0; i < insert; i++) {
            help[i] = arr[i];
        }
        help[insert] = x;
        if (insert < m - 1) {
            for (int i = insert + 1; i < m; i++) {
                help[i] = arr[i - 1];
            }
        }

        StringBuilder builder = new StringBuilder();
        if (neg) {
            builder.append("-");
        }
        for (int e : help) {
            builder.append(e);
        }
        return builder.toString();
    }

}
