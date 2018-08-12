package LeetCode;

/**
 * @author Srunkyo
 * @Date: 2018/8/12
 * @Description:
 * Medium
 *
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 * For example, given
 *
 * preorder = [3,9,20,15,7]
 * inorder = [9,3,15,20,7]
 * Return the following binary tree:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 思路：
 *   1. 从前序树中第一个节点为根
 *   2. 从中序树中确认根的位置
 *   3. 构造根
 *   4. 构造左右节点并返回根
 *   注意用位置标记构建的范围
 *
 *   TODO： Iterative？
 */
public class Problem105_ConstructBinaryTreeFromPreoderAndInorderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, inorder, 0, 0, inorder.length);
    }

    private TreeNode build(int[] preorder, int[] inorder, int pStart, int iStart, int iEnd) {
        if (pStart > preorder.length - 1 || iStart > iEnd) return null;
        TreeNode root = new TreeNode(preorder[pStart]);
        int index = 0;
        for (int i = iStart; i <= iEnd; i++) {
            if (inorder[i] == root.val) {
                index = i;
                break;
            }
        }
        root.left = build(preorder, inorder, pStart + 1, iStart, index - 1);
        root.right = build(preorder, inorder, pStart + index - iStart + 1, index + 1, iEnd);
        // 右边子树前序树的起点：
        // index - iStart: 左子树的大小 可知前序树序列化构造为：根 + 左子树 + 右子树，因此左子树大小为 index - iStart + 1，加上起点位置就是右子树的起点位置
        // so basically inIndex - instart is size of the roots left subtree,
        // therefore to get the start of right subtree you gotta get to the
        // start of the first value of right subtree within preorder.
        // relative start of the root + left + right tree (preStart) + left
        // sub tree size (inIndex - inStart) + 1 (the root).
        return root;
    }
}
