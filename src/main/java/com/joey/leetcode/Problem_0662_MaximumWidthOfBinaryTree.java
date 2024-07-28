package com.joey.leetcode;


import java.util.LinkedList;

/**
 * Given a binary tree, write a function to get the maximum width of the given tree. The maximum width of a tree is the maximum width among all levels.
 *
 * The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.
 *
 * It is guaranteed that the answer will in the range of 32-bit signed integer.
 *
 * Example 1:
 *
 * Input:
 *
 *            1
 *          /   \
 *         3     2
 *        / \     \
 *       5   3     9
 *
 * Output: 4
 * Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
 * Example 2:
 *
 * Input:
 *
 *           1
 *          /
 *         3
 *        / \
 *       5   3
 *
 * Output: 2
 * Explanation: The maximum width existing in the third level with the length 2 (5,3).
 * Example 3:
 *
 * Input:
 *
 *           1
 *          / \
 *         3   2
 *        /
 *       5
 *
 * Output: 2
 * Explanation: The maximum width existing in the second level with the length 2 (3,2).
 * Example 4:
 *
 * Input:
 *
 *           1
 *          / \
 *         3   2
 *        /     \
 *       5       9
 *      /         \
 *     6           7
 * Output: 8
 * Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).
 *
 *
 * Constraints:
 *
 * The given binary tree will have between 1 and 3000 nodes.
 */


// change the val of node to be the index to save space. The value is useless. All we need is just the index.
public class Problem_0662_MaximumWidthOfBinaryTree {

    //bfs宽度优先遍历
    //补充知识：
    //假设父节点在第 n 层的索引为 i，
    //（1）当索引从0开始时，该父节点在第 n + 1 层的左孩子节点的索引为 2* i + 1，右孩子节点的索引为 2*i + 2；
    //（2）当索引从1开始时，该父节点在第 n + 1 层的左孩子节点的索引为 2 *i，右孩子节点的索引为 2 * i + 1。
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<TreeNode> dq = new LinkedList<>();
        dq.offer(root);
        root.val = 1; //人为规定根节点index=1，左右子节点的索引分别为index*2，index*2+1
        int ans = 1;
        while (!dq.isEmpty()) {
            int size = dq.size();
            ans = Math.max(ans, dq.peekLast().val - dq.peekFirst().val + 1);
            for (int i = 0; i < size; i++) {
                root = dq.poll();
                if (root.left != null) {
                    root.left.val = root.val * 2;
                    dq.offer(root.left);
                }
                if (root.right != null) {
                    root.right.val = root.val * 2 + 1;
                    dq.offer(root.right);
                }
            }
        }
        return ans;
    }

}
