package com.joey.od.s200;

/**
 * @author pei.liu
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

public class 根据IP查找城市 {
    static class Range {
        String city;
        long startIpDec;
        long endIpDec;
        long ipLen;

        public Range(String city, String startIpStr, String endIpStr) {
            this.city = city;
            // 将IP地址转为整型
            this.startIpDec = ip2Long(startIpStr);
            this.endIpDec = ip2Long(endIpStr);
            this.ipLen = this.endIpDec - this.startIpDec + 1;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Range> ranges = new ArrayList<>();

        // 城市IP列表
        String[] cities = sc.nextLine().split(";");
        // 带查询的IP列表
        String[] queryIps = sc.nextLine().split(",");

        // 提取各个城市IP列表信息
        for (String city : cities) {
            String[] tmp = city.split("[=,]");
            ranges.add(new Range(tmp[0], tmp[1], tmp[2]));
        }

        StringJoiner sj = new StringJoiner(",");

        // 遍历待查询的IP地址
        for (String ip : queryIps) {
            long ipDec = ip2Long(ip);

            // 记录该目标IP地址的最佳匹配城市
            String city = "";
            // 记录最佳匹配城市IP段的长度
            long minLen = Long.MAX_VALUE;

            // 将带查询IP与城市IP段列表逐一匹配
            for (Range range : ranges) {
                // 如果带查询的IP地址 在某城市的IP段范围内，且该城市的IP段长度更小，则该城市为待查询IP的最佳匹配城市
                if (ipDec >= range.startIpDec && ipDec <= range.endIpDec) {

                    // 2024.04.05 根据考友反馈，如果存在区间长度相同的匹配城市，则字典序更大的是最佳匹配城市，此类用例有20%
                    if (minLen > range.ipLen || (minLen == range.ipLen && city.compareTo(range.city) < 0)) {
                        city = range.city;
                        minLen = range.ipLen;
                    }

                }
            }

            sj.add(city);
        }

        System.out.println(sj);
    }

    // IP地址转整型
    public static long ip2Long(String ip) {
        long res = 0;

        int[] blocks = Arrays.stream(ip.split("\\.")).mapToInt(Integer::parseInt).toArray();
        for (int cur : blocks) {
            res = cur | (res << 8);
        }

        return res;
    }
}