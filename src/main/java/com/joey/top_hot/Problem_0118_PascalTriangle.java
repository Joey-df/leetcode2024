package com.joey.top_hot;

import java.util.ArrayList;
import java.util.List;

/**
 * 118. 杨辉三角
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * 示例:
 * 输入: 5
 * 输出:
 * [
 * [1],
 * [1,1],
 * [1,2,1],
 * [1,3,3,1],
 * [1,4,6,4,1]
 * ]
 */
public class Problem_0118_PascalTriangle {

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            ans.add(new ArrayList<>());
            ans.get(i).add(1); //第一列
        }
        for (int i = 1; i < numRows; i++) {
            for (int j = 1; j < i; j++) {
                //普通位置：右上角+上方
                int p1 = ans.get(i - 1).get(j - 1); //左上角
                int p2 = ans.get(i - 1).get(j); //上方
                int cur = p1 + p2;
                ans.get(i).add(cur);
            }
            ans.get(i).add(1); //右腰线（对角线）
        }
        return ans;
    }

}