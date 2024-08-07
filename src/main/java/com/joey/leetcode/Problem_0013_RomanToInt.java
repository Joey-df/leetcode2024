package com.joey.leetcode;

/**
 * @author pei.liu
 */
public class Problem_0013_RomanToInt {

    public static int romanToInt(String s) {
        // C     M     X   C     I   X
        // 100  1000  10   100   1   10
        int nums[] = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case 'M':
                    nums[i] = 1000;
                    break;
                case 'D':
                    nums[i] = 500;
                    break;
                case 'C':
                    nums[i] = 100;
                    break;
                case 'L':
                    nums[i] = 50;
                    break;
                case 'X':
                    nums[i] = 10;
                    break;
                case 'V':
                    nums[i] = 5;
                    break;
                case 'I':
                    nums[i] = 1;
                    break;
            }
        }
        int sum = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                sum -= nums[i]; // 比如IV表示4
            } else {
                sum += nums[i]; // 比如VI表示6
            }
        }
        return sum + nums[nums.length - 1];
    }
}
