package com.joey.leetcode;

import java.util.Arrays;

// 每个元音包含偶数次的最长子字符串
// 给你一个字符串 s ，请你返回满足以下条件的最长子字符串的长度
// 每个元音字母，即 'a'，'e'，'i'，'o'，'u'
// 在子字符串中都恰好出现了偶数次。
// 测试链接 : https://leetcode.cn/problems/find-the-longest-substring-containing-vowels-in-even-counts/
public class Problem_1371_EvenCountsLongestSubarray {

	public static int findTheLongestSubstring(String s) {
		//TODO
		return 0;
	}
	static class Info{
		int v;
		int i;
		public Info(int v, int i) {
			this.v=v;
			this.i=i;
		}
	}
	public static int[] twoSum(int[] nums, int target) {
		int n=nums.length;
		Info[] infos=new Info[n];
		for(int i=0;i<n;i++) {
			infos[i]=new Info(nums[i],i);
		}
		Arrays.sort(infos, (a,b)->a.v-b.v);

		for(int i=1;i<n;i++) {
			int p = find(infos,0,i-1,target-infos[i].v);
			if(p!=-1) {
				return new int[]{infos[i].i, p};
			}
		}
		return new int[]{-1,-1};
	}

	public static int find(Info[] infos, int l, int r, int target) {
		while(l <= r) {
			int m=l+(r-l)/2;
			if(infos[m].v == target) {
				return infos[m].i;
			} else if(infos[m].v < target) {
				l=m+1;
			} else {
				r=m-1;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] nums = {0,4,3,0};
		int t = 0;
		System.out.println(twoSum(nums, t));
	}

}
