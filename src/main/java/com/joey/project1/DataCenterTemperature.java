package com.joey.project1;


//某地数据中心有多台型号不一的服务器，每台服务器都有自己的最佳运行温度区间，当数据中心温度在其最佳温度区间时，这台服务器的处理能力最大；
//而在其他温度时，服务器的处理能力会有不同程度的衰减。因此，可以通过调节数据中心温度来调节服务器的处理能力。
//这些服务器的最佳工作温度区间记录于scopes中，scopes[i]=[lowT,highT]，表示服务器最佳温度区间(左闭右闭)，
//服务器的处理能力记录于capabilities中，capabilities=[valX, valY, valZ]代表服务器的处理能力值：
//如果致数据中心温度T小于lowT，则服务器处理能力为valX；
//如果数据中心温度T处于服务器最佳温度区间，则服务器处理能力为valY；
//如果数据中心温度T大于highT，则服务器处理能力为valZ；
//请给数据中心设置一个合理的温度，使得所有服务器的处理能力之和最大，并输出这个最大值。

import java.util.*;

public class DataCenterTemperature {
    static class Capabilities {
        int valX; // 温度小于最佳区间时的能力值
        int valY; // 温度在最佳区间时的能力值
        int valZ; // 温度大于最佳区间时的能力值

        public Capabilities(int valX, int valY, int valZ) {
            this.valX = valX;
            this.valY = valY;
            this.valZ = valZ;
        }
    }

    public static int maximizeProcessingPower(int[][] scopes, Capabilities capabilities) {
        // Step 1: 找到全局温度范围
        int minTemp = Integer.MAX_VALUE;
        int maxTemp = Integer.MIN_VALUE;

        for (int[] scope : scopes) {
            minTemp = Math.min(minTemp, scope[0]);
            maxTemp = Math.max(maxTemp, scope[1]);
        }

        // Step 2: 初始化差分数组和前缀和数组
        int[] diff = new int[maxTemp + 2]; // 多一位防止越界
        int[] prefixSum = new int[maxTemp + 2]; // 前缀和数组

        // Step 3: 构建差分数组
        for (int[] scope : scopes) {
            int lowT = scope[0];
            int highT = scope[1];
            diff[lowT] += capabilities.valY - capabilities.valX; // 进入最佳区间
            diff[highT + 1] -= capabilities.valY - capabilities.valX; // 离开最佳区间
        }

        // Step 4: 计算前缀和数组（代表每个温度点处理能力提升的幅度）
        for (int temp = minTemp; temp <= maxTemp; temp++) {
            prefixSum[temp] = temp == minTemp ? diff[temp] : prefixSum[temp - 1] + diff[temp];
        }

        // Step 5: 遍历所有温度点，计算总能力
        int totalServers = scopes.length;
        int maxProcessingPower = Integer.MIN_VALUE; // 初始化为基础能力
        int basePower = capabilities.valX * totalServers;

        for (int temp = minTemp; temp <= maxTemp; temp++) {
            int totalPower = prefixSum[temp] + basePower; // 当前温度的总能力
            maxProcessingPower = Math.max(maxProcessingPower, totalPower); // 更新最大值
        }

        // Step 6: 处理温度高于所有区间的情况(如果温度高处理能力高才有这种情况)
        int highTempPower = totalServers * capabilities.valZ; // 所有服务器能力为 valZ
        maxProcessingPower = Math.max(maxProcessingPower, highTempPower);

        return maxProcessingPower;
    }


    public static int maximizeProcessingPower2(int[][] scopes, Capabilities capabilities) {
        // Step 1: 找到温度的全局范围
        int minTemp = Integer.MAX_VALUE;
        int maxTemp = Integer.MIN_VALUE;

        for (int[] scope : scopes) {
            minTemp = Math.min(minTemp, scope[0]);
            maxTemp = Math.max(maxTemp, scope[1]);
        }

        // Step 2: 枚举所有温度点
        int maxProcessingPower = Integer.MIN_VALUE;

        for (int temp = minTemp; temp <= maxTemp; temp++) {
            int totalPower = 0;

            // Step 3: 计算当前温度下的总处理能力
            for (int[] scope : scopes) {
                if (temp < scope[0]) {
                    totalPower += capabilities.valX; // 小于最佳范围
                } else if (temp > scope[1]) {
                    totalPower += capabilities.valZ; // 大于最佳范围
                } else {
                    totalPower += capabilities.valY; // 在最佳范围内
                }
            }

            // Step 4: 更新最大值
            maxProcessingPower = Math.max(maxProcessingPower, totalPower);
        }

        // Step 6: 处理温度高于所有区间的情况(如果温度高处理能力高才有这种情况)
        int highTempPower = scopes.length * capabilities.valZ; // 所有服务器能力为 valZ
        maxProcessingPower = Math.max(maxProcessingPower, highTempPower);
        return maxProcessingPower;
    }

    public static void main(String[] args) {
        int[][] scopes = {
                {10, 20},
                {15, 25},
                {30, 35}
        };
        Capabilities capabilities = new Capabilities(5, 10, 3);

        int result1 = maximizeProcessingPower(scopes, capabilities);
        System.out.println("result1: " + result1);

        int result2 = maximizeProcessingPower2(scopes, capabilities);
        System.out.println("result2: " + result2);

        int[][] scopes1 = {
                {3, 26},
                {20, 24},
                {100, 240},
                {120, 214},
                {5, 1000},
                {30, 60}
        };
        Capabilities capabilities1 = new Capabilities(34, 69, 2);

        int result3 = maximizeProcessingPower(scopes1, capabilities1);
        System.out.println("result3: " + result3);

        int result4 = maximizeProcessingPower2(scopes1, capabilities1);
        System.out.println("result4: " + result4);
    }
}
