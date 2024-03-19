package com.joey.top_hot;

/**
 * Given a non negative integer number num.
 * For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.
 * 
 * Example 1:
 * 
 * Input: 2
 * Output: [0,1,1]
 * Example 2:
 * 
 * Input: 5
 * Output: [0,1,1,2,1,2]
 * Follow up:
 * 
 * It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
 * Space complexity should be O(n).
 * Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
 */
public class Problem_0338_CountingBits {

    //O(n*sizeof(integer))
    public static int[] process(int n) {
        int[] ans = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            ans[i] = help(i);
        }
        return ans;
    }

    public static int help(int n) {
        int ans = 0;
        while (n != 0) {
            int rightOne = n & (~n + 1);
            ans++;
            n ^= rightOne;
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 116;
        int[] ans = process(n);
        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i] + " ");
        }
    }
}
