package com.joey.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pei.liu
 */
public class Problem_1146_SnapshotArray {

    //并不需要每次在获取快照时都clone出一个数组
    static class SnapshotArray {

        static class Info {
            int snapId; //快照id
            int val; //修改的值

            public Info(int snapId, int val) {
                this.snapId = snapId;
                this.val = val;
            }
        }

        int snapId;
        List<List<Info>> infos;

        public SnapshotArray(int length) {
            infos = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                infos.add(new ArrayList<>());
            }
        }

        //0 <= index < length
        public void set(int index, int val) {
            infos.get(index)
                    .add(new Info(snapId, val));
        }

        public int snap() {
            return snapId++;
        }

        public int get(int index, int snap_id) {
            List<Info> arr = infos.get(index); //index位置的修改记录（快照id,val），快照id升序
            if (arr.isEmpty()) return 0;
            //找 <= snap_id 最右的位置最用的info.val
            int l = 0;
            int r = arr.size() - 1;
            int hit = -1;
            while (l <= r) {
                int m = l + (r - l) / 2;
                if (arr.get(m).snapId <= snap_id) {
                    hit = m;
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            return hit == -1 ? 0 : arr.get(hit).val;
        }
    }
}
