package com.joey.od.realquestions;

import java.util.Arrays;

/**
 * @author pei.liu
 */
//https://blog.csdn.net/misayaaaaa/article/details/130426636?spm=1001.2014.3001.5501
public class No5_Lc_3_最长不重复子串 {

    //abcab
    public static int fun(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] str = s.toCharArray();
        int n = str.length;
        int[] map = new int[256];//存放每个位置字符上次出现的位置
        for (int i = 0; i < map.length; i++) {
            map[i] = -1;
        }
        map[str[0]] = 0;
        //init
        int pre = 1;
        int ans = 1;
        for (int i = 1; i < n; i++) {
            int cur = Math.min(pre + 1, i - map[str[i]]);
            ans = Math.max(ans, cur);
            //update
            pre = cur;
            //set map
            map[str[i]] = i;
        }
        return ans;
    }
}
