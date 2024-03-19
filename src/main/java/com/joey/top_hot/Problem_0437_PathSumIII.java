package com.joey.top_hot;

import tree.TreeNode;

import java.util.HashMap;

/**
 * 437. 路径总和 III
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 *
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 */
public class Problem_0437_PathSumIII {

    //Typical recursive DFS.
    //Space: O(n) due to recursion.
    //Time: O(n^2) in worst case (no branching); O(nlogn) in best case (balanced tree).
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) return 0;
        return allTreePathSum(root, targetSum);
    }

    //递归含义：
    //以x为头的二叉树，整棵树，得到累加和为sum的路径有几条，返回
    public int allTreePathSum(TreeNode root, int targetSum) {
        if (root == null) return 0;
        return mustFromX(root, targetSum) +
                allTreePathSum(root.left, targetSum) +
                allTreePathSum(root.right, targetSum);
    }

    //递归含义：
    //必须从x节点出发，往下扎，得到累加和为sum的路径有几条，返回
    public int mustFromX(TreeNode x, int sum) {
        if (x == null) return 0;
        return (x.val == sum ? 1 : 0)
                + mustFromX(x.left, sum - x.val)
                + mustFromX(x.right, sum - x.val);
    }

    //==============================================================//

    //算法原型
    //数组累加和问题三连第二连
    public static int pathSum2(TreeNode root, int sum) {
        HashMap<Integer, Integer> preSumMap = new HashMap<>(); //<前缀和sum，出现的次数>
        preSumMap.put(0, 1);
        return process(root, sum, 0, preSumMap);
    }

    // 以x为头整棵树，有多少方法数 返回
    // preAll:从根节点到x之前路径的累加和
    // 返回方法数
    // 本质还是dfs
    public static int process(TreeNode x, int sum, int preAll, HashMap<Integer, Integer> preSumMap) {
        if (x == null) {
            return 0;
        }
        int all = preAll + x.val;
        int ans = 0;
        if (preSumMap.containsKey(all - sum)) {
            ans = preSumMap.get(all - sum);
        }
        if (!preSumMap.containsKey(all)) {
            preSumMap.put(all, 1);
        } else {
            preSumMap.put(all, preSumMap.get(all) + 1);
        }
        ans += process(x.left, sum, all, preSumMap);
        ans += process(x.right, sum, all, preSumMap);
        if (preSumMap.get(all) == 1) {
            preSumMap.remove(all);
        } else {
            preSumMap.put(all, preSumMap.get(all) - 1);
        }
        return ans;
    }

}
