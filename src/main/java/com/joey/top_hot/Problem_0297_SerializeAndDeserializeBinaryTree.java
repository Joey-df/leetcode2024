package com.joey.top_hot;

import java.util.LinkedList;
import java.util.Queue;

// 297. 二叉树的序列化与反序列化
// 层序遍历解法
public class Problem_0297_SerializeAndDeserializeBinaryTree {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return null;
        Queue<String> queue = new LinkedList<>();
        level(root, queue);
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            sb.append(cur == null ? "null" : cur);
            sb.append(",");
        }
        String ans = sb.toString();
        return ans.substring(0, ans.length() - 1);
    }

    private void level(TreeNode root, Queue<String> res) {
        if (root == null) {
            res.offer(null);
        } else {
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            res.offer(String.valueOf(root.val));
            while (!q.isEmpty()) {
                TreeNode cur = q.poll();
                if (cur.left != null) {
                    q.offer(cur.left);
                    res.offer(String.valueOf(cur.left.val));
                } else {
                    res.offer(null);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                    res.offer(String.valueOf(cur.right.val));
                } else {
                    res.offer(null);
                }
            }
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        String[] arr = data.split(",");
        Queue<String> queue = new LinkedList<>();
        for (String str : arr) {
            queue.offer(str);
        }
        return buildFromLevel(queue);
    }

    private TreeNode buildFromLevel(Queue<String> cand) {
        if (cand == null || cand.isEmpty()) {
            return null;
        }
        String cur = cand.poll();
        TreeNode head = generateNode(cur);
        if (head == null) {
            return head; // 是为兼容q中只有一个null的case
        }
        Queue<TreeNode> help = new LinkedList<>();
        help.offer(head);
        while (!help.isEmpty()) {
            TreeNode node = help.poll();
            node.left = generateNode(cand.poll());
            node.right = generateNode(cand.poll());
            if (node.left != null) {
                help.offer(node.left);
            }
            if (node.right != null) {
                help.offer(node.right);
            }
        }
        return head;
    }

    private TreeNode generateNode(String val) {
        if ("null".equals(val)) {
            return null;
        } else {
            return new TreeNode(Integer.parseInt(val));
        }
    }
}
