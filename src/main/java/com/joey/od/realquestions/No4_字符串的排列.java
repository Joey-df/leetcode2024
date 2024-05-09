package com.joey.od.realquestions;

import java.util.*;

/**
 * @author pei.liu
 */
//LCR 157. 套餐内商品的排列顺序
//https://leetcode.cn/problems/zi-fu-chuan-de-pai-lie-lcof/description/
public class No4_字符串的排列 {

    public static String[] goodsOrder(String goods) {
        if (goods == null || goods.length() == 0) return new String[]{};
        ArrayList<String> clt = new ArrayList<>();
        fun(goods.toCharArray(), 0, clt);
        return clt.stream().toArray(String[]::new);
    }

    //arr:固定参数
    //当前来到index位置做决定，[0...index-1]已经做好决定了，不用操心了
    //path，当前已经形成的路径，复用arr
    //collect，收集答案
    public static void fun(char[] arr, int index,  ArrayList<String> collect) {
        if (index == arr.length) {
            //collect ans
            StringJoiner stringJoiner = new StringJoiner("");
            for (char c : arr) stringJoiner.add(String.valueOf(c));
            collect.add(stringJoiner.toString());
        } else {
            int n = arr.length;
            //index位置还有字符
            //end位置的字符均有机会来到index位置
            Set<Character> set = new HashSet<>(); // 用于对index位置的字符去重
            for (int end = index; end < n; end++) {
                if (!set.contains(arr[end])) {
                    set.add(arr[end]);
                    swap(arr, index, end);
                    fun(arr, index+1, collect);
                    swap(arr, index, end);// clear
                }
            }
        }
    }

    public static void swap(char[] arr, int i,int j) {
        char temp=arr[i];
        arr[i] = arr[j];
        arr[j]=temp;
    }
}
