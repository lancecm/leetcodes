package LeetCode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author Srunkyo
 * @Date: 2018/8/13
 * @Description:
 * Easy
 *
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 *
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * But the following [1,2,2,null,3,null,3] is not:
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 * Note:
 * Bonus points if you could solve it both recursively and iteratively.
 *
 * 思路：
 * 如果root为空，则对称
 * 否则检查左右子树是否对称：
 * 对称要领： 两个子树为null，或者都不为null且val相等，需要检查他们的子节点，左子树左节点=右子树右节点；左子树右节点=右子树左节点，递归检查下去。
 *
 * 非递归的方法运用到了队列结构：
 * 人为控制入队检查的顺序，把需要检查一致的节点们放在一起
 */
public class Problem101_SymmetricTree {
    // 递归
    public boolean isSymmetric(TreeNode root) {
        return root == null || helper(root.left, root.right);
    }
    private boolean helper(TreeNode left, TreeNode right) {
        if (left == null || right == null) {
            return left == right;
        }
        if (left.val != right.val) return false;
        return helper(left.left, right.right) && helper(left.right, right.left);
    }


    // 非递归
    public boolean isSymmetric_Iter(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        if (root == null) return true;
        q.add(root.left);
        q.add(root.right);
        while (q.size() > 1) {
            TreeNode left = q.poll();
            TreeNode right = q.poll();
            if (left == null && right == null) continue;
            if (left == null || right == null || left.val != right.val) return false;
            q.add(left.left);
            q.add(right.right);
            q.add(left.right);
            q.add(right.left);
        }
        return true;
    }
}
