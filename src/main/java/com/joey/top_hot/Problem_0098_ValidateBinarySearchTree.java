package com.joey.top_hot;

import java.util.Stack;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * 假设一个二叉搜索树具有如下特征：
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 */
public class Problem_0098_ValidateBinarySearchTree {

    //根据中序遍历判断
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode pre = null;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode node = stack.pop();
                if (pre != null && pre.val >= node.val) return false;
                pre = node;
                cur = node.right;
            }
        }
        return true;
    }

    private class Info {
        int max; //整棵树上的最大值
        int min; //整棵树上的最小值
        boolean isallbst; //是否整体是bst

        public Info(int max, int min, boolean isallbst) {
            this.max = max;
            this.min = min;
            this.isallbst = isallbst;
        }
    }

    public boolean isValidBST2(TreeNode root) {
        if (root == null) return true;
        return fun(root).isallbst;
    }

    private Info fun(TreeNode x) {
        if (x == null) return null;
        Info linfo = fun(x.left);
        Info rinfo = fun(x.right);
        int min = x.val, max = x.val;
        if (linfo != null) {
            min = Math.min(min, linfo.min);
            max = Math.max(max, linfo.max);
        }
        if (rinfo != null) {
            min = Math.min(min, rinfo.min);
            max = Math.max(max, rinfo.max);
        }
        boolean isallbst = (linfo == null ? true : linfo.isallbst && linfo.max < x.val) &&
                (rinfo == null ? true : rinfo.isallbst && rinfo.min > x.val);

        return new Info(max, min, isallbst);
    }
}
