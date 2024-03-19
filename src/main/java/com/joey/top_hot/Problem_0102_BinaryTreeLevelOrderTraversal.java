package com.joey.top_hot;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 102. 二叉树的层序遍历
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层序遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 */
public class Problem_0102_BinaryTreeLevelOrderTraversal {

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root==null) return ans;
        LinkedList<TreeNode> q = new LinkedList<>();
        q.offerFirst(root);
        while(!q.isEmpty()) {
            int size = q.size();
            ArrayList<Integer> cur = new ArrayList<>();
            while (size-- > 0) {
                TreeNode node = q.pollLast();
                cur.add(node.val);
                if (node.left!=null) {
                    q.offerFirst(node.left);
                }
                if (node.right!=null) {
                    q.offerFirst(node.right);
                }
            }
            ans.add(cur);
        }
        return ans;
    }
}
