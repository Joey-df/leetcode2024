package com.joey.top_hot;

/**
 * 322. 零钱兑换
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。
 * 你可以认为每种硬币的数量是无限的。
 * 示例 1：
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * 示例 2：
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * 示例 3：
 * 输入：coins = [1], amount = 0
 * 输出：0
 * 示例 4：
 * 输入：coins = [1], amount = 1
 * 输出：1
 * 示例 5：
 * 输入：coins = [1], amount = 2
 * 输出：2
 */
public class Problem_0322_CoinChange {

    //方法1：暴力尝试
    public static int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount < 0) {
            return 0;
        }
        return process(coins,0, amount);
    }

    //递归含义：
    //coins[i...]自由选择，搞定rest所需的最少硬币数量，返回
    public static int process(int[] coins, int i, int rest) {
        //因为下面的for循环决定了rest不可能取到0以下
        //if (rest < 0) { //负数无法搞定
        //    return -1;
        //}
        if (i == coins.length) {//没钱了，coins[index...]为无效区域了
            //没有硬币可以选择，搞定0元需要0个硬币，否则没有方案
            return rest == 0 ? 0 : -1;
        }
        //rest > 0 && index < coins.length
        //普遍位置: 枚举coins[index] 所需的数量k
        int ways = Integer.MAX_VALUE;
        for (int k = 0; k * coins[i] <= rest; k++) {
            //rest - k * coins[index]不可能小于0
            int p1 = process(coins, i + 1, rest - k * coins[i]);
            if (p1 != -1) {
                ways = Math.min(ways, p1 + k);
            }
        }
        return ways == Integer.MAX_VALUE ? -1 : ways;
    }

    //方法2
    //dp[i][j]的含义：
    //coins[i...]自由选择，搞定 j元 所需最少硬币的数量是多少？
    //带有枚举行为的dp 待优化
    public static int coinChange1(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount < 0) {
            return -1;
        }
        int N = coins.length;
        int[][] dp = new int[N + 1][amount + 1];
        dp[N][0] = 0;
        for (int j = 1; j <= amount; j++) {
            dp[N][j] = -1;
        }

        // 整体从下往上，每行从左到右 填
        for (int i = N - 1; i >= 0; i--) {
            for (int rest = 0; rest <= amount; rest++) {
                int ways = Integer.MAX_VALUE;
                for (int k = 0; k * coins[i] <= rest; k++) {
                    //amount - k * coins[index]不可能小于0
                    int p1 = dp[i + 1][rest - k * coins[i]];
                    if (p1 != -1) {
                        ways = Math.min(ways, p1 + k);
                    }
                }
                dp[i][rest] = ways == Integer.MAX_VALUE ? -1 : ways;
            }
        }
        return dp[0][amount];
    }

    //方法3
    //dp[i][j]的含义：
    //coins[i...]自由选择，搞定 j元 所需最少硬币的数量是多少？
    //优化掉枚举行为后的dp
    public static int coinChange2(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount < 0) {
            return -1;
        }
        int N = coins.length;
        int[][] dp = new int[N + 1][amount + 1];
        dp[N][0] = 0;
        for (int j = 1; j <= amount; j++) {
            dp[N][j] = -1;
        }

        //从左到右 + 从下往上 填
        for (int i = N - 1; i >= 0; i--) {
            for (int rest = 0; rest <= amount; rest++) {
                //[1,2,3,5,8]
                //dp[3][100] = Math.min(dp[4][100] + 0,  //0张5元
                //             dp[4][95] + 1,   //1张5元
                //             dp[4][90] + 2,   //2张5元
                //             dp[4][85] + 3,...//3张5元
                //             )
                //            = Math.min(dp[4][100], dp[3][95] + 1)

                //dp[3][95] =  Math.min(dp[4][95] + 0,
                //             dp[4][90] + 1,   //1张5元
                //             dp[4][85] + 2,   //2张5元
                //             dp[4][80] + 3,...   //3张5元
                //             )
                dp[i][rest] = -1;
                if (dp[i + 1][rest] != -1) {
                    dp[i][rest] = dp[i + 1][rest]; //下面的格子
                }
                if (rest - coins[i] >= 0) {
                    int p1 = dp[i][rest - coins[i]]; //dp[3][95]
                    if (p1 != -1) {
                        if (dp[i][rest] == -1) {
                            dp[i][rest] = p1 + 1;
                        } else {
                            dp[i][rest] = Math.min(dp[i][rest], p1 + 1);
                        }
                    }
                }
            }
        }
        return dp[0][amount];
    }

    //方法4
    //dp[i][j]含义：
    //arr[0..i]自由选择，凑出j元，所需最少硬币数
    public static int coinChange4(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        //dp[i][j]含义：
        //arr[0..i]自由选择，凑出j元，所需最少硬币数
        int[][] dp = new int[arr.length][aim + 1];

        //第一列:arr[0,i]自由选择，凑出0元所需0张
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 0;
        }
        //第一行:arr[0,0]自由选择，凑出j元  看是否能整除
        for (int i = 1; i <= aim; i++) {
            dp[0][i] = (i % arr[0] == 0) ? i / arr[0] : -1;
        }
        //普遍位置
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                //涉及枚举行为优化
                dp[i][j] = Integer.MAX_VALUE;
                if (dp[i - 1][j] != -1) {
                    dp[i][j] = dp[i - 1][j];
                }
                if (j - arr[i] >= 0 && dp[i][j - arr[i]] != -1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - arr[i]] + 1);
                }
                if (dp[i][j] == Integer.MAX_VALUE) {
                    dp[i][j] = -1;
                }
            }
        }
        return dp[arr.length - 1][aim];
    }

}
