package com.joey.leetcode;

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

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root==null) return ans;
        LinkedList<TreeNode > q = new LinkedList<>();
        q.addLast(root); // addLast或者addFirst，这里无所谓
        boolean ltor = true;
        int size;
        while (!q.isEmpty()) {
            size = q.size();
            List<Integer > cur  = new ArrayList<>();
            for (int i=0;i<size;i++) {
                if (ltor) {
                    TreeNode node = q.pollFirst();
                    cur.add(node.val);
                    if (node.left!=null) q.addLast(node.left);
                    if (node.right!=null) q.addLast(node.right);
                } else {
                    TreeNode node = q.pollLast();
                    cur.add(node.val);
                    if (node.right!=null) q.addFirst(node.right);
                    if (node.left!=null) q.addFirst(node.left);
                }
            }
            ltor = !ltor;
            ans.add(cur);
        }
        return ans;
    }
}
