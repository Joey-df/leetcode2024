package com.joey.od.s200;

/**
 * @author pei.liu
 */

import java.util.Arrays;
import java.util.Scanner;

public class 部门人力分配 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = Integer.parseInt(scanner.nextLine());
        int[] requirements = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//        int m=3;
//        int[] requirements=new int[]{3,5,3,4};
        Arrays.sort(requirements);
        int n = requirements.length;
        int l = requirements[n - 1];
        int r = (int) 1e9;

        while (l < r) {
            int mid = (l + r) / 2;
            if (check(requirements, m, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        System.out.println(l);
    }

    private static boolean check(int[] w, int limit, int x) {
        int cnt = 0;
        int l = 0;
        int r = w.length - 1;

        while (l <= r) {
            if (w[l] + w[r] <= x) {
                l++;
                r--;
            } else {
                r--;
            }
            cnt++;
        }

        return cnt <= limit;
    }
}

