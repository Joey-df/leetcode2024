package com.joey.leetcode;

import java.util.*;

//218. The Skyline Problem
public class Problem_0218_TheSkylineProblem {

    //流程设计：扫描线
    //扫描线一般需要先保证给定的区间有序
    //每栋大楼有start，有end，即 + 和 -
    //其实就是每个端点时求当前最大
    //可以使用的数据结构：大根堆、有序表

    static class Info {
        int x; // 位置
        int type; // 0:+ 1:-
        int height; // 高度

        public Info(int x, int type, int height) {
            this.x = x;
            this.type = type;
            this.height = height;
        }
    }

    public static List<List<Integer>> getSkyline(int[][] buildings) {
        int n = buildings.length;
        Info[] infos = new Info[n * 2];
        for (int i = 0; i < n; i++) {
            int[] building = buildings[i];
            int start = building[0];
            int end = building[1];
            int h = building[2];
            infos[2 * i] = new Info(start, 0, h);
            infos[2 * i + 1] = new Info(end, 1, h);
        }
        Arrays.sort(infos, (a, b) -> {
            if (a.x != b.x) {
                return a.x - b.x;
            }
            return a.type - b.type; //+的动作排前面，防止纸片大楼
        });

        //key：当前高度
        //value：出现的次数
        TreeMap<Integer, Integer> heightTimes = new TreeMap<>();
        //key：x位置
        //value：高度
        TreeMap<Integer, Integer> xHeight = new TreeMap<>();
        for (Info info : infos) {
            //根据type加或者减
            int type = info.type;
            int height = info.height;
            int x = info.x;
            int pre = heightTimes.getOrDefault(height, 0);
            if (type == 0) { //+
                heightTimes.put(height, pre + 1);
            } else { //-
                if (pre == 1) {
                    heightTimes.remove(height);
                } else {
                    heightTimes.put(height, pre - 1);
                }
            }
            //获取当前x位置的最大高度(保证每次都是操作在有序表中更新完当前x，再收集答案)
            if (heightTimes.isEmpty()) {
                xHeight.put(x, 0);
            } else {
                xHeight.put(x, heightTimes.lastKey());
            }
        }
        //生成答案
        List<int[]> clt = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : xHeight.entrySet()) {
            int x = entry.getKey();
            int height = entry.getValue();
            clt.add(new int[]{x, height});
        }
        List<List<Integer>> ans = new ArrayList<>();
        int[] first = clt.get(0);
        ans.add(Arrays.asList(first[0], first[1]));
        for (int i = 1; i < clt.size(); i++) {
            int[] pre = clt.get(i - 1);
            int[] c = clt.get(i);
            if (c[1] != pre[1]) {
                ans.add(Arrays.asList(c[0], c[1]));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] buildings = {
                {2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}
        };
        System.out.println(getSkyline(buildings));
    }

}
