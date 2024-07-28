package com.joey.leetcode;

import java.util.*;

//49. 字母异位词分组
//给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
//
//示例:
//输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出:
//[
//  ["ate","eat","tea"],
//  ["nat","tan"],
//  ["bat"]
//]
//说明：
//所有输入均为小写字母。
//不考虑答案输出的顺序。
public class Problem_0049_GroupAnagrams {

    // use hashmap
    // 思路：
    // 使用一个hashmap，key：每个字符串排序后的结果：value：存在异位词的list
    // 遍历数组，先对每个字符串排序，存入map对应key的list中（需要注意：当key对应的list不存在时，先创建list）
    // 最后遍历map。收集答案
    public List<List<String>> groupAnagrams(String[] arr) {
        List<List<String>> ans = new ArrayList<>();
        if (arr==null || arr.length==0) return ans;
        Map<String, List<String>> map = new HashMap<>();
        for (String str: arr) {
            char[] cur = str.toCharArray();
            Arrays.sort(cur);
            String key = String.valueOf(cur);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }
        for (String key: map.keySet()) {
            ans.add(map.get(key));
        }
        return ans;
    }

}
