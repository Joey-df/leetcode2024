package com.joey.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author pei.liu
 */

//https://leetcode.cn/problems/binary-tree-preorder-traversal/
//144. 二叉树的前序遍历
public class Problem144_PreorderTraversal {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        stack.push(cur);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            ans.add(node.val);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        return ans;
    }

}
