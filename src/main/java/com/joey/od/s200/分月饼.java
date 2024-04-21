package com.joey.od.s200;

/**
 * 题目描述
 * 中秋节，公司分月饼，m个员工，买了n个月饼，m<=n，每个员工至少分1个月饼，
 * 但可以分多个，单人分到最多月饼的个数是Max1，单人分到第二多月饼个数是Max2，Max1-Max2<=3，
 * 单人分到第n-1多月饼个数是Max(n-1)，单人分到第n多月饼个数是Max(n)，Max(n-1)-Max(n)<=3问有多少种分月饼的方法?
 *
 * 输入描述
 * 每一行输入m n，表示m个员工，n个月饼，m<=n
 *
 * 输出描述
 * 输出有多少种月饼分法
 *
 * 示例1
 * 输入：
 * 2 4
 *
 * 输出：
 * 2
 *
 * 说明：
 * 分法有2种
 * 4=1+3
 * 4=2+2
 * 注意:1+3和3+1算一种分法
 * 示例2
 * 输入：
 * 3 5
 *
 * 输出：
 * 2
 *
 * 说明：
 * 5=1+1+3
 * 5=1+2+2
 * 示例3
 * 输入：
 * 3 12
 *
 * 输出：
 * 6
 *
 * 说明：
 * 满足要求的有6种分法:
 * 12=1+1+10(Max1=10,Max2=1，不满足要求)
 * 12=1+2+9(Max1=9,Max2=2,不满足要求)
 * 12=1+3+8(Max1=8,Max2=3 不满足要求)
 * 12=1+4+7(Max1=7,Max2=4,Max3=1, 满足要求)
 * 12=1+5+6(Max1=6,Max2=5,Max3=1，不满足要求)
 * 12=2+2+8(Max1=8,Max2=2,不满足要求)
 * 12=2+3+7(Max1=7,Max2=3,不满足要求)
 * 12=2+4+6(Max1=6,Max2=4,Max3=2，满足要求)
 * 12=2+5+5(Max1=5,Max2=2，满足要求)
 * 12=3+3+6(Max1=6,Max2=3,满足要求)
 * 12=3+4+5(Max1=5,Max2=4,Max3=3，满足要求)
 * 12=4+4+4(Max1=4,满足要求)
 *
 * 作者：code5bug
 * 链接：https://www.nowcoder.com/discuss/594576774558683136
 * 来源：牛客网
 */
import java.util.Scanner;

public class 分月饼 {
    static int ans = 0;  // 初始化结果
    static int n, m;  // 定义变量n和m

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();  // m个人
        n = sc.nextInt();  // n个月饼
        dfs(0, n, 1, n);  // 调用dfs函数
        System.out.println(ans);  // 输出结果
    }

    //index:当前分配到第i个人（0，index-1）的人已经分配好了，不用操心了
    //remain：分配到第i个人时剩余的月饼数量
    //min：第i个人应该获得的月饼数量最小值
    //max：第i个人应该获得的月饼数量最大值
    static void dfs(int index, int remain, int min, int max) {
        if (index == m) {  // 如果u等于m
            if (remain == 0) {  // 如果sum等于0
                ans++;  // 结果加一
            }
            return;
        }

        if (min > remain || remain < 0 || min > max) {
            return;
        }

        // 枚举第index的人获得的月饼数量
        for (int x = min; x <= max; x++) {
            dfs(index + 1, remain - x, x, Math.min(remain - x, x + 3));
        }
    }
}

