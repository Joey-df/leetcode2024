package com.joey.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author pei.liu
 */
public class Problem_0981_TimeBasedKeyValueStore {


    static class TimeMap {

        static class Info {
            String value;
            int time;

            public Info(String value, int time) {
                this.value = value;
                this.time = time;
            }
        }

        HashMap<String, List<Info>> store;

        public TimeMap() {
            store = new HashMap<>();
        }

        //set 操作中的时间戳 timestamp 都是严格递增的
        public void set(String key, String value, int timestamp) {
            store.computeIfAbsent(key, k -> new ArrayList<>())
                    .add(new Info(value, timestamp));
        }

        public String get(String key, int timestamp) {
            if (!store.containsKey(key)) return "";
            List<Info> infos = store.get(key);
            int l = 0;
            int r = infos.size() - 1;
            int hit = infos.size();
            while (l <= r) {
                int m = l + (r - l) / 2;
                if (infos.get(m).time <= timestamp) {
                    hit = m;
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            return hit == infos.size() ? "" : infos.get(hit).value;
        }
    }
}
