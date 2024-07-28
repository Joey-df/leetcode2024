package com.joey.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Problem_0119_PascalTriangleII {

	//整个过程就是：
	//[1]变成
	//[1,1]变成
	//[1,2,1]变成
	//[1,3,3,1]
	public List<Integer> getRow(int rowIndex) {
		List<Integer> ans = new ArrayList<>();
		for (int i = 0; i <= rowIndex; i++) {
			// i>=2时才会执行for循环
			for (int j = i - 1; j > 0; j--) { //必须从后往前填，dp空间压缩的思想
				ans.set(j, ans.get(j - 1) + ans.get(j)); //左上+上
			}
			ans.add(1);
		}
		return ans;
	}

}