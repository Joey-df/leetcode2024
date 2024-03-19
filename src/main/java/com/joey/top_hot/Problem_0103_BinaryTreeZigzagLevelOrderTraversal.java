package com.joey.top_hot;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 103. 二叉树的锯齿形层序遍历
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。
 * （即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回锯齿形层序遍历如下：
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 */
public class Problem_0103_BinaryTreeZigzagLevelOrderTraversal {

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root==null) return ans;
        LinkedList<TreeNode> q = new LinkedList<>();
        q.addLast(root);
        boolean flag = true;
        while(!q.isEmpty()) {
            int size = q.size();
            ArrayList<Integer> sub = new ArrayList<>();
            while(size-- > 0 ) {
                if (flag) {
                    TreeNode node = q.pollFirst();
                    sub.add(node.val);
                    if (node.left != null) q.addLast(node.left);
                    if (node.right != null) q.addLast(node.right);
                } else {
                    TreeNode node = q.pollLast();
                    sub.add(node.val);
                    if (node.right != null) q.addFirst(node.right);
                    if (node.left != null) q.addFirst(node.left);
                }
            }
            flag = !flag;
            ans.add(sub);
        }
        return ans;
    }
}
