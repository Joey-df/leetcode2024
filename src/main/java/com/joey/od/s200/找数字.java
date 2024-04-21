package com.joey.od.s200;

/**
 * @author pei.liu
 */

import java.util.Scanner;

public class 找数字 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        // 将整数n转为二进制字符串
        String nBinStr = "0" + Integer.toBinaryString(n);

        char[] mBinCharArr = nBinStr.toCharArray();

        int countOne = 0;

        for (int i = mBinCharArr.length - 2; i >= 0; i--) {
            if (mBinCharArr[i] == '0' && mBinCharArr[i + 1] == '1') {
                // 从右向左找到了第一组"01"子串，则替换为"10"
                mBinCharArr[i] = '1';
                mBinCharArr[i + 1] = '0';

                // 如果第一组"01"子串右边存在1
                if (countOne > 0) {
                    // 则将第一组"01"子串的右边部分的'1'要全部集中到尾部
                    for (int j = i + 2; j < mBinCharArr.length; j++) {
                        if (j < mBinCharArr.length - countOne) {
                            mBinCharArr[j] = '0';
                        } else {
                            mBinCharArr[j] = '1';
                        }
                    }
                }

                break; // important
            }

            if (mBinCharArr[i + 1] == '1') countOne++; // 记录第一组"01"子串右边1的个数
        }

        int m = Integer.parseInt(new String(mBinCharArr), 2);
        System.out.println(m);
    }
}