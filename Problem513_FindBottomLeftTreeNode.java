package LeetCode;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Srunkyo
 * @Date: 2018/8/1
 * @Description:
 * Medium
 *
 * Given a binary tree, find the leftmost value in the last row of the tree.
 *
 * Example 1:
 * Input:
 *
 *     2
 *    / \
 *   1   3
 *
 * Output:
 * 1
 * Example 2:
 * Input:
 *
 *         1
 *        / \
 *       2   3
 *      /   / \
 *     4   5   6
 *        /
 *       7
 *
 * Output:
 * 7
 * Note: You may assume the tree (i.e., the given root node) is not NULL.
 */
public class Problem513_FindBottomLeftTreeNode {
    // 1: level order traversal
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        int result = root.val;
        q.offer(root);
        while(q.size() != 0) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if(i == 0) result = node.val;
                if(node.left != null) q.offer(node.left);
                if(node.right != null) q.offer(node.right);
            }
        }
        return result;
    }

    // 2: 显然BFS加子节点的方向换一下就可以了。。从右到左加入queue，最终root为最左最下节点
    public int findBottomLeftValue2(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            root = q.poll();
            if (root.right != null)
                q.offer(root.right);
            if (root.left != null)
                q.offer(root.left);
        }
        return root.left.val;
    }

    @Test
    public void test() {
        TreeNode r1 = new TreeNode(1);
        TreeNode r2 = new TreeNode(2);
        TreeNode r3 = new TreeNode(3);
        r1.left = r2;
        r1.right = r3;
        System.out.println(findBottomLeftValue(r1));
    }
}
