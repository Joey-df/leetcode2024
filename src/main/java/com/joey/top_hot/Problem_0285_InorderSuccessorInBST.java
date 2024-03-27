package com.joey.top_hot;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//求二叉搜索树的某个节点的中序后继节点
//Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
//The successor of a node p is the node with the smallest key greater than p.val.
public class Problem_0285_InorderSuccessorInBST {

    //中序遍历的序列中，p的下一个就是答案
    public static Integer inorderSuccessor(TreeNode root, TreeNode p) {
        List<Integer> inlist = in(root);
        if (inlist.size() == 0) {
            return null;
        }
        int N = inlist.size();
        int i = 0;
        for (; i < N; i++) {
            if (inlist.get(i) == p.val) {
                if (i == N - 1) {
                    return null;
                }
                break;
            }
        }
        return inlist.get(i + 1);
    }

    //给定根节点root，返回中序遍历的结果
    public static List<Integer> in(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
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

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        List<Integer> inlist = in(root);
        System.out.println(inlist);
        System.out.println(inorderSuccessor(root, root.right));
    }
}
