package com.joey.leetcode;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串""。
 * <p>
 * 示例 1：
 * <p>
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 示例 2：
 * <p>
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-prefix
 */
public class Problem_0014_LongestCommonPrefix {

    public static String longestCommonPrefix(String[] arr) {
        if (arr == null || arr.length == 0) return "";
        String first = arr[0];
        //minIndex初始化为第一个字符串的长度
        //从第二个字符串开始遍历，每个字符串和first进行比对，更新minIndex
        int minIndex = first.length();
        for (int i = 1; i < arr.length; i++) {
            int curIndex = 0;
            String cur = arr[i];
            while (curIndex < first.length() && curIndex < cur.length()
                    && cur.charAt(curIndex) == first.charAt(curIndex)) {
                curIndex++;
            }
            if (curIndex == 0) return "";
            minIndex = Math.min(minIndex, curIndex);
        }
        return first.substring(0, minIndex);
    }
}
