package com.joey.leetcode;

import java.util.Stack;

/**
 * 538. 把二叉搜索树转换为累加树
 * 给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），
 * 使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
 * <p>
 * 提醒一下，二叉搜索树满足下列约束条件：
 * <p>
 * 节点的左子树仅包含键 小于 节点键的节点。
 * 节点的右子树仅包含键 大于 节点键的节点。
 * 左右子树也必须是二叉搜索树。
 */
//本题和 1038: https://leetcode-cn.com/problems/binary-search-tree-to-greater-sum-tree/ 相同
public class Problem_0538_ConvertBSTToGreaterTree {

    //思路
    //利用中序遍历+栈，实现按中序遍历的逆序 设置每个节点的值
    public TreeNode convertBST(TreeNode root) {
        if (root == null) return null;
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> collect = new Stack<>(); //中序遍历的结果先装到栈中
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode node = stack.pop();
                collect.push(node);
                cur = node.right;
            }
        }
        int sum = 0;
        //依次弹栈(从大到小的顺序)，设置每个节点值
        while (!collect.isEmpty()) {
            cur = collect.pop();
            sum += cur.val;
            cur.val = sum;
        }
        return root;
    }

}
