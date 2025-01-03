package com.joey.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author pei.liu
 */
//面试题 16.19. 水域大小
//你有一个用于表示一片土地的整数矩阵land，该矩阵中每个点的值代表对应地点的海拔高度。
//若值为0则表示水域。由垂直、水平或对角连接的水域为池塘。
//池塘的大小是指相连接的水域的个数。
//编写一个方法来计算矩阵中所有池塘的大小，返回值需要从小到大排序。
//示例：
//输入：
//[
//  [0,2,1,0],
//  [0,1,0,1],
//  [1,1,0,1],
//  [0,1,0,1]
//]
//输出： [1,2,4]
//提示：
//0 < len(land) <= 1000
//0 < len(land[i]) <= 1000
public class Problem_PondSizesLcci {

    //连成一片的0的个数（带对角线）
    public int[] pondSizes(int[][] land) {
        int n = land.length;
        int m = land[0].length;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int curr = f(land, n, m, i, j);
                if (curr > 0) {
                    ans.add(curr);
                }
            }
        }
        return ans.stream()
                .sorted(Integer::compare)
                .mapToInt(Integer::valueOf).toArray();
    }


    public static int f(int[][] land, int n, int m, int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= m || land[x][y] != 0) {
            return 0;
        }
        //land[x][y]==0
        land[x][y] = -1;
        int ans = 1;
        ans += f(land, n, m, x + 1, y)
                + f(land, n, m, x - 1, y)
                + f(land, n, m, x, y + 1)
                + f(land, n, m, x, y - 1)
                + f(land, n, m, x + 1, y + 1)
                + f(land, n, m, x + 1, y - 1)
                + f(land, n, m, x - 1, y + 1)
                + f(land, n, m, x - 1, y - 1);
        return ans;
    }
}
