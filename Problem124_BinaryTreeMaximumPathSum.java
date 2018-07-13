package LeetCode;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/13
 * @Description:
 * 124
 * Hard
 *
 * Given a non-empty binary tree, find the maximum path sum.
 *
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.
 *
 * Example 1:
 *
 * Input: [1,2,3]
 *
 *        1
 *       / \
 *      2   3
 *
 * Output: 6
 * Example 2:
 *
 * Input: [-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * Output: 42
 *
 * 给定非空二叉树，求路径权值和的最大值（路径可以从任意节点，沿着树走到某个节点
 * 思路：
 * 将一整条路径从离根节点最近的节点分为两个部分：左子树延伸与右子树延伸。
 * 对于每个节点，递归计算左右子树最大路径，同该节点拼接，即可得到最大路径。
 */
public class Problem124_BinaryTreeMaximumPathSum {
    private int result;
    public int maxPathSum(TreeNode root) {
        result = Integer.MIN_VALUE;
        dfs(root);
        return result;
    }

    public int dfs(TreeNode root) {
        if (root == null) return 0;
        int left = Math.max(0, dfs(root.left));
        int right = Math.max(0, dfs(root.right));
        result = Math.max(result, left + right + root.val); // update maximum path
        return Math.max(left, right) + root.val; // choose bigger path for its parent node
    }
}
