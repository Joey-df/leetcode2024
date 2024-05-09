package com.joey.top_hot;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//236. 二叉树的最近公共祖先
public class Problem_0236_LowestCommonAncestorOfBinaryTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        HashMap<TreeNode, TreeNode> father = new HashMap<>();
        father.put(root, null); //root的父亲是null
        fillFatherMap(root, father); // important
        Set<TreeNode> set = new HashSet<>();
        set.add(p);
        TreeNode cur = p;
        while (father.get(cur) != null) {
            TreeNode f = father.get(cur);
            set.add(f);
            cur = f;
        }
        cur = q;
        while (!set.contains(cur)) {
            cur = father.get(cur);
        }
        return cur;
    }

    private void fillFatherMap(TreeNode x, HashMap<TreeNode, TreeNode> father) {
        if (x == null) return; // important
        if (x.left != null) {
            father.put(x.left, x);
            fillFatherMap(x.left, father);
        }
        if (x.right != null) {
            father.put(x.right, x);
            fillFatherMap(x.right, father);
        }
    }
}
