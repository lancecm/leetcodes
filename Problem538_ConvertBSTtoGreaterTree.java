package LeetCode;

/**
 * @author Srunkyo
 * @Date: 2018/8/16
 * @Description:
 * Easy
 *
 * Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.
 *
 * Example:
 *
 * Input: The root of a Binary Search Tree like this:
 *               5
 *             /   \
 *            2     13
 *
 * Output: The root of a Greater Tree like this:
 *              18
 *             /   \
 *           20     13
 *
 * 思路：
 * 注意，采用的是反向的中序遍历，即先访问右节点，再访问根，再访问左节点
 * 这样，从逻辑上访问的是递减序列，因此可以保存较大值之和sum，并加到节点上
 */
public class Problem538_ConvertBSTtoGreaterTree {
    int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        dfs(root);
        return root;
    }

    public void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.right);
        root.val += sum;
        sum = root.val;
        dfs(root.left);
    }

}
