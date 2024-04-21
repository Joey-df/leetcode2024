package com.joey.od.s200;

/**
 * @author pei.liu
 */

import java.util.Scanner;

public class 员工派遣 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long x = sc.nextInt();
        long y = sc.nextInt();
        long cntx = sc.nextInt();
        long cnty = sc.nextInt();

        // 使用此范围，实际通过率55%
        //  long min = cntx + cnty;
        //  long max = Long.MAX_VALUE;

        // 使用此范围，实际通过率可以100%
        long min = 1;
        long max = 1000000000L;

        while (min <= max) {
            long mid = min + (max - min) / 2;

            if (check(mid, x, y, cntx, cnty)) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        System.out.println(min);
    }

    // x,y,cntx,cnty为固定参数
    public static boolean check(long k, long x, long y, long cntx, long cnty) {
        long A = k / x; // 1~k范围内x倍数的数量
        long B = k / y; // 1~k范围内y倍数的数量
        long C = k / (x * y); // 1~k范围内x*y倍数的数量

        return Math.max(0, cntx - (B - C)) + Math.max(0, cnty - (A - C)) <= k - A - B + C;
    }
}