package com.joey.top_hot;

/**
 * @author pei.liu
 */
//12. 整数转罗马数字
public class Problem_0012_IntToRoman {

    public static String intToRoman(int num) {
        //1 <= num <= 3999
        //因为题目限制num最大不超过4000
        //所以初始化以下范围足够
        String[][] c = {
                {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"}, // 1～9
                {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"}, // 10～90
                {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"}, // 100～900
                {"", "M", "MM", "MMM"}}; // 1000～3000
        StringBuilder roman = new StringBuilder();
        roman.append(c[3][num / 1000 % 10])
                .append(c[2][num / 100 % 10])
                .append(c[1][num / 10 % 10])
                .append(c[0][num % 10]);
        return roman.toString();
    }
}
