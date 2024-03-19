package com.joey.top_hot;

import tree.TreeNode;

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

    private static class Info {
        private int min;
        private int max;
        private boolean isAllBst;//是否整体是bst

        public Info(int min, int max, boolean isAllBst) {
            this.min = min;
            this.max = max;
            this.isAllBst = isAllBst;
        }
    }

    //以root为根的二叉树是否为BST
    public static Info fun(TreeNode root) {
        if (root == null) {
            return null;
        }
        Info l = fun(root.left);
        Info r = fun(root.right);
        int min = root.val;
        int max = root.val;
        if (l != null) { //只需要和左子树pk最小值即可
            min = Math.min(min, l.min);
        }
        if (r != null) { //只需要和右子树pk最大值即可
            max = Math.max(max, r.max);
        }
        boolean isAllBst = false;
        if ((l == null ? true : (l.isAllBst && l.max < root.val))
                && (r == null ? true : (r.isAllBst && r.min > root.val))) {
            isAllBst = true;
        }
        return new Info(min, max, isAllBst);
    }

    //方法1
    //二叉树递归套路（本质上是利用后续遍历）
    public static boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        return fun(root).isAllBst;
    }

    //方法2
    //经典中序遍历解法
    //时间复杂度O(N)，空间复杂度O(H)
    public boolean isValidBST2(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        Integer pre = null;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode node = stack.pop();
                if (pre != null && pre >= node.val) {
                    return false;
                }
                pre = node.val;
                cur = node.right;
            }
        }
        return true;
    }

    //方法3：morris中序遍历
    //违反 非递减序 就返回false
    //时间复杂度O(N)，空间复杂度O(1)
    public static boolean isValidBST3(TreeNode root) {
        if (root == null) return true;
        TreeNode cur = root;
        TreeNode mostRight;
        Integer pre = null;
        boolean ans = true;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right; //找到左树最右节点
                }
                //while出来时：mostRight.right==null 或者 mostRight.right==cur
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else { //mostRight.right==cur
                    //此处是第二次到达节点
                    mostRight.right = null;
                }
            }
            if (pre != null && pre >= cur.val) {
                ans = false; //这里不直接返回，是为了防止原来的树被调乱
            }
            pre = cur.val;
            cur = cur.right;
        }
        return ans;
    }

}
