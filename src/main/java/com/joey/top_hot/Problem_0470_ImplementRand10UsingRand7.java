package com.joey.top_hot;

public class Problem_0470_ImplementRand10UsingRand7 {

    // 此函数为系统提供，只能用，不能修改
    // 等概率返回1~7
    public static int rand7() {
        return (int) (Math.random() * 7) + 1;
    }


    //等概率返回1～10
    public static int fun() {
        return f3() + 1;
    }

    //使用rand7构造01发生器
    public static int f1() {
        //1 2 3 4 5 6 7
        int ans = 0;
        do {
            ans = rand7();
        } while (ans == 4);
        return ans < 4 ? 0 : 1;
    }

    //1~10，10有4为，二进制为 1010
    //so，使用f1() 构造0000～1111 ，即0～15等概率返回函数f2
    public static int f2() {
        return f1() << 3 | f1() << 2 | f1() << 1 | f1();
    }

    //利用f2()构造0～9等概率返回函数
    public static int f3() {
        int ans;
        do {
            ans = f2();
        } while (ans > 9);
        return ans;
    }
}