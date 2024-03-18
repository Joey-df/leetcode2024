package com.joey.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author pei.liu
 */

// 94. 用一个栈完成中序遍历
// 测试链接 : https://leetcode.cn/problems/binary-tree-inorder-traversal/
public class Problem_0094_InorderTraversal {

    // 0.用栈
    // 1.先压入整个树 的左边界，直至指针到null
    // 2.弹栈，没弹出一个结点，压入其右树的左边届
    // while条件：栈不空或者当前结点不为空
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();
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
