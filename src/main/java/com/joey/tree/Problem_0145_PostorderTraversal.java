package com.joey.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author pei.liu
 */

// 145.用两个栈完成后序遍历
// 测试链接 : https://leetcode.cn/problems/binary-tree-postorder-traversal/
public class Problem_0145_PostorderTraversal {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> clt = new Stack<>();
        TreeNode cur = root;
        stack.push(cur);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            clt.push(node);
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
        }
        while (!clt.isEmpty()) {
            ans.add(clt.pop().val);
        }
        return ans;
    }

}
