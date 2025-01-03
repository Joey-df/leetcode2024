package com.joey.leetcode;

import java.util.*;

/**
 * @author pei.liu
 */
//911. 在线选举
//给你两个整数数组 persons 和 times 。在选举中，第 i 张票是在时刻为 times[i] 时投给候选人 persons[i] 的。
//对于发生在时刻 t 的每个查询，需要找出在 t 时刻在选举中领先的候选人的编号。
//在 t 时刻投出的选票也将被计入我们的查询之中。在平局的情况下，最近获得投票的候选人将会获胜。
//实现 TopVotedCandidate 类：
//TopVotedCandidate(int[] persons, int[] times) 使用 persons 和 times 数组初始化对象。
//int q(int t) 根据前面描述的规则，返回在时刻 t 在选举中领先的候选人的编号。
//
//示例：
//输入：
//["TopVotedCandidate", "q", "q", "q", "q", "q", "q"]
//[[[0, 1, 1, 0, 0, 1, 0], [0, 5, 10, 15, 20, 25, 30]], [3], [12], [25], [15], [24], [8]]
//输出：
//[null, 0, 1, 1, 0, 0, 1]
//解释：
//TopVotedCandidate topVotedCandidate = new TopVotedCandidate([0, 1, 1, 0, 0, 1, 0], [0, 5, 10, 15, 20, 25, 30]);
//topVotedCandidate.q(3); // 返回 0 ，在时刻 3 ，票数分布为 [0] ，编号为 0 的候选人领先。
//topVotedCandidate.q(12); // 返回 1 ，在时刻 12 ，票数分布为 [0,1,1] ，编号为 1 的候选人领先。
//topVotedCandidate.q(25); // 返回 1 ，在时刻 25 ，票数分布为 [0,1,1,0,0,1] ，编号为 1 的候选人领先。（在平局的情况下，1 是最近获得投票的候选人）。
//topVotedCandidate.q(15); // 返回 0
//topVotedCandidate.q(24); // 返回 0
//topVotedCandidate.q(8); // 返回 1
//
//提示：
//1 <= persons.length <= 5000
//times.length == persons.length
//0 <= persons[i] < persons.length
//0 <= times[i] <= 10^9
//times 是一个严格递增的有序数组
//times[0] <= t <= 10^9
//每个测试用例最多调用 10^4 次 q
public class Problem_0911_OnlineElection {

    //q(t)：在time[] 中查询 <= t 最右的位置，假如这个位置是 i，统计[0,i]范围上词频最大的人，如果有多个相同的词频，返回i位置的人
    //如何快速统计[0,i]范围上词频最大的人，需要在构造函数中加工好一个结构，查询时O(1)返回
    static class TopVotedCandidate {

        static class Info {
            int person;
            int cnt;
            int time;

            public Info(int person, int cnt, int time) {
                this.person = person;
                this.cnt = cnt;
                this.time = time;
            }
        }

        int[] times;
        PriorityQueue<Info> q;
        int n;
        Map<Integer, Integer> cnts;
        //key: time
        //value: 胜出的人
        Map<Integer, Integer> clt;

        public TopVotedCandidate(int[] persons, int[] times) {
            n = persons.length;
            this.times = Arrays.copyOfRange(times, 0, n);
            q = new PriorityQueue<>((a, b) -> a.cnt != b.cnt ? b.cnt - a.cnt : b.time - a.time);
            cnts = new HashMap<>();
            clt = new HashMap<>();
            for (int i = 0; i < n; i++) {
                int p = persons[i];
                int t = times[i];
                int pre = cnts.getOrDefault(p, 0);
                cnts.put(p, pre + 1);
                q.offer(new Info(p, cnts.get(p), t));
                clt.put(t, q.peek().person);
            }
        }

        public int q(int t) {
            int l = 0;
            int r = n - 1;
            int hit = -1;
            while (l <= r) {
                int m = l + (r - l) / 2;
                if (times[m] <= t) {
                    hit = m;
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            return clt.get(times[hit]);
        }
    }

    public static void main(String[] args) {
        int[] persons = {0, 1, 1, 0, 0, 1, 0};
        int[] times = {0, 5, 10, 15, 20, 25, 30};
        TopVotedCandidate votedCandidate = new TopVotedCandidate(persons, times);
        System.out.println(votedCandidate.q(3));
        System.out.println(votedCandidate.q(12));
        System.out.println(votedCandidate.q(25));
        System.out.println(votedCandidate.q(15));
        System.out.println(votedCandidate.q(24));
        System.out.println(votedCandidate.q(8));
    }

}
