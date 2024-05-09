package com.joey.top_hot;

import java.util.HashMap;
import java.util.Map;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class Problem_0105_ConstructBinaryTreeFromPreorderAndInorderTraversal {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return fun(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
    }

    //使用pre[l1,r1],in[l2,r2]构建的二叉树头节点返回
    private TreeNode fun(int[] pre, int l1, int r1, int[] in, int l2, int r2, Map<Integer, Integer> map) {
        if (l1 > r1) return null;
        if (l1 == r1) return new TreeNode(pre[l1]);
        //l1 < r1
        // 3,9,20,15,7
        // 0 1  2  3 4
        // 9,3,15,20,7
        // 0 1 2  3  4
        TreeNode root = new TreeNode(pre[l1]);
        int index = map.get(pre[l1]);
        int leftLen = index - l2; // 左子树的数组长度
        root.left = fun(pre, l1 + 1, l1 + leftLen, in, l2, index - 1, map);
        root.right = fun(pre, l1 + leftLen + 1, r1, in, index + 1, r2, map);
        return root;
    }

}
