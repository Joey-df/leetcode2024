package com.joey.top_hot;

import java.util.LinkedList;

/**
 * 224. 基本计算器
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
 * s 表示一个有效的表达式
 * <p>
 * 示例 1：
 * 输入：s = "1 + 1"
 * 输出：2
 * 示例 2：
 * 输入：s = " 2-1 + 2 "
 * 输出：3
 * 示例 3：
 * 输入：s = "(1+(4+5+2)-3)+(6+8)"
 * 输出：23
 */
public class Problem_0224_BasicCalculator {

    public static int calculate(String str) {
        return f(str.toCharArray(), 0)[0];
    }

    // 请从str[i...]往下算，遇到字符串终止位置或者右括号，就停止
    // 返回两个值，长度为2的数组
    // 0) 负责的这一段的结果是多少
    // 1) 负责的这一段计算到了哪个位置
    public static int[] f(char[] str, int i) {
        //每个子过程一个que(栈)
        LinkedList<String> que = new LinkedList<>();
        int cur = 0;
        int[] sub = null;
        // 从i出发，开始撸串，越界或者遇到 ) 停
        while (i < str.length && str[i] != ')') {
            if (str[i] == ' ') { //如果遇到的是空格，直接跳过
                i++;
                continue;
            }
            if (str[i] >= '0' && str[i] <= '9') { //遇到的是数字字符，直接累加当前数字
                cur = cur * 10 + str[i++] - '0';
            } else if (str[i] != '(') { // 遇到的是 + - * / 运算符号
                //每次必定都是压一个数字和一个运算符
                addNum(que, cur); //把当前数字加进去
                que.addLast(String.valueOf(str[i++]));//把运算符加进去
                cur = 0; //遇到运算符就把cur清零
            } else { // 遇到左括号了，交给子过程
                sub = f(str, i + 1);
                cur = sub[0];
                i = sub[1] + 1;
            }
        }
        //while跑完的时候，还有最后一个数字cur还没放进去，要压进去
        addNum(que, cur);
        //最后栈中只剩下数字和加减号，直接计算结果返回
        return new int[] { getNum(que), i };
    }

    //向栈中压入数字num
    public static void addNum(LinkedList<String> que, int num) {
        //如果栈不为空，先判断栈顶的运算符，如果是* /需要先将，倒数第二位的数和num做乘除运算，然后压进去
        if (!que.isEmpty()) {
            int cur = 0;
            String op = que.pollLast(); //先弹出栈顶（运算符号）
            if (op.equals("+") || op.equals("-")) {
                que.addLast(op); //如果是加减号，拿出来再重新放回去
            } else {
                //如果是乘除号，将倒数第二位置的数字与即将进来的num做运算后，得到的结果，放到队尾
                cur = Integer.valueOf(que.pollLast());
                num = op.equals("*") ? (cur * num) : (cur / num);
            }
        }
        que.addLast(String.valueOf(num));
    }

    //que中只剩下数字和加减号
    //计算que中的结果
    //从头到尾遍历，计算（这里体现出必须要用LinkedList）
    public static int getNum(LinkedList<String> que) {
        int res = 0;
        boolean add = true;
        String cur = null;
        int num = 0;
        while (!que.isEmpty()) {
            cur = que.pollFirst();
            if (cur.equals("+")) {
                add = true;
            } else if (cur.equals("-")) {
                add = false;
            } else { //数字
                num = Integer.valueOf(cur);
                res += add ? num : (-num);
            }
        }
        return res;
    }

}
