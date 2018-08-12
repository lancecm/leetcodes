package LeetCode;

/**
 * @author Srunkyo
 * @Date: 2018/8/12
 * @Description:
 * Medium
 *
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 * For example, given
 *
 * postorder = [9,15,7,20,3]
 * inorder = [9,3,15,20,7]
 * Return the following binary tree:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 和105一样：
 * 不同在于
 * 1. 从postorder中选择最后一个作为根
 * 2. 判断在inorder中的位置；构造根
 * 3. 先构造右子树（根节点为postorder中下一个节点），再构造左子树（左子树+右子树+根，所以是根的位置减去右子树的长度，右子树的长度为iEnd - inindex + 1）
 */
public class Problem106_ConstructBinaryTreeFromInorderAndPostOrderTraversal {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length) return null;
        return helper(postorder, inorder, postorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode helper(int[] post, int[] in, int idx, int iStart, int iEnd) {
        if (iStart > iEnd || idx < 0) return null;
        TreeNode root = new TreeNode(post[idx]);
        int inIndex = iStart;
        while (inIndex < iEnd) {
            if (in[inIndex] == root.val) break;
            inIndex++;
        }
        root.right = helper(post, in, idx - 1, inIndex + 1, iEnd);
        //(iEnd - inIndex + 1) 右子树的长度
        root.left = helper(post, in, idx - (iEnd - inIndex + 1), iStart, inIndex - 1);
        return root;
    }
}
