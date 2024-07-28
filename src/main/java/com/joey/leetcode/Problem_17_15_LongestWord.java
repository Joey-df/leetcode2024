package com.joey.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author pei.liu
 */
//面试题 17.15. 最长单词
//给定一组单词words，编写一个程序，找出其中的最长单词，且该单词由这组单词中的其他单词组合而成。
//若有多个长度相同的结果，返回其中字典序最小的一项，若没有符合要求的单词则返回空字符串。
//
//示例：
//输入： ["cat","banana","dog","nana","walk","walker","dogwalker"]
//输出： "dogwalker"
//解释： "dogwalker"可由"dog"和"walker"组成。
//提示：
//0 <= len(words) <= 200
//1 <= len(words[i]) <= 100
public class Problem_17_15_LongestWord {

    public String longestWord(String[] words) {
        Arrays.sort(words, (o1, o2) -> {
            if (o1.length() == o2.length())
                return o1.compareTo(o2); //如果长度相同，按字典序升序排
            else {
                return Integer.compare(o2.length(), o1.length()); //如果长度不同，长度长的排前面
            }
        });

        Set<String> set = new HashSet<>(Arrays.asList(words));
        //words已经排过序了（长度大的排前面，长度一样字典序小的排前面）
        for (String word : words) {
            set.remove(word); //先在单词表中把当前单词删掉，然后判断是否可以被单词表分解
            if (find(set, word)) {
                return word;
            }
        }
        return "";
    }

    //给定单词表set，判断，word是否可以被set中的单词分解
    public boolean find(Set<String> set, String word) {
        if (word.length() == 0) {
            return true;
        }
        for (int i = 0; i < word.length(); i++) {
            if (set.contains(word.substring(0, i + 1)) && find(set, word.substring(i + 1))) {
                return true;
            }
        }
        return false;
    }

}
