package com.joey.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 102. 二叉树的层序遍历
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 * <p>
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层序遍历结果：
 * <p>
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 */
public class Problem_0102_BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        LinkedList<TreeNode> q = new LinkedList<>();
        q.addLast(root);
        int size;
        while (!q.isEmpty()) {
            size = q.size();
            List<Integer> cur = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.pollFirst();
                cur.add(node.val);
                if (node.left != null) q.addLast(node.left);
                if (node.right != null) q.addLast(node.right);
            }
            ans.add(cur);
        }
        return ans;
    }
}
