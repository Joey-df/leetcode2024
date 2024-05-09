package com.joey.top_hot;


/**
 * 543. 二叉树节点间的最长路径（二叉树的直径）
 * 给定一棵二叉树，你需要计算它的直径长度。
 * 一棵二叉树的直径长度是任意两个结点路径长度中的最大值。
 * 这条路径可能穿过也可能不穿过根结点。
 *
 * 示例 :
 * 给定二叉树
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 */
//最大距离
//system_study.class12.Code06_MaxDistance
public class Problem_0543_DiameterOfBinaryTree {

    private static class Info {
        int maxDistance; //每棵子树的最大距离(节点个数)
        int height;

        public Info(int max, int h) {
            maxDistance = max;
            height = h;
        }
    }

    public static int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root).maxDistance - 1;
    }

    public static Info process(TreeNode x) {
        if (x == null) {
            return new Info(0, 0);
        }
        Info l = process(x.left);
        Info r = process(x.right);
        int height = Math.max(l.height, r.height) + 1;
        int maxDistance = Math.max(l.maxDistance, r.maxDistance); //和x无关
        maxDistance = Math.max(maxDistance, l.height + r.height + 1); //和x有关
        return new Info(maxDistance, height);
    }
}
