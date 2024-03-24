package com.joey.top_hot;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//94. 二叉树的中序遍历
public class Problem_0094_BinaryTreeInorderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode node = stack.pop();
                ans.add(node.val);
                cur = node.right;
            }
        }
        return ans;
    }
}
