package com.joey.leetcode;

import java.util.HashMap;

/**
 * 166. 分数到小数
 * 给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以 字符串形式返回小数 。
 * 如果小数部分为循环小数，则将循环的部分括在括号内。
 * 如果存在多个答案，只需返回 任意一个 。
 * 对于所有给定的输入，保证 答案字符串的长度小于 104 。
 *
 * 示例 1：
 * 输入：numerator = 1, denominator = 2
 * 输出："0.5"
 * 示例 2：
 * 输入：numerator = 2, denominator = 1
 * 输出："2"
 * 示例 3：
 * 输入：numerator = 2, denominator = 3
 * 输出："0.(6)"
 * 示例 4：
 * 输入：numerator = 4, denominator = 333
 * 输出："0.(012)"
 * 示例 5：
 * 输入：numerator = 1, denominator = 5
 * 输出："0.2"
 *
 * 提示：
 *
 * -231 <= numerator, denominator <= 231 - 1
 * denominator != 0
 */
public class Problem_0166_FractionToRecurringDecimal {

    public static String fractionToDecimal(int numerator, int denominator) {
        //分子为0，直接返回"0"
        if (numerator == 0) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        // 添加符号 "+" or "-"
        res.append(((numerator > 0) ^ (denominator > 0)) ? "-" : "");
        long num = Math.abs((long) numerator); //分子
        long den = Math.abs((long) denominator); //分母
        // integral part（整除的部分）
        res.append(num / den);
        num %= den;
        if (num == 0) { //如果取模结果为0说明可以整除，直接返回
            return res.toString();
        }
        // fractional part（小数部分）
        res.append(".");
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        map.put(num, res.length());
        while (num != 0) {
            num *= 10;
            res.append(num / den);
            num %= den;
            if (map.containsKey(num)) { //代表开始重复，出现循环节，找出num出现的位置，添加左括号，结束
                int index = map.get(num);
                res.insert(index, "(");
                res.append(")");
                break;
            } else {
                map.put(num, res.length()); //记录此时的余数出现在啥位置
            }
        }
        return res.toString();
    }

}
