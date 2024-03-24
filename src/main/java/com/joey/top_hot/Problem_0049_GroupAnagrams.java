package com.joey.top_hot;

import java.util.*;

/**
 * 49. 字母异位词分组
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * <p>
 * 示例:
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * 说明：
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 */
public class Problem_0049_GroupAnagrams {

    // use hashmap
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
