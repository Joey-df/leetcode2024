package com.joey.leetcode;


/**
 * 337. 打家劫舍 III
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。
 * 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。
 * 一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 * <p>
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 * 示例 1:
 * 输入: [3,2,3,null,3,null,1]
 * <p>
 * 3
 * / \
 * 2   3
 * \   \
 * 3   1
 * <p>
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 * 示例 2:
 * <p>
 * 输入: [3,4,5,1,3,null,1]
 * <p>
 * 3
 * / \
 * 4   5
 * / \   \
 * 1   3   1
 * <p>
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 */
//基础班讲过的最大快乐值问题
public class Problem_0337_HouseRobberIII {

    private static class Info {
        int yes; //整颗子树，在要头结点的最大收益
        int no; //整颗子树，在不要头结点的最大收益

        public Info(int y, int n) {
            yes = y;
            no = n;
        }
    }

    public static Info process(TreeNode x) {
        if (x == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int yes = x.val + leftInfo.no + rightInfo.no;
        int no = Math.max(leftInfo.yes, leftInfo.no) + Math.max(rightInfo.yes, rightInfo.no);
        return new Info(yes, no);
    }

    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Info info = process(root);
        return Math.max(info.yes, info.no);
    }
}
