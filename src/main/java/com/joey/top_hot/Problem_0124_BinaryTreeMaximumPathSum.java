package com.joey.top_hot;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * 124. 二叉树中的最大路径和
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。
 * 同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 * <p>
 * 路径和 是路径中各节点值的总和。
 * <p>
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 */
public class Problem_0124_BinaryTreeMaximumPathSum {


    private static class Info {
        int allMaxPathSum; //整棵树上的最大路径和
        int maxPathSumFromHead; //必须从根节点出发往下扎的最大路径和

        public Info(int a, int b) {
            allMaxPathSum = a;
            maxPathSumFromHead = b;
        }
    }

    public static int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        return func(root).allMaxPathSum;
    }

    //递归含义：
    //返回以x为头的二叉树的Info
    public static Info func(TreeNode x) {
        if (x == null) {
            return null;
        }
        Info l = func(x.left);
        Info r = func(x.right);
        int p1 = Integer.MIN_VALUE;
        if (l != null)
            p1 = l.allMaxPathSum;
        int p2 = Integer.MIN_VALUE;
        if (r != null)
            p2 = r.allMaxPathSum;
        int p3 = x.val;
        int p4 = Integer.MIN_VALUE;
        if (l != null)
            p4 = x.val + l.maxPathSumFromHead;
        int p5 = Integer.MIN_VALUE;
        if (r != null)
            p5 = x.val + r.maxPathSumFromHead;
        int p6 = Integer.MIN_VALUE;
        if (l != null && r != null)
            p6 = x.val + l.maxPathSumFromHead + r.maxPathSumFromHead;
        int allMaxPathSum = Math.max(p1, Math.max(p2, Math.max(p3, Math.max(p4, Math.max(p5, p6)))));
        int maxPathSumFromHead = Math.max(p3, Math.max(p4, p5));
        return new Info(allMaxPathSum, maxPathSumFromHead);
    }

    // follow up : 如果要求返回整个路径怎么做？
    // 如果要返回路径的做法
    public static List<TreeNode> getMaxSumPath(TreeNode head) {
        List<TreeNode> ans = new ArrayList<>();
        if (head != null) {
            Data data = f(head);
            HashMap<TreeNode, TreeNode> fmap = new HashMap<>();
            fmap.put(head, head);
            fatherMap(head, fmap);
            fillPath(fmap, data.from, data.to, ans);
        }
        return ans;
    }

    public static class Data {
        public int maxAllSum;
        public TreeNode from;
        public TreeNode to;
        public int maxHeadSum;
        public TreeNode end;

        public Data(int a, TreeNode b, TreeNode c, int d, TreeNode e) {
            maxAllSum = a;
            from = b;
            to = c;
            maxHeadSum = d;
            end = e;
        }
    }

    public static Data f(TreeNode x) {
        if (x == null) {
            return null;
        }
        Data l = f(x.left);
        Data r = f(x.right);
        int maxHeadSum = x.val;
        TreeNode end = x;
        if (l != null && l.maxHeadSum > 0 && (r == null || l.maxHeadSum > r.maxHeadSum)) {
            maxHeadSum += l.maxHeadSum;
            end = l.end;
        }
        if (r != null && r.maxHeadSum > 0 && (l == null || r.maxHeadSum > l.maxHeadSum)) {
            maxHeadSum += r.maxHeadSum;
            end = r.end;
        }
        int maxAllSum = Integer.MIN_VALUE;
        TreeNode from = null;
        TreeNode to = null;
        if (l != null) {
            maxAllSum = l.maxAllSum;
            from = l.from;
            to = l.to;
        }
        if (r != null && r.maxAllSum > maxAllSum) {
            maxAllSum = r.maxAllSum;
            from = r.from;
            to = r.to;
        }
        int p3 = x.val + (l != null && l.maxHeadSum > 0 ? l.maxHeadSum : 0)
                + (r != null && r.maxHeadSum > 0 ? r.maxHeadSum : 0);
        if (p3 > maxAllSum) {
            maxAllSum = p3;
            from = (l != null && l.maxHeadSum > 0) ? l.end : x;
            to = (r != null && r.maxHeadSum > 0) ? r.end : x;
        }
        return new Data(maxAllSum, from, to, maxHeadSum, end);
    }

    public static void fatherMap(TreeNode h, HashMap<TreeNode, TreeNode> map) {
        if (h.left == null && h.right == null) {
            return;
        }
        if (h.left != null) {
            map.put(h.left, h);
            fatherMap(h.left, map);
        }
        if (h.right != null) {
            map.put(h.right, h);
            fatherMap(h.right, map);
        }
    }

    public static void fillPath(HashMap<TreeNode, TreeNode> fmap, TreeNode a, TreeNode b, List<TreeNode> ans) {
        if (a == b) {
            ans.add(a);
        } else {
            HashSet<TreeNode> ap = new HashSet<>();
            TreeNode cur = a;
            while (cur != fmap.get(cur)) {
                ap.add(cur);
                cur = fmap.get(cur);
            }
            ap.add(cur);
            cur = b;
            TreeNode lca = null;
            while (lca == null) {
                if (ap.contains(cur)) {
                    lca = cur;
                } else {
                    cur = fmap.get(cur);
                }
            }
            while (a != lca) {
                ans.add(a);
                a = fmap.get(a);
            }
            ans.add(lca);
            ArrayList<TreeNode> right = new ArrayList<>();
            while (b != lca) {
                right.add(b);
                b = fmap.get(b);
            }
            for (int i = right.size() - 1; i >= 0; i--) {
                ans.add(right.get(i));
            }
        }
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(4);
        head.left = new TreeNode(-7);
        head.right = new TreeNode(-5);
        head.left.left = new TreeNode(9);
        head.left.right = new TreeNode(9);
        head.right.left = new TreeNode(4);
        head.right.right = new TreeNode(3);

        List<TreeNode> maxPath = getMaxSumPath(head);

        for (TreeNode n : maxPath) {
            System.out.println(n.val);
        }
    }
}
