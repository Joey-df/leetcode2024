package com.joey.od.s200;

import java.util.*;

/**
 * 题目描述
 * 石头剪刀布游戏有 3 种出拳形状：石头、剪刀、布。分别用字母 A , B , C 表示。
 *
 * 游戏规则:
 *
 * 出拳形状之间的胜负规则如下： A > B；B > C；C > A；">"左边一个字母，表示相对优势形状。右边一个字母，表示相对劣势形状。
 * 当本场次中有且仅有一种出拳形状优于其它出拳形状，则该形状的玩家是胜利者。否则认为是平局。
 * 当发生平局，没有赢家。有多个胜利者时，同为赢家。
 * 例如 1： 三个玩家出拳分别是A, B, C ，由于出现三方优势循环(即没有任何一方优于其它出拳者)，判断为平局。
 *
 * 例如 2： 三个玩家，出拳分别是 A, B ，出拳 A 的获胜。
 *
 * 例如 3： 三个玩家，出拳全部是 A ，判为平局。
 *
 * 输入描述
 * 在一场游戏中，每个玩家的信息为一行。玩家数量不超过 1000 。每个玩家信息有 2 个字段，用空格隔开：
 *
 * 玩家 ID：一个仅由英文字母和数字组成的字符串
 * 出拳形状：以英文大写字母表示, A 、B 、C 形状。 例：
 * abc1 A
 * xyz B
 * 
 * 解释：玩家 abc1 出拳为石头( A )。玩家 xyz 出拳为剪刀( B )
 *
 * 输出描述
 * 输出为赢家的玩家 ID 列表(一个或多个)，每个 ID 一行，按字符串升序排列。如果没有赢家，输出为"NULL"字符串。例如：
 *
 * abc1
 * 
 * 示例一
 * 输入
 * abc1 A
 * xyz B
 * 
 * 输出
 * abc1
 * 
 * 说明
 * A 比 B 有优势，abc1 胜出
 *
 * 示例二
 * 输入
 * abc1 A
 * xyz A
 * 
 * 输出
 * NULL
 * 
 * 说明
 * 没有优胜的出拳形状，平局
 *
 * 示例三
 * 输入
 * abc1 A
 * def A
 * alic A
 * xyz B
 * 
 * 输出
 * abc1
 * alic
 * def
 * 
 * 说明
 * A 为优胜方，有三个赢家。
 */

//解题思路
//本题就是童年的石头剪刀布游戏，不用想得太复杂。
//
//用一个哈希表记录所有出A、B、C的人，其中key为字符串A或B或C中的一种，value为出该种形状的任命所构成的列表。若
//
//哈希表中只存在1种键或者3种键均存在，则说明出现平局，输出"NULL"
//哈希表中恰好只存在2种键。若
//只存在字符串A和B，则逐行输出键A对应的列表升序排列的结果
//只存在字符串B和C，则逐行输出键B对应的列表升序排列的结果
//只存在字符串C和A，则逐行输出键C对应的列表升序排列的结果

public class 石头剪刀布游戏 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, List<String>> dic = new HashMap<>();

        while (true) {
            try {
                String name = scanner.next();
                String k = scanner.next();

                if (!dic.containsKey(k)) {
                    dic.put(k, new ArrayList<>());
                }
                dic.get(k).add(name);
            } catch (Exception e) {
                break;
            }
        }

        if (dic.size() != 2) {
            System.out.println("NULL");
        } else {
            List<String> ans = new ArrayList<>();
            if (dic.containsKey("A") && dic.containsKey("B")) {
                ans = dic.get("A");
            } else if (dic.containsKey("B") && dic.containsKey("C")) {
                ans = dic.get("B");
            } else {
                ans = dic.get("C");
            }

            Collections.sort(ans);
            for (String name : ans) {
                System.out.println(name);
            }
        }
    }
}
